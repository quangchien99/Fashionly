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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import chn.phm.domain.model.fashionly.FashionlyData
import chn.phm.presentation.R
import chn.phm.presentation.base.components.SuggestionBottomSheet
import chn.phm.presentation.base.components.dialog.ErrorDialog
import chn.phm.presentation.base.components.dialog.LoadingDialog
import chn.phm.presentation.screens.fashionly.components.ClothingTypeSelector
import chn.phm.presentation.screens.fashionly.components.HeaderSection
import chn.phm.presentation.screens.fashionly.components.ImageSelectionSection

@Composable
fun FashionlyScreen(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 24.dp)
        .background(color = Color.White),
    snackbarHostState: SnackbarHostState
) {
    val viewModel: FashionlyViewModel = hiltViewModel()
    val uploadedImages by viewModel.uploadedImages.observeAsState()
    val state = viewModel.fashionlyUiState.observeAsState()

    when (val uiState = state.value) {
        is FashionlyUiState.Loading -> {
            LoadingDialog()
        }
        is FashionlyUiState.Success -> {
            // Show success content
            // uiState.data contains the success data
        }
        is FashionlyUiState.Error -> {
            ErrorDialog(message = "Something went wrong,\n Please try again")
        }
        else -> {
        }
    }

    LaunchedEffect(uploadedImages) {
        uploadedImages?.let {
            viewModel.fashionize(
                FashionlyData(
                    key = "sIuXvK5HOEti1312HBQuRHiGhNNf6POciL2iFOaxzov5RE1BfWGGa53Sujqe",
                    prompt = "A realistic photo of a model wearing a beautiful white top.",
                    negativePrompt = "Low quality, unrealistic, bad cloth, warped cloth",
                    modelImage = "https://www.vstar.in/media/cache/350x0/catalog/product/f/0/f09632_parent_1_1653003388.jpg",
                    clothImage = "https://thumbs.dreamstime.com/b/plain-hollow-female-tank-top-shirt-isolated-white-background-30020169.jpg",
                    clothType = "upper_body",
                    height = 512,
                    width = 384,
                    guidanceScale = 8.0,
                    numInferenceSteps = 20,
                    seed = 128915590,
                    temp = "no",
                    webhook = null,
                    trackId = null
                )
            )
        }
    }

    val scrollState = rememberScrollState()
    val modelImageUri = remember { mutableStateOf<Uri?>(null) }
    val clothImageUri = remember { mutableStateOf<Uri?>(null) }

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
            HeaderSection(modelImageUri, clothImageUri) {
                viewModel.uploadImages(
                    listOf(modelImageUri.value!!, clothImageUri.value!!)
                )
            }

            ClothingTypeSelector()

            Column(
                modifier = modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageSelectionSection(
                    modelImage = modelImageUri,
                    clothImage = clothImageUri,
                    snackbarHostState = snackbarHostState
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
