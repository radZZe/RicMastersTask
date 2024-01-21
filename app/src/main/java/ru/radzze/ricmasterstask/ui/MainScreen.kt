package ru.radzze.ricmasterstask.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import ru.radzze.ricmasterstask.R
import ru.radzze.ricmasterstask.model.Door

import ru.radzze.ricmasterstask.ui.theme.BackgroundColor
import ru.radzze.ricmasterstask.ui.theme.CirceText
import ru.radzze.ricmasterstask.ui.theme.DividerColor
import ru.radzze.ricmasterstask.ui.theme.MainTextColor
import ru.radzze.ricmasterstask.ui.theme.TittleTextColor
import ru.radzze.ricmasterstask.ui.theme.WhiteColor

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CirceText(
            modifier = Modifier.padding(0.dp, 30.dp),
            text = "Мой дом",
            size = 21,
            weight = FontWeight.Normal,
            color = TittleTextColor
        )
        TabRow(selectedTabIndex = viewModel.selectedTabIndex) {
            viewModel.tabList.forEachIndexed { index, item ->
                Tab(
                    selected = index == viewModel.selectedTabIndex, onClick = {
                        viewModel.selectedTabIndex = index
                    },
                    text = {
                        CirceText(
                            modifier = Modifier,
                            text = item,
                            size = 17,
                            weight = FontWeight.Normal,
                            color = TittleTextColor
                        )
                    },
                    selectedContentColor = DividerColor,
                    unselectedContentColor = BackgroundColor,
                    modifier = Modifier.background(BackgroundColor)
                )
            }
        }
        if (viewModel.selectedTabIndex == 1) {
            viewModel.getCameras()
            LazyColumn(
                contentPadding = PaddingValues(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(viewModel.doorsList.value!!) {
                    doorItem(door = it)
                }
            }
        }else{
            viewModel.getDoors()
            Column(modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())) {
                viewModel.camerasList.value?.forEach {
                    CirceText(modifier = Modifier, text =it.key , size =21 , weight = FontWeight.Normal , color = MainTextColor)
                    it.value.forEach{
                        cameraItem(name = it.name, image = it.snapshot)
                    }
                }
            }
        }
    }
}

@Composable
fun cameraItem(name:String,image:String){
    Box(
        modifier = Modifier
            .fillMaxWidth(0.90f)
            .clip(RoundedCornerShape(10))
            .background(WhiteColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = image,
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = null
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment =
                Alignment.CenterVertically
            ) {
                CirceText(
                    modifier = Modifier,
                    text = name,
                    size = 17,
                    weight = FontWeight.Normal,
                    color = MainTextColor
                )
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.defender),
                    contentDescription = null
                )
            }
        }

    }
}

@Composable
fun doorItem(door: Door) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.90f)
            .clip(RoundedCornerShape(10))
            .background(WhiteColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment =
            Alignment.CenterVertically
        ) {
            CirceText(
                modifier = Modifier,
                text = door.name,
                size = 17,
                weight = FontWeight.Normal,
                color = MainTextColor
            )
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.lockon),
                contentDescription = null
            )
        }

    }
}
