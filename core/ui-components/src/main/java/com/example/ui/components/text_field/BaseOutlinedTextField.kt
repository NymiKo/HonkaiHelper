package com.example.ui.components.text_field

import androidx.compose.ui.unit.sp

@androidx.compose.runtime.Composable
fun BaseOutlinedTextField(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier.Companion,
    value: String,
    enabled: Boolean = false,
    label: String = "",
    singleLine: Boolean = false,
    isError: Boolean = false,
    supportingText: String = "",
    shape: androidx.compose.ui.graphics.Shape = androidx.compose.material3.OutlinedTextFieldDefaults.shape,
    visualTransformation: androidx.compose.ui.text.input.VisualTransformation = androidx.compose.ui.text.input.VisualTransformation.Companion.None,
    onValueChanged: (newValue: String) -> Unit,
    leadingIcon: @androidx.compose.runtime.Composable (() -> Unit)? = null,
    trailingIcon: @androidx.compose.runtime.Composable (() -> Unit)? = null,
) {
    androidx.compose.material3.OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged::invoke,
        enabled = enabled,
        label = {
            androidx.compose.material3.Text(text = label, fontSize = 16.sp)
        },
        leadingIcon = leadingIcon,
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            unfocusedLabelColor = com.example.core.ui.theme.Grey,
            focusedLabelColor = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
            focusedBorderColor = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
            unfocusedLeadingIconColor = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
            focusedLeadingIconColor = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
            cursorColor = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
            errorBorderColor = com.example.core.ui.theme.Red,
            errorSupportingTextColor = com.example.core.ui.theme.Red,
            errorLabelColor = com.example.core.ui.theme.Red,
        ),
        shape = shape,
        singleLine = singleLine,
        isError = isError,
        supportingText = {
            if (isError) androidx.compose.material3.Text(text = supportingText, fontSize = 14.sp)
        },
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
    )
}