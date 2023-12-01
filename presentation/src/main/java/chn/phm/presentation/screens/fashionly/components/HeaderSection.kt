package chn.phm.presentation.screens.fashionly.components

import android.net.Uri
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import chn.phm.presentation.R
import chn.phm.presentation.screens.fashionly.ClothType
import java.util.Locale

@Composable
fun HeaderSection(
    modelImage: MutableState<Uri?>,
    clothImage: MutableState<Uri?>,
    onMixBtnClicked: () -> Unit
) {
    val hint = stringResource(id = R.string.home_prompt_hint)
    var text by remember { mutableStateOf(hint) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp),
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

        MixButton(modelImage = modelImage, clothImage = clothImage, onClicked = onMixBtnClicked)
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

@Composable
fun MixButton(
    modelImage: MutableState<Uri?>,
    clothImage: MutableState<Uri?>,
    onClicked: () -> Unit
) {
    Button(
        onClick = { onClicked() },
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
fun ClothingTypeSelector() {
    var selectedOption by remember { mutableStateOf<ClothType?>(null) }
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClothType.values().forEach { option ->
            OutlinedButton(
                onClick = { selectedOption = option },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (option == selectedOption) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                    contentColor = if (option == selectedOption) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(
                    text = option.name.replace('_', ' ').lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
