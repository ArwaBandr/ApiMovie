package com.example.apimovie.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.apimovie.Constant
import com.example.apimovie.R
import com.example.apimovie.model.ImageSize
import com.example.apimovie.model.UIState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun DetailsScreen(
    moviId: Int?,
    navController: NavController,
    viewModel: DetailsViewModel
) {
    LaunchedEffect(moviId) {
        if (moviId != null) {
            viewModel.getDetails(moviId)
        }
    }

    when (val result = viewModel.DetailsState.value) {
        is UIState.Success -> {

            Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = "${Constant.MOVIE_IMAGE_BASE_URL}${ImageSize.w300}/${result.data?.posterPath}",
                        contentDescription = "movie image",
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.TopStart)
                    )


                LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.BottomCenter)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .shadow(
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                                    clip = true
                                )
                        ) {
                            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Bottom) {
                                AsyncImage(
                                    model = "${Constant.MOVIE_IMAGE_BASE_URL}${ImageSize.w300}/${result.data?.backdropPath}",
                                    contentDescription = "movie image",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                )

                                Text(
                                    text = result.data?.title ?: "",
                                    fontSize = 35.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Serif,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .wrapContentSize()
                                            .border(
                                                width = 1.dp,
                                                color = MaterialTheme.colorScheme.primary,
                                                shape = RoundedCornerShape(10.dp)
                                            ),
                                        verticalAlignment = Alignment.CenterVertically,

                                    ) {
                                        Spacer(modifier = Modifier
                                            .width(14.dp)
                                            .padding(3.dp))
                                        Icon(
                                            painter = painterResource(id = R.drawable.spark_icon),
                                            contentDescription = "spark",
                                            tint = Color.Unspecified
                                        )
                                        Spacer(modifier = Modifier
                                            .width(14.dp)
                                            .padding(3.dp))
                                        Text(
                                            text = result.data?.voteAverage.toString(),
                                        )
                                        Spacer(modifier = Modifier
                                            .width(14.dp)
                                            .padding(3.dp))
                                    }
                                    Text(
                                        text = " (${result.data?.voteCount} reviews)"
                                    )
                                }

                                Text(
                                    text = result.data?.overview ?: "",
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )

//                                Text(
//                                    text = result.data?.releaseDate ?: "",
//                                    modifier = Modifier.padding(bottom = 8.dp)
//                                )
                            }
                        }
                    }
                }
            }

        }


        is UIState.Error -> {}
        is UIState.Loading -> {}
        is UIState.Empty -> {}

    }


}