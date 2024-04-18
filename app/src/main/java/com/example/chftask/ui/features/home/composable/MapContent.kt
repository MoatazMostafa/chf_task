import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.chftask.BuildConfig
import com.example.chftask.ui.features.home.model.VenueUIModel
import com.example.chftask.ui.shared.dialog.MessageDialog
import com.example.chftask.ui.shared.uimodel.DialogTexts
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun MapContent(
    modifier: Modifier = Modifier,
    venuesList: List<VenueUIModel>
) {
    val mapView = rememberMapViewWithLifecycle()
    val selectedVenue = remember { mutableStateOf(venuesList.firstOrNull()) }
    val shouldShowDialog = remember { mutableStateOf(false) }
    val mapInitialized = rememberSaveable { mutableStateOf(false) }

    AndroidView(
        modifier = modifier,
        factory = { mapView }
    ) { mapView ->
        mapView.getMapAsync { googleMap ->
            if (!mapInitialized.value) {
                addMarkersToMap(googleMap, venuesList)
                mapInitialized.value = true
            }
            googleMap.setOnMarkerClickListener { marker ->
                val venue = marker.tag as? VenueUIModel
                venue?.let {
                    selectedVenue.value = it
                    shouldShowDialog.value = true
                }
                true
            }
        }
    }
    if (shouldShowDialog.value) {
        MessageDialog(
            dialogTexts = DialogTexts(
                title = selectedVenue.value?.name ?: "",
                message = "Location: ${selectedVenue.value?.location?.address?:""}\n" +
                          "Category: ${selectedVenue.value?.categories?.firstOrNull()?.name?:""}"
            ),
            onConfirm = {
                shouldShowDialog.value = false
            },
            onDismiss = {
                shouldShowDialog.value = false
            }
        )
    }
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply { BuildConfig.GOOGLE_MAPS_ID }
    }
    DisposableEffect(Unit) {
        mapView.onCreate(Bundle())
        mapView.onStart()
        onDispose {
            mapView.onStop()
            mapView.onDestroy()
        }
    }
    return mapView
}

fun addMarkersToMap(googleMap: GoogleMap, venuesList: List<VenueUIModel>) {
    venuesList.forEach { venue ->
        venue.location?.let { location ->
            val position = LatLng(location.lat ?: 0.0, location.lng ?: 0.0)
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .position(position)
                    .title("${venue.name}")
            )
            marker?.tag = venue
        }
    }
    venuesList.firstOrNull()?.location?.let { location ->
        val position = LatLng(location.lat ?: 0.0, location.lng ?: 0.0)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 10f))
    }
}