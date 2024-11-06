package com.example.tanorami.auth.login.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.tanorami.base_components.text_field.BaseOutlinedTextField

@Composable
fun LoginOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (newValue: String) -> Unit,
    enabled: Boolean = true,
    label: String,
    isError: Boolean = false,
    supportingText: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    BaseOutlinedTextField(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        value = value,
        onValueChanged = onValueChanged::invoke,
        label = label,
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        isError = isError,
        supportingText = supportingText,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
    )
}