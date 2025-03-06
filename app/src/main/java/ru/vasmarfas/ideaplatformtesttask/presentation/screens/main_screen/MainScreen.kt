package ru.vasmarfas.ideaplatformtesttask.presentation.screens.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.vasmarfas.ideaplatformtesttask.R
import ru.vasmarfas.ideaplatformtesttask.data.db.ItemsTable
import ru.vasmarfas.ideaplatformtesttask.presentation.component.ProductCard
import ru.vasmarfas.ideaplatformtesttask.presentation.component.ProjectColors
import ru.vasmarfas.ideaplatformtesttask.presentation.screens.main_screen.MainScreenContract.Action
import ru.vasmarfas.ideaplatformtesttask.presentation.ui.DeleteDialog
import ru.vasmarfas.ideaplatformtesttask.presentation.ui.EditDialog

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    Content(
        state = state,
        onEditConfirm = { id, newCount ->
            viewModel.sendAction(Action.UpdateItemCount(id, newCount))
        },
        onDeleteConfirm = { id ->
            viewModel.sendAction(Action.DeleteItem(id))
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    state: MainScreenContract.MainScreenState,
    onEditConfirm: (
        id: Int,
        newCount: Int,
    ) -> Unit,
    onDeleteConfirm: (id: Int) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var inputText by remember { mutableStateOf("") }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var currentEditableItem: ItemsTable? by remember { mutableStateOf(null) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = ProjectColors.TopBarColor,
                    titleContentColor = ProjectColors.TopBarTextColor,
                ),
                title = {
                    Text(
                        stringResource(R.string.products_list),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        label = {
                            Text(
                                text = stringResource(R.string.search_products),
                                textAlign = TextAlign.Start
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Иконка поиска"
                            )
                        },
                        maxLines = 1,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .background(Color.White),
                    )
                }
                items(if (inputText != "") {
                    state.items.filter { item ->
                        item.name.contains(inputText, ignoreCase = true)
                    }
                } else state.items) { product ->
                    ProductCard(product = product, onDeleteClick = {
                        currentEditableItem = product
                        showDeleteDialog = true
                    }, onEditClick = {
                        currentEditableItem = product
                        showEditDialog = true
                    })

                }
            }
            if (showEditDialog) {
                currentEditableItem?.id?.let { id ->
                    EditDialog(
                        onConfirmation = { newAmount ->
                            onEditConfirm(id, newAmount)
                            showEditDialog = false
                        },
                        onDismissRequest = { showEditDialog = false },
                        productAmount = currentEditableItem?.amount ?: 0
                    )
                }

            }
            if (showDeleteDialog) {
                currentEditableItem?.id?.let { id ->
                    DeleteDialog(onConfirmation = {
                        onDeleteConfirm(id)
                        showDeleteDialog = false
                    }, onDismissRequest = { showDeleteDialog = false })
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview
@Composable
private fun MainScreen_Preview() {
    Content(state = MainScreenContract.MainScreenState(items = ItemsTable.mockList()),
        onEditConfirm = { _, _ -> },
        onDeleteConfirm = {})
}