package ru.radzze.ricmasterstask.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import ru.radzze.ricmasterstask.MainScreenViewModel
import ru.radzze.ricmasterstask.ui.theme.BackgroundColor
import ru.radzze.ricmasterstask.ui.theme.CirceText
import ru.radzze.ricmasterstask.ui.theme.DividerColor
import ru.radzze.ricmasterstask.ui.theme.TittleTextColor

@Composable
fun MainScreen(
    viewModel:MainScreenViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CirceText(
            modifier = Modifier.padding(0.dp,30.dp),
            text = "Мой дом",
            size = 21,
            weight = FontWeight.Normal,
            color = TittleTextColor
        )
        viewModel.jopa()
        TabRow(selectedTabIndex = viewModel.selectedTabIndex,) {
            viewModel.tabList.forEachIndexed { index,item->
                Tab(selected = index == viewModel.selectedTabIndex, onClick = {
                    viewModel.selectedTabIndex = index
                },
                    text = {
                        CirceText(modifier = Modifier, text = item , size = 17, weight = FontWeight.Normal , color = TittleTextColor )
                    },
                    selectedContentColor = DividerColor,
                    unselectedContentColor = BackgroundColor,
                    modifier = Modifier.background(BackgroundColor)
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewMainScreen(){
    MainScreen()
}