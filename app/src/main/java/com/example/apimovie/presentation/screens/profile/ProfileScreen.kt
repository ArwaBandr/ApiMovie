package com.example.apimovie.presentation.screens.profile

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.apimovie.model.UIState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel, navController: NavHostController) {
    LaunchedEffect(Unit) {
        viewModel.getUserToken()
    }
    val userToken = viewModel.userTokenState.collectAsState()
    val sessionId = viewModel.userSessionIdtState.collectAsState()
    val userAccount = viewModel.userAccountState.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    var generatedUserToken by remember {
        mutableStateOf("")
    }
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.wrapContentSize(),
                onDismissRequest = {
                    showBottomSheet = false
                    if (generatedUserToken.isNotEmpty()) {
                        viewModel.getSessioId(generatedUserToken)
                    }
                    navController.popBackStack()
                },
                sheetState = sheetState,
                windowInsets = WindowInsets.waterfall
            ) {
                WebViewScreen("https://www.themoviedb.org/authenticate/$generatedUserToken")
            }

        }

        when (val userTokenResult = userToken.value) {

            is UIState.Success -> {
                showBottomSheet = true
                generatedUserToken = userTokenResult.data?.requestToken.orEmpty()
            }

            is UIState.Loading -> {}
            is UIState.Error -> {}
            is UIState.Empty -> {}
        }

        when (val session = sessionId.value) {
            is UIState.Success -> {
                showBottomSheet = false
                val generatedSessioId = session.data?.sessionId
                if (generatedSessioId?.isNotEmpty() == true) {
                    LaunchedEffect(viewModel) {
                        viewModel.getAccountId(generatedSessioId.toString())
                    }
                }
            }

            is UIState.Loading -> {}
            is UIState.Error -> {}
            is UIState.Empty -> {}

        }
        when (val account = userAccount.value) {
            is UIState.Success -> {
                Text(text = account.data?.username.toString())
                Text(text = account.data?.id.toString())
            }

            is UIState.Loading -> {}
            is UIState.Error -> {}
            is UIState.Empty -> {}

        }

    }

}

@Composable
fun WebViewScreen(url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.setSupportZoom(true)
        }
    },
        update = {webView ->
            webView.loadUrl(url)
        }
    )
}
    









