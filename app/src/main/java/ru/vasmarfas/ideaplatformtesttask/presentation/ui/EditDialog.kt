package ru.vasmarfas.ideaplatformtesttask.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vasmarfas.ideaplatformtesttask.R

@Composable
fun EditDialog(
    productAmount: Int,
    onConfirmation: (Int) -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    var amount by remember { mutableIntStateOf(productAmount) }
    AlertDialog(
        icon = {
            Icon(Icons.Filled.Settings, contentDescription = "Example Icon")
        },
        title = {
            Text(stringResource(R.string.products_amount))
        },
        text = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth() // Добавляем отступы вокруг строки
            ) {
                IconButton(
                    onClick = { if (amount > 0) amount -= 1 },
                    modifier = Modifier.size(48.dp) // Увеличиваем размер кнопки
                ) {
                    Icon(
                        painter = painterResource(R.drawable.remove_icon),
                        contentDescription = "remove",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp), // Увеличиваем размер иконки
                        // Явно указываем цвет иконки
                    )
                }
                Text(
                    text = amount.toString(),
                    style = TextStyle(
                        fontSize = 24.sp, // Увеличиваем размер текста
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp) // Добавляем отступы между текстом и кнопками
                )
                IconButton(
                    onClick = { amount += 1 },
                    modifier = Modifier.size(48.dp) // Увеличиваем размер кнопки
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add_icon),
                        "add",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp), // Увеличиваем размер иконки
                    )
                }
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },

        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(R.string.cancel))
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation(amount)
                }
            ) {
                Text(stringResource(R.string.apply))
            }
        }
    )
}
