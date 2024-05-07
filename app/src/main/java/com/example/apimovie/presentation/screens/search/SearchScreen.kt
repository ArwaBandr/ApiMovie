package com.example.apimovie.presentation.screens.search

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.apimovie.Constant
import com.example.apimovie.R
import com.example.apimovie.model.ImageSize
import com.example.apimovie.presentation.navigation.Scrrens

@Composable
fun SearchScreen(viewModel: SearchViewModel, navController: NavHostController) {

    var searchText by remember {
        mutableStateOf("")
    }

    var result = viewModel.searchMovieState.collectAsLazyPagingItems()
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = searchText,
                onValueChange = { searchText = it },
                leadingIcon ={  Icon(Icons.Filled.Search ,"icon")},
                label = { Text(text = "search")},
                keyboardActions = KeyboardActions(
                    onSearch = {viewModel.getSearchedMulti(searchText)}
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.DarkGray,
                    unfocusedContainerColor = Color.Gray
                )
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center
            ) {
                items(result.itemCount) { index ->
                    if (result[index]?.adult == false) {
                        AsyncImage(
                            model = "${Constant.MOVIE_IMAGE_BASE_URL}${ImageSize.w300}/${result[index]?.posterPath}",
                            contentDescription = "movie image",
                            modifier = Modifier
                                .padding(2.dp)
                                .clickable {
                                    navController.navigate("${Scrrens.Details.rout}/${result[index]?.id}")
                                },
                            contentScale = ContentScale.FillWidth,
                            error = painterResource(R.drawable.ic_launcher_background),
                            placeholder = painterResource(R.drawable.ic_launcher_background)
                        )

                    }
                }

            }
            result.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
//                    Row ( modifier= Modifier.fillMaxSize(),
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically
//                    ){
//                        CircularProgressIndicator()
//
//                    }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = result.loadState.refresh as LoadState.Error
                        Row(
                            Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = error.error.localizedMessage.orEmpty(), modifier = Modifier)

                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        Row(
                            Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = result.loadState.append as LoadState.Error
                        Text(
                            text = error.error.localizedMessage.orEmpty(),
                            modifier = Modifier,
                        )
                    }
                }
            }

        }
    }
}