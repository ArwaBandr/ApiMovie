package com.example.apimovie.presentation.screens.OnBoardingScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.apimovie.R
import com.example.apimovie.presentation.navigation.Scrrens
import com.example.apimovie.presentation.navigation.popUpToTop

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(OnBoardingViewModel: OnBoardingViewModel, navController: NavHostController) {
    val onBoardingComleted by OnBoardingViewModel.OnBoardingCompleted.collectAsState()

    if (onBoardingComleted) {
        navController.navigate(Scrrens.mainScreen.rout) {
            popUpToTop(navController)
        }
    } else {
        val pagerState = rememberPagerState { 3 }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            pageSize = PageSize.Fill,

            ) {
            when (pagerState.currentPage) {
                0 -> FirstScren()
                1 -> SecondScren()
                2 -> ThirdScren(navController) {
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
    }
}


@Composable
fun FirstScren() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSecondary),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .rotate(-90f)
                .paint(
                    painterResource(id = R.drawable.brutalist),
                    contentScale = ContentScale.FillWidth,
                    sizeToIntrinsics = false
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.filmrollsamico),
                    contentDescription = "fil image",
                    modifier = Modifier.size(350.dp)
                )

                Text(
                    text = "Welcome to CineSpectra!",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Explore the latest movies, reserve the perfect seats, and experience the cinema in a whole new way.",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun SecondScren() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSecondary),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .rotate(-90f)
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.brutalist),
                    contentScale = ContentScale.FillWidth,
                    sizeToIntrinsics = false
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.home_cinema_amico),
                    contentDescription = "fil image",
                    modifier = Modifier.size(350.dp)
                )

                Text(
                    text = "Welcome to CineSpectra!",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Explore the latest movies, reserve the perfect seats, and experience the cinema in a whole new way.",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }

}

@Composable
fun ThirdScren(navController: NavHostController, onFinishClick: () -> Unit) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSecondary),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .rotate(-90f)
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.brutalist),
                    contentScale = ContentScale.FillWidth,
                    sizeToIntrinsics = false
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.movie_night_amico),
                    contentDescription = "fil image",
                    modifier = Modifier.size(350.dp)
                )

                Text(
                    text = "Welcome to CineSpectra!",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Explore the latest movies, reserve the perfect seats, and experience the cinema in a whole new way.",
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Row(
                    modifier = Modifier
                        .padding(40.dp)
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AnimatedVisibility(
                        modifier = Modifier.fillMaxWidth(),
                        visible = true
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(Scrrens.mainScreen.rout) {
                                    popUpToTop(navController)
                                }
                                onFinishClick.invoke()
                            },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(5.dp)
                        ) {
                            Text(text = "lets get started", modifier = Modifier.padding(4.dp))
                        }
                    }


                }
            }
        }
    }}

