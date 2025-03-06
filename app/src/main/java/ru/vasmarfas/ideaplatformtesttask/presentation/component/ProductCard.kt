package ru.vasmarfas.ideaplatformtesttask.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vasmarfas.ideaplatformtesttask.R
import ru.vasmarfas.ideaplatformtesttask.data.db.ItemsTable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: ItemsTable,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            HeaderRow(
                productName = product.name,
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick
            )
            ChipsRow(productTags = product.tags)
            InfoRow(
                productAmount = product.amount,
                productTime = product.time
            )
        }
    }
}

@Composable
private fun HeaderRow(
    productName: String,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = productName,
            fontSize = 24.sp,
        )
        Row(modifier = Modifier.wrapContentSize()) {
            IconButton(
                onClick = onEditClick,
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    tint = ProjectColors.EditButtonColor,
                    contentDescription = stringResource(R.string.edit_icon)
                )
            }
            IconButton(
                onClick = onDeleteClick,
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    tint = ProjectColors.DeleteButtonColor,
                    contentDescription = stringResource(R.string.delete_icon)
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ChipsRow(productTags: List<String>) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        productTags.forEach { tag ->
            SuggestionChip(
                onClick = { },
                label = { Text(tag) },
                modifier = Modifier.padding(horizontal = 2.dp)
            )
        }
    }
}

@Composable
private fun InfoRow(
    productAmount: Int,
    productTime: Long,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(Modifier.weight(0.5f)) {
            Text(stringResource(R.string.in_stock), fontWeight = FontWeight.Bold)
            Text(if (productAmount > 0) productAmount.toString() else stringResource(R.string.not_in_stock))
        }
        Column(Modifier.weight(0.5f)) {
            Text(stringResource(R.string.add_date), fontWeight = FontWeight.Bold)
            Text(
                SimpleDateFormat(
                    "dd.MM.yyyy", Locale.getDefault()
                ).format(Date(productTime))
            )
        }
    }
}


@Preview
@Composable
private fun ProductCard_Preview() {
    ProductCard(
        product = ItemsTable.mock()
    )
}
