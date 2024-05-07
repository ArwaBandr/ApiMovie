package com.example.apimovie.presentation.screens.details

import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.apimovie.Constant
import com.example.apimovie.R
import com.example.apimovie.model.GetMovieDetailsResponse
import com.example.apimovie.model.ImageSize
import com.example.apimovie.model.UIState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
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
    val result = viewModel.DetailsState.value
    when (result) {
        is UIState.Success -> {
            Box(Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                        .paint(
                            painterResource(id = R.drawable.background),
                            contentScale = ContentScale.FillBounds,
                            sizeToIntrinsics = false
                        )
                )
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {

                        AsyncImage(
                            model = "${Constant.MOVIE_IMAGE_BASE_URL}${ImageSize.w300}/${result.data?.backdropPath}",
                            contentDescription = "movie image",
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Text(
                            text = result.data?.title.toString(),
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.spark_icon),
                                contentDescription = "spark"
                            )
                            Text(text = result.data?.voteAverage.toString())

                            Text(text = " (${result.data?.voteCount.toString()} reviews)")

                        }
                        Text(text = result.data?.overview.toString())
                        Text(text = result.data?.releaseDate.toString())
                    }
                }
            }
        }


        is UIState.Error -> {}
        is UIState.Loading -> {}
        is UIState.Empty -> {}

    }


}