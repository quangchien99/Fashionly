package chn.phm.presentation.screens.fashionly

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import chn.phm.presentation.R
import chn.phm.presentation.base.components.SuggestionBottomSheet
import chn.phm.presentation.base.components.dialog.ErrorDialog
import chn.phm.presentation.base.components.dialog.LoadingDialog
import chn.phm.presentation.base.components.dialog.ResultDialog
import chn.phm.presentation.base.theme.BackgroundLight
import chn.phm.presentation.screens.fashionly.components.ClothingTypeSelector
import chn.phm.presentation.screens.fashionly.components.HeaderSection
import chn.phm.presentation.screens.fashionly.components.ImageSelectionSection

@Composable
fun FashionlyScreen(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(color = BackgroundLight),
    snackBarHostState: SnackbarHostState,
    viewModel: FashionlyViewModel
) {
    val uploadImagesStatus by viewModel.uploadImagesStatus.observeAsState()
    val getAPIKeyStatus by viewModel.getAPIKeyStatus.observeAsState()
    val state = viewModel.fashionlyUiState.observeAsState()

    when (val uiState = state.value) {
        is FashionlyUiState.Loading -> {
            LoadingDialog()
        }
        is FashionlyUiState.Success -> {
            viewModel.insertFashionlyResult(uiState.data.toString())
            ResultDialog(
                imageResultUrl = uiState.data.toString(),
                snackBarHostState = snackBarHostState,
                onGenerateNewImage = {
                    viewModel.resetStatus()
                },
                onSaveToDevice = {
                    viewModel.saveImageToStorage(uiState.data.toString())
                    viewModel.resetStatus()
                }
            )
        }
        is FashionlyUiState.Error -> {
            ErrorDialog(message = "Something went wrong,\n Please try again")
            viewModel.resetStatus()
        }
        else -> {
        }
    }

    LaunchedEffect(uploadImagesStatus) {
        if (uploadImagesStatus == true) {
            viewModel.getStableDiffusionApiKey()
        }
    }

    LaunchedEffect(getAPIKeyStatus) {
        if (getAPIKeyStatus == true) {
            viewModel.fashionize()
        }
    }

    val scrollState = rememberScrollState()
    val modelImageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val clothImageUri = rememberSaveable { mutableStateOf<Uri?>(null) }

    var showSuggestionBottomSheet by remember { mutableStateOf(false) }
    if (showSuggestionBottomSheet) {
        SuggestionBottomSheet() {
            showSuggestionBottomSheet = false
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderSection(
                modelImageUri,
                clothImageUri,
                onMixBtnClicked = {
                    viewModel.uploadImages(
                        listOf(modelImageUri.value!!, clothImageUri.value!!)
                    )
                },
                onPromptValueChange = {
                    viewModel.setPrompt(it)
                }
            )

            ClothingTypeSelector() {
                viewModel.setClothType(it)
            }

            Column(
                modifier = modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageSelectionSection(
                    modelImage = modelImageUri,
                    clothImage = clothImageUri,
                    snackBarHostState = snackBarHostState
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_suggestion),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 64.dp, end = 12.dp)
                .height(40.dp)
                .width(40.dp)
                .clickable {
                    showSuggestionBottomSheet = true
                },
            contentDescription = "Suggestion"
        )
    }
}
