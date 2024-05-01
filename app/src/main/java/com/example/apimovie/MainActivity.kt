package com.example.apimovie

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.Coil
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apimovie.Constant.MOVIE_IMAGE_BASE_URL
import com.example.apimovie.model.ImageSize
import com.example.apimovie.model.UIState
import com.example.apimovie.presentation.navigation.NavGraph
import com.example.apimovie.presentation.screens.OnBoardingScreen.OnBoardingScreen
import com.example.apimovie.presentation.screens.popular.PopularViewModel
import com.example.apimovie.ui.theme.ApiMovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
       // val viewModel by viewModels<PopularViewModel>()
        super.onCreate(savedInstanceState)

        setContent {
            ApiMovieTheme {
                NavGraph()
//                when (val result = viewModel.popularMovieState.value) {
//                    is UIState.Success -> {
//                        Box(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .background(MaterialTheme.colorScheme.primary)
//                        ) {
//                            Box(modifier = Modifier
//                                .fillMaxWidth()
//                                .fillMaxHeight(0.4f)
//                                .paint(
//                                    painterResource(id = R.drawable.background),
//                                    contentScale = ContentScale.FillBounds,
//                                    sizeToIntrinsics = false
//                                )
//                            )
//
//                            LazyColumn(
//                                modifier = Modifier
//                                    .fillMaxSize()
//
//                            ) {
//
//                                items(result.data?.results.orEmpty()) {
//
//                                    Text(
//                                        text = it.title.orEmpty(),
//                                        modifier = Modifier.padding(12.dp)
//                                    )
//                                    AsyncImage(
//                                        model = "${MOVIE_IMAGE_BASE_URL}${ImageSize.w300}/${it.backdropPath}",
//                                        contentDescription = "movie image"
//                                    )
//
//
//                                    // AsyncImage(model = result.data?.results[it.backdropPath], contentDescription = "image")
//                                    //AsyncImage(model = ImageRequest.Builder(context = LocalContext.current).data(Constant.MOVIE_BASE_URL.plus(it.backdropPath)).build(), contentDescription = "image")
//                                }
//
//                            }
//                        }
//
//                    }
//
//                    is UIState.Empty -> {}
//                    is UIState.Loading -> {}
//                    is UIState.Error -> {}
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MainScreen(viewModel: PopularViewModel= hiltViewModel()){
    when (val result = viewModel.popularMovieState.value) {
        is UIState.Success -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .paint(
                        painterResource(id = R.drawable.background),
                        contentScale = ContentScale.FillBounds,
                        sizeToIntrinsics = false
                    )
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()

                ) {

                    items(result.data?.results.orEmpty()) {

                        Text(
                            text = it.title.orEmpty(),
                            modifier = Modifier.padding(12.dp)
                        )
                        AsyncImage(
                            model = "${MOVIE_IMAGE_BASE_URL}${ImageSize.w300}/${it.backdropPath}",
                            contentDescription = "movie image"
                        )


                        // AsyncImage(model = result.data?.results[it.backdropPath], contentDescription = "image")
                        //AsyncImage(model = ImageRequest.Builder(context = LocalContext.current).data(Constant.MOVIE_BASE_URL.plus(it.backdropPath)).build(), contentDescription = "image")
                    }

                }
            }

        }

        is UIState.Empty -> {}
        is UIState.Loading -> {}
        is UIState.Error -> {}
    }



}