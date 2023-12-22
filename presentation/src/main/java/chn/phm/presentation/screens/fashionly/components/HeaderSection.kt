package chn.phm.presentation.screens.fashionly.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import chn.phm.presentation.R
import chn.phm.presentation.base.theme.Grey900
import chn.phm.presentation.screens.fashionly.ClothType
import java.util.Locale

@Composable
fun HeaderSection(
    modelImage: MutableState<Uri?>,
    clothImage: MutableState<Uri?>,
    onMixBtnClicked: () -> Unit,
    onPromptValueChange: (String) -> Unit
) {

    val hint = stringResource(id = R.string.home_prompt_hint)
    val text = rememberSaveable { mutableStateOf(hint) }

    LaunchedEffect(key1 = text) {
        onPromptValueChange.invoke(text.value)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RoundedCornerOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .height(52.dp)
                .background(Color.White),
            value = text.value,
            onValueChange = {
                text.value = it
                onPromptValueChange.invoke(it)
            },
            placeholder = hint
        )

        Spacer(modifier = Modifier.padding(start = 8.dp))

        GenerateButton(
            modelImage = modelImage,
            clothImage = clothImage,
            onClicked = onMixBtnClicked,
            modifier = Modifier
                .fillMaxWidth()
                .size(52.dp)
        )
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
fun GenerateButton(
    modifier: Modifier,
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
        ),
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_magic),
            contentDescription = "App Icon",
            // Keep original color of the icon
            tint = Color.Unspecified
        )

        Text(
            stringResource(id = R.string.home_mix),
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun ClothingTypeSelector(obClothTypeSelected: (ClothType) -> Unit) {
    val selectedOption = rememberSaveable { mutableStateOf<ClothType?>(ClothType.UPPER_BODY) }
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
                modifier = Modifier.padding(end = 12.dp),
                onClick = {
                    selectedOption.value = option
                    obClothTypeSelected.invoke(
                        selectedOption.value!!
                    )
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (option == selectedOption.value) Grey900 else Color.White,
                    contentColor = if (option == selectedOption.value) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                ),
                shape = ShapeDefaults.Small
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
