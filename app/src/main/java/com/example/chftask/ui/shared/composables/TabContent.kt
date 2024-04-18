package com.example.chftask.ui.shared.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chftask.ui.theme.CHFTaskTheme
import com.example.chftask.ui.theme.Gray
import com.example.chftask.ui.theme.Primary
import com.example.chftask.ui.theme.Secondary


@Composable
fun TabContent(
    modifier: Modifier = Modifier,
    tabsList: List<String> = emptyList(),
    selectedTabIndex: Int = 0,
    onTabClick: (String) -> Unit
) {
    var currentSelectedTabIndex by remember {
        mutableIntStateOf(selectedTabIndex)
    }
    Column(modifier = modifier) {
        TabRow(
            modifier = Modifier,
            selectedTabIndex = currentSelectedTabIndex,
            divider = {},
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = Primary,
            indicator = @Composable { tabPositions ->
                if (currentSelectedTabIndex < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[currentSelectedTabIndex]),
                        color = Secondary
                    )
                }
            }) {
            tabsList.forEachIndexed { index, item ->
                Tab(
                    selected = index == currentSelectedTabIndex,
                    onClick = {
                        currentSelectedTabIndex = index
                        onTabClick(item)
                    },
                    text = {
                        Text(
                            text = item,
                            style = MaterialTheme.typography.headlineSmall,
                            color = if (index == currentSelectedTabIndex) MaterialTheme.colorScheme.onSurface else Gray
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun TabContentPreview() {
    CHFTaskTheme {
        TabContent(
            tabsList = listOf("Tab 1", "Tab2"),
            selectedTabIndex = 0,
            onTabClick = { }
        )
    }
}
