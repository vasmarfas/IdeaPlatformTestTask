package ru.vasmarfas.ideaplatformtesttask.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.vasmarfas.ideaplatformtesttask.R

@Composable
fun DeleteDialog(
    onConfirmation: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    AlertDialog(
        icon = {
            Icon(Icons.Filled.Warning, contentDescription = "Example Icon")
        },
        title = {
            Text(text = stringResource(R.string.product_deletion))
        },
        text = {
            Text(text = stringResource(R.string.are_you_sure_to_delete_selected_product))
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(R.string.yes))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(R.string.no))
            }
        }
    )
}

@Preview
@Composable
fun PreviewDeleteDialog() {
    DeleteDialog()
}