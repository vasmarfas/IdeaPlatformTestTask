package ru.vasmarfas.ideaplatformtesttask.presentation.screens.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.vasmarfas.ideaplatformtesttask.data.MainRepositoryInterface
import ru.vasmarfas.ideaplatformtesttask.presentation.screens.main_screen.MainScreenContract.Action
import ru.vasmarfas.ideaplatformtesttask.presentation.screens.main_screen.MainScreenContract.MainScreenState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepositoryInterface) :
    ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    private val _action = MutableSharedFlow<Action>()
    private val action: SharedFlow<Action> = _action.asSharedFlow()

    init {
        loadItems()
        handleAction()
    }

    fun sendAction(action: Action) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }

    private fun loadItems() {
        viewModelScope.launch {
            repository.getItemsFlow().collectLatest { items ->
                _state.emit(_state.value.copy(items = items, isLoading = false))
            }
        }
    }

    private fun handleAction() {
        viewModelScope.launch {
            action.collectLatest {
                when (it) {
                    is Action.UpdateItemCount -> updateItemCountById(it.id, it.newCount)
                    is Action.DeleteItem -> deleteItemById(it.id)
                }
            }
        }
    }

    private fun updateItemCountById(id: Int, newCount: Int) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(isLoading = true))
            delay(1000) // Imitation of long request for demonstration of loading indicator
            repository.updateAmountById(id, newCount)
        }
    }

    private fun deleteItemById(id: Int) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(isLoading = true))
            delay(1000) // Imitation of long request for demonstration of loading indicator
            repository.deleteItemById(id)
        }
    }
}
