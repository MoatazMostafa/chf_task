package com.example.chftask.ui.features.myprofile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chftask.R
import com.example.chftask.ui.shared.uimodel.UserUIModel
import com.example.chftask.ui.theme.CHFTaskTheme
import com.example.chftask.ui.theme.LightGray

@Composable
fun MyProfileContent(modifier: Modifier, user: UserUIModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(color = LightGray, shape = CircleShape),
        ) {
            Image(
                modifier = Modifier.size(150.dp).padding(16.dp),
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "First Name: ${user.firstName}", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Last Name: ${user.lastName}", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Age: ${user.age}", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Email: ${user.email}", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
@Preview
fun MyProfileContentPreview() {
    CHFTaskTheme {

        MyProfileContent(
            modifier = Modifier,
            user = UserUIModel(
                id = "1",
                firstName = "Ahmed",
                lastName = "Mohamed",
                age = "25",
                email = "Ahmed@gmail.com",
                password = "",
            )
        )
    }
}