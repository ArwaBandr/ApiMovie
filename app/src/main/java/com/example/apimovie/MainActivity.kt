package com.example.apimovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.apimovie.Constant.MOVIE_IMAGE_BASE_URL
import com.example.apimovie.model.ImageSize
import com.example.apimovie.model.SearchResponse
import com.example.apimovie.model.UIState
import com.example.apimovie.presentation.navigation.BottomNavigationItem
import com.example.apimovie.presentation.navigation.NavGraph
import com.example.apimovie.presentation.navigation.Scrrens
import com.example.apimovie.presentation.navigation.popUpToTop
import com.example.apimovie.presentation.screens.popular.PopularViewModel
import com.example.apimovie.ui.theme.ApiMovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
       // val viewModel by viewModels<PopularViewModel>()
        super.onCreate(savedInstanceState)
        setContent {
            ApiMovieTheme {
                val navController = rememberNavController()
                var showBottomBar by rememberSaveable {
                    mutableStateOf(true)
                }
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                showBottomBar =when(navBackStackEntry?.destination?.route){
                    Scrrens.OnboardingScreen.rout -> false
                    else -> true
                }
                val navigationSelectedItem = rememberSaveable {
                    mutableIntStateOf(0)
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar {
                                BottomNavigationBar(navigationSelectedItem,navController)
                            }
                        }
                        }
                ){
                    paddingValues ->
                    Box (modifier = Modifier.padding(paddingValues)){
                        NavGraph(navController)
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.BottomNavigationBar(
    navigationSelectedItem:MutableIntState,
    navController :NavHostController
)
{

    BottomNavigationItem().bottomNavigationItem()
        .forEachIndexed { index, NavigationItem ->
            NavigationBarItem(selected = index == navigationSelectedItem.intValue,
                label = { Text(text = NavigationItem.route)},
                icon = {
                    Icon(NavigationItem.icon, contentDescription =NavigationItem.label )
                },
                onClick = {
                    navigationSelectedItem.intValue =index
                    navController.navigate(NavigationItem.route){
                        popUpToTop(navController)
                    }
                }
                )
        }
}
