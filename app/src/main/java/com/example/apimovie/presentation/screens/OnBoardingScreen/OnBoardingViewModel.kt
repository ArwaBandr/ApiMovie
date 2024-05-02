package com.example.apimovie.presentation.screens.OnBoardingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apimovie.domain.onboarding.GetIsSaveFirstTimeDataStoreUseCase
import com.example.apimovie.domain.onboarding.SaveIsFirstTimeinDataStoreUseCase
import com.example.apimovie.presentation.navigation.Scrrens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveFirstTimeDataStoreUseCase: SaveIsFirstTimeinDataStoreUseCase,
    private val getIsSaveFirstTimeDataStoreUseCase: GetIsSaveFirstTimeDataStoreUseCase
) :ViewModel(){

    val OnBoardingCompleted = MutableStateFlow(false)
    var startDistination:String =Scrrens.OnboardingScreen.rout

    init {
        getonBoardingState()
    }
    private fun getonBoardingState(){
        viewModelScope.launch {
            OnBoardingCompleted.value=getIsSaveFirstTimeDataStoreUseCase().stateIn(viewModelScope).value
            startDistination=if (OnBoardingCompleted.value) Scrrens.mainScreen.rout else Scrrens.OnboardingScreen.rout
        }
    }

    fun saveOnBoardingState(showOnBoardingPage:Boolean){

        viewModelScope.launch (Dispatchers.IO){
            saveFirstTimeDataStoreUseCase(ShowTipsPages=showOnBoardingPage)
        }

    }

}