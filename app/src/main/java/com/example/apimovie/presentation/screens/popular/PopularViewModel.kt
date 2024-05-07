package com.example.apimovie.presentation.screens.popular


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.apimovie.domain.popular.GetPopularMoviewUseCase
import com.example.apimovie.model.Results
import com.example.apimovie.model.SearchResponse
import com.example.apimovie.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel@Inject constructor(
    private val getPopularMoviewUseCase: GetPopularMoviewUseCase
) :ViewModel(){

     var popularMovieState: MutableStateFlow<PagingData<Results>> = MutableStateFlow(PagingData.empty())
 init {
     getArtWorks()
 }
    private fun getArtWorks(){
        viewModelScope.launch {
            getPopularMoviewUseCase.invoke().distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect{
                    popularMovieState.value=it
                }
        }
    }
}