package com.example.apimovie.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.apimovie.domain.User.GetSessionIdUseCase
import com.example.apimovie.domain.User.GetUserAccountUseCase
import com.example.apimovie.domain.User.GetUserTokenUseCase
import com.example.apimovie.model.Results
import com.example.apimovie.model.UIState
import com.example.apimovie.model.UserAccount
import com.example.apimovie.model.UserTokenResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val getUserTokenUseCase: GetUserTokenUseCase,
    private val getSessionIdUseCase: GetSessionIdUseCase,
    private val getUserAccountUseCase: GetUserAccountUseCase
    ):ViewModel(){

    var userTokenState: MutableStateFlow<UIState<UserTokenResponse>> = MutableStateFlow(UIState.Loading())
    var userSessionIdtState: MutableStateFlow<UIState<UserTokenResponse>> = MutableStateFlow(UIState.Loading())
    var userAccountState: MutableStateFlow<UIState<UserAccount>> = MutableStateFlow(UIState.Loading())

    fun getUserToken(){
        viewModelScope.launch {
            when (val response = getUserTokenUseCase.invoke()) {
                is UIState.Success -> userTokenState.value = UIState.Success(response.data)
                is UIState.Error -> userTokenState.value = UIState.Error(response.error)
                is UIState.Empty -> userTokenState.value = UIState.Empty(title = response.title)
                is UIState.Loading -> userTokenState.value = UIState.Loading()
            }
        }

        }


    fun getSessioId(requstedToken:String){
        viewModelScope.launch {
            when (val response = getSessionIdUseCase.invoke(requstedToken)) {
                is UIState.Success -> userSessionIdtState.value = UIState.Success(response.data)
                is UIState.Error -> userSessionIdtState.value = UIState.Error(response.error)
                is UIState.Empty -> userSessionIdtState.value = UIState.Empty(title = response.title)
                is UIState.Loading -> userSessionIdtState.value = UIState.Loading()
            }
        }
    }
    fun getAccountId(sessionId:String){
        viewModelScope.launch {
            when (val response = getUserAccountUseCase.invoke(sessionId)) {
                is UIState.Success -> userAccountState.value = UIState.Success(response.data)
                is UIState.Error -> userAccountState.value = UIState.Error(response.error)
                is UIState.Empty -> userAccountState.value = UIState.Empty(title = response.title)
                is UIState.Loading -> userAccountState.value = UIState.Loading()
            }
        }
    }
    }

