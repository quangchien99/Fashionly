package chn.phm.presentation.base.components.dialog

import android.Manifest
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import chn.phm.presentation.R
import chn.phm.presentation.utils.permission.PermissionHandlerHost
import chn.phm.presentation.utils.permission.PermissionHandlerHostState
import chn.phm.presentation.utils.permission.PermissionHandlerResult
import chn.phm.presentation.utils.permission.showAppSettingsSnackbar
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@Composable
fun ResultDialog(
    imageResultUrl: String,
    snackBarHostState: SnackbarHostState,
    onGenerateNewImage: () -> Unit,
    onSaveToDevice: () -> Unit
) {

    val permissionHandlerHostState = PermissionHandlerHostState(
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    PermissionHandlerHost(
        hostState = permissionHandlerHostState,
    )
    val message = stringResource(id = R.string.common_permission_denied)
    val openSettingsActionLabel = stringResource(id = R.string.common_settings)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val showDialog = remember { mutableStateOf(true) }
    if (showDialog.value) {
        Dialog(onDismissRequest = {
        }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .background(Color.White, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = rememberAsyncImagePainter(imageResultUrl),
                        contentDescription = "Loaded Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {

                        OutlinedButton(
                            onClick = {
                                showDialog.value = false
                                onGenerateNewImage()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            ),
                        ) {
                            Text(
                                "Close",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black,
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Blue
                            ),
                            onClick = {
                                showDialog.value = false
                                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                                    coroutineScope.launch {
                                        when (permissionHandlerHostState.handlePermissions()) {
                                            PermissionHandlerResult.DENIED -> {
                                                snackBarHostState.showAppSettingsSnackbar(
                                                    message = message,
                                                    openSettingsActionLabel = openSettingsActionLabel,
                                                    context = context
                                                )
                                            }
                                            PermissionHandlerResult.GRANTED -> {
                                                onSaveToDevice()
                                            }
                                            PermissionHandlerResult.DENIED_NEXT_RATIONALE -> {} // noop
                                        }
                                    }
                                } else {
                                    onSaveToDevice()
                                }
                            }
                        ) {
                            Text(
                                "Save",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White,
                            )
                        }
                    }
                }
            }
        }
    }
}
