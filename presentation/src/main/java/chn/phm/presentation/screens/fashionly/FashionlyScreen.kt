package chn.phm.presentation.screens.fashionly

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import chn.phm.presentation.R
import chn.phm.presentation.base.theme.RedLight
import chn.phm.presentation.utils.permission.PermissionHandlerHost
import chn.phm.presentation.utils.permission.PermissionHandlerHostState
import chn.phm.presentation.utils.permission.PermissionHandlerResult
import chn.phm.presentation.utils.permission.showAppSettingsSnackbar
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@Composable
fun FashionlyScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 64.dp)
        .background(color = Color.White),
    snackbarHostState: SnackbarHostState
) {
    val scrollState = rememberScrollState()
    val modelImageUri = remember { mutableStateOf<Uri?>(null) }
    val clothImageUri = remember { mutableStateOf<Uri?>(null) }

    Column(
        modifier = modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HeaderSection(modelImageUri, clothImageUri)

        NoteSection()

        ImageSelectionSection(
            modelImage = modelImageUri,
            clothImage = clothImageUri,
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
fun HeaderSection(modelImage: MutableState<Uri?>, clothImage: MutableState<Uri?>) {
    val hint = stringResource(id = R.string.home_prompt_hint)
    var text by remember { mutableStateOf(hint) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.home_prompt),
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge
        )

        RoundedCornerOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(52.dp),
            value = text,
            onValueChange = { text = it },
            placeholder = hint
        )

        MixButton(modelImage = modelImage, clothImage = clothImage)
    }
}

@Composable
fun NoteSection() {
    Text(
        text = stringResource(id = R.string.home_note_content),
        color = RedLight,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(bottom = 8.dp, top = 16.dp)
            .fillMaxWidth(0.9f),
        style = MaterialTheme.typography.labelSmall.copy(fontStyle = FontStyle.Italic)
    )
}

@Composable
fun ImageSelectionSection(
    modelImage: MutableState<Uri?>,
    clothImage: MutableState<Uri?>,
    snackbarHostState: SnackbarHostState
) {
    val modelImagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            modelImage.value = uri
        }
    )

    val clothImagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            clothImage.value = uri
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

    Text(
        text = stringResource(id = R.string.home_model_image),
        color = Color.Black,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 16.dp)
    )

    FashionlyImage(image = modelImage) {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            when (permissionHandlerHostState.handlePermissions()) {
                PermissionHandlerResult.DENIED -> {
                    snackbarHostState.showAppSettingsSnackbar(
                        message = "App permission denied",
                        openSettingsActionLabel = "Settings",
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

    Text(
        text = stringResource(id = R.string.home_cloth_image),
        color = Color.Black,
        modifier = Modifier.padding(vertical = 16.dp),
        style = MaterialTheme.typography.bodyMedium
    )

    FashionlyImage(image = clothImage) {
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            when (permissionHandlerHostState.handlePermissions()) {
                PermissionHandlerResult.DENIED -> {
                    snackbarHostState.showAppSettingsSnackbar(
                        message = "App permission denied",
                        openSettingsActionLabel = "Settings",
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

@Composable
fun MixButton(modelImage: MutableState<Uri?>, clothImage: MutableState<Uri?>) {
    Button(
        onClick = { /* Handle button click here */ },
        enabled = modelImage.value != null && clothImage.value != null,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
            disabledContainerColor = Color.LightGray
        )
    ) {
        Text(
            stringResource(id = R.string.home_mix),
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun FashionlyImage(image: MutableState<Uri?>, onImageClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .aspectRatio(9f / 16f)
            .clip(RoundedCornerShape(12.dp))
            .background(if (image.value == null) Color.LightGray else Color.Transparent)
            .clickable(onClick = onImageClick),
        contentAlignment = Alignment.Center
    ) {
        if (image.value != null) {
            val painter = rememberAsyncImagePainter(model = image.value)
            Image(
                painter = painter,
                contentDescription = stringResource(id = R.string.home_content_desc_select_image),
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_add_image),
                contentDescription = stringResource(id = R.string.home_content_desc_add_image),
                modifier = Modifier.fillMaxSize(0.5f)
            )
        }
    }
}

@Composable
fun RoundedCornerOutlinedTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = ""
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                placeholder,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        maxLines = 1
    )
}
