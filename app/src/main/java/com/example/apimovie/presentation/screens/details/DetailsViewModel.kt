package com.example.apimovie.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apimovie.domain.details.GetPopularMovieDetalisUseCase
import com.example.apimovie.model.GetMovieDetailsResponse
import com.example.apimovie.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val GetPopularMovieDetailUseCase: GetPopularMovieDetalisUseCase
) : ViewModel() {
    var DetailsState: MutableState<UIState<GetMovieDetailsResponse>> = mutableStateOf(UIState.Loading())
    fun getDetails(movieId:Int) {

        viewModelScope.launch {
            val response = GetPopularMovieDetailUseCase.invoke(movieId)
            when (response) {
                is UIState.Success -> DetailsState.value = UIState.Success(response.data)
                is UIState.Error -> DetailsState.value = UIState.Error(response.error)
                is UIState.Empty -> DetailsState.value = UIState.Empty(title = response.title)
                is UIState.Loading -> DetailsState.value = UIState.Loading()
            }
        }
    }
}