package com.example.apimovie.presentation.screens.popular


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apimovie.domain.popular.GetPopularMoviewUseCase
import com.example.apimovie.model.SearchResponse
import com.example.apimovie.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel@Inject constructor(
    private val getPopularMoviewUseCase: GetPopularMoviewUseCase
) :ViewModel(){

     var popularMovieState: MutableState<UIState<SearchResponse>> = mutableStateOf(UIState.Loading())
 init {
     getArtWorks()
 }
    private fun getArtWorks(){
        viewModelScope.launch {

            when(val response =getPopularMoviewUseCase.invoke()){
                is UIState.Success ->popularMovieState.value=UIState.Success(response.data)
                is UIState.Error ->popularMovieState.value=UIState.Error(response.error)
                is UIState.Empty ->popularMovieState.value=UIState.Empty(title=response.title)
                is UIState.Loading -> popularMovieState.value=UIState.Loading()
            }
        }
    }
}