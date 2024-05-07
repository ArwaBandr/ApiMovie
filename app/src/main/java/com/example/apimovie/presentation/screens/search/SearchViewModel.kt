package com.example.apimovie.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.apimovie.domain.search_multi.GetSearchedMultiUseCase
import com.example.apimovie.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getSearchedMultiUseCase: GetSearchedMultiUseCase):ViewModel() {

    var searchMovieState: MutableStateFlow<PagingData<Results>> = MutableStateFlow(PagingData.empty())
     fun getSearchedMulti(searchQuery:String){

        viewModelScope.launch {
            getSearchedMultiUseCase.invoke(searchQuery).cachedIn(viewModelScope)
                .collect{
                    searchMovieState.value=it
                }

        }
    }


}