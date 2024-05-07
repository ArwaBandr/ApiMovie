package com.example.apimovie.presentation.screens.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.apimovie.Constant
import com.example.apimovie.R
import com.example.apimovie.model.ImageSize
import com.example.apimovie.model.Results
import com.example.apimovie.model.UIState
import com.example.apimovie.presentation.navigation.Scrrens
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun MainScreen(
    navController: NavHostController,
    popularMovieState: MutableStateFlow<PagingData<Results>>,
){

    val moviePagingStateItem =popularMovieState.collectAsLazyPagingItems()
    Box {
            LazyVerticalGrid(columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center) {
                items(moviePagingStateItem.itemCount){index ->
                    if(moviePagingStateItem[index]?.adult==false){
                        AsyncImage(
                                model = "${Constant.MOVIE_IMAGE_BASE_URL}${ImageSize.w300}/${moviePagingStateItem[index]?.posterPath}",
                                contentDescription = "movie image",
                            modifier = Modifier
                                .padding(2.dp)
                                .clickable {
                                       navController.navigate("${Scrrens.Details.rout}/${moviePagingStateItem[index]?.id}")
                                },
                            contentScale = ContentScale.FillWidth,
                            error = painterResource(R.drawable.ic_launcher_background),
                            placeholder = painterResource(R.drawable.ic_launcher_background)
                        )

                    }
                }

            }
        moviePagingStateItem.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    Row ( modifier= Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                        ){
                      CircularProgressIndicator()

                    }
                }

                loadState.refresh is LoadState.Error ->{
                    val error =moviePagingStateItem.loadState.refresh as LoadState.Error
                    Row (Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically){
                        Text(text = error.error.localizedMessage.orEmpty(), modifier = Modifier)

                    }
                }

                loadState.append is LoadState.Loading ->{
                    Row (Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically){
                        CircularProgressIndicator()
                    }
                }

                loadState.append is LoadState.Error -> {
                    val error = moviePagingStateItem.loadState.append as LoadState.Error
                    Text(
                        text = error.error.localizedMessage.orEmpty(),
                        modifier = Modifier,
                    )
                }
            }
        }

    }







//    Column(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primary)) {
//
//
//        when (val result = popularMovieState.value) {
//            is UIState.Success -> {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.primary)
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight(0.4f)
//                            .paint(
//                                painterResource(id = R.drawable.background),
//                                contentScale = ContentScale.FillBounds,
//                                sizeToIntrinsics = false
//                            )
//                    )
//
//                    LazyColumn(
//                        modifier = Modifier
//                            .fillMaxSize()
//
//                    ) {
//
//                        items(result.data?.results.orEmpty()) {
//
//                            Text(
//                                text = it.title.orEmpty(),
//                                modifier = Modifier.padding(12.dp)
//                            )
//                            AsyncImage(
//                                model = "${Constant.MOVIE_IMAGE_BASE_URL}${ImageSize.w300}/${it.backdropPath}",
//                                contentDescription = "movie image"
//                            )
//                        }
//
//                    }
//                }
//
//            }
//
//            is UIState.Empty -> {}
//            is UIState.Loading -> {}
//            is UIState.Error -> {}
//        }
//
//    }

}