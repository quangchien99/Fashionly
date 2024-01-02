package chn.phm.presentation.screens.fashionly.components

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import chn.phm.presentation.R
import chn.phm.presentation.base.components.FashionlyImage
import chn.phm.presentation.utils.permission.PermissionHandlerHost
import chn.phm.presentation.utils.permission.PermissionHandlerHostState
import chn.phm.presentation.utils.permission.PermissionHandlerResult
import chn.phm.presentation.utils.permission.showAppSettingsSnackbar
import kotlinx.coroutines.launch

@Composable
fun ImageSelectionSection(
    modelImage: MutableState<Uri?>,
    clothImage: MutableState<Uri?>,
    snackBarHostState: SnackbarHostState
) {
    val modelImagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                modelImage.value = uri
            }
        }
    )

    val clothImagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                clothImage.value = uri
            }
        }
    )

    val permissionHandlerHostState =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PermissionHandlerHostState(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            PermissionHandlerHostState(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

    PermissionHandlerHost(
        hostState = permissionHandlerHostState,
    )

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val message = stringResource(id = R.string.common_permission_denied)
    val openSettingsActionLabel = stringResource(id = R.string.common_settings)

    FashionlyImage(
        title = stringResource(id = R.string.home_model_image),
        image = modelImage,
        defaultImageId = R.raw.model_image_sample
    ) {
        coroutineScope.launch {
            snackBarHostState.currentSnackbarData?.dismiss()
            when (permissionHandlerHostState.handlePermissions()) {
                PermissionHandlerResult.DENIED -> {
                    snackBarHostState.showAppSettingsSnackbar(
                        message = message,
                        openSettingsActionLabel = openSettingsActionLabel,
                        context = context
                    )
                }
                PermissionHandlerResult.GRANTED -> {
                    modelImagePicker.launch("image/*")
                }
                PermissionHandlerResult.DENIED_NEXT_RATIONALE -> {} // noop
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    FashionlyImage(
        title = stringResource(id = R.string.home_cloth_image),
        image = clothImage,
        defaultImageId = R.raw.cloth_image_sampe
    ) {
        coroutineScope.launch {
            snackBarHostState.currentSnackbarData?.dismiss()
            when (permissionHandlerHostState.handlePermissions()) {
                PermissionHandlerResult.DENIED -> {
                    snackBarHostState.showAppSettingsSnackbar(
                        message = message,
                        openSettingsActionLabel = openSettingsActionLabel,
                        context = context
                    )
                }
                PermissionHandlerResult.GRANTED -> {
                    clothImagePicker.launch("image/*")
                }
                PermissionHandlerResult.DENIED_NEXT_RATIONALE -> {} // noop
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
}
