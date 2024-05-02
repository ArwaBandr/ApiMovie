package com.example.apimovie.presentation.screens.OnBoardingScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.apimovie.R
import com.example.apimovie.presentation.navigation.Scrrens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(OnBoardingViewModel: OnBoardingViewModel, navController: NavHostController) {
    val onBoardingComleted by OnBoardingViewModel.OnBoardingCompleted.collectAsState()

    if(onBoardingComleted){
        navController.navigate(Scrrens.mainScreen.rout)
    }else{
    val pagerState = rememberPagerState { 3 }
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,

        ) {
        when (pagerState.currentPage) {
            0 -> FirstScren()
            1 -> SecondScren()
            2 -> ThirdScren(navController){
                OnBoardingViewModel.saveOnBoardingState(true)
            }

        }
    }
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primaryContainer
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(16.dp)
            )
        }
    }
}}

@Composable
fun FirstScren() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onSecondary)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .paint(
                    painterResource(id = R.drawable.filmrollsamico),
                    contentScale = ContentScale.FillBounds,
                    sizeToIntrinsics = false
                )
                .padding(12.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .rotate(-90f)
                .fillMaxSize()
                .align(Alignment.End)
                .paint(painterResource(id = R.drawable.brutalist))

        )
    }

}

@Composable
fun SecondScren() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onSecondary)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .paint(
                    painterResource(id = R.drawable.home_cinema_amico),
                    contentScale = ContentScale.FillBounds,
                    sizeToIntrinsics = false
                )
                .padding(12.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .rotate(-90f)
                .fillMaxSize()
                .align(Alignment.End)
                .paint(painterResource(id = R.drawable.brutalist))

        )
    }

}

@Composable
fun ThirdScren(navController: NavHostController, FinshOnBoarding: () -> Unit) {


    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onSecondary)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        // horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .paint(
                    painterResource(id = R.drawable.movie_night_amico),
                    contentScale = ContentScale.FillBounds,
                    sizeToIntrinsics = false
                )
        )

        //Spacer(modifier = Modifier.padding(30.dp))
        Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Bottom), contentAlignment = Alignment.Center
            )
            {

                Image(
                    painter = painterResource(id = R.drawable.brutalist),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .rotate(-90f)
                        .fillMaxSize()

                )
                Button(
                    onClick = {
                        navController.navigate(Scrrens.mainScreen.rout)
                        FinshOnBoarding.invoke()
                    }) {
                    Text(text = "lets get started")
                }

            }
        }

    }
}

