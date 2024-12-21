package com.example.ui.components.text_field

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.core.ui.theme.Grey
import com.example.core.ui.theme.Red

@Composable
fun BaseOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    enabled: Boolean = false,
    label: String = "",
    singleLine: Boolean = false,
    isError: Boolean = false,
    supportingText: String = "",
    shape: Shape = OutlinedTextFieldDefaults.shape,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (newValue: String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged::invoke,
        enabled = enabled,
        label = {
            Text(text = label, fontSize = 16.sp)
        },
        leadingIcon = leadingIcon,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedLabelColor = Grey,
            focusedLabelColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.secondary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary,
            errorBorderColor = Red,
            errorSupportingTextColor = Red,
            errorLabelColor = Red,
        ),
        shape = shape,
        singleLine = singleLine,
        isError = isError,
        supportingText = {
            if (isError) Text(text = supportingText, fontSize = 14.sp)
        },
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
    )
}