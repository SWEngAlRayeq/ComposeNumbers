package app.numbers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.numbers.domain.model.NumberFact
import app.numbers.domain.usecase.NumberFactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumbersViewModel @Inject constructor(
    private val getNumberFactUseCase: NumberFactUseCase
) : ViewModel() {

    var numberInput = MutableStateFlow("")
    private val _fact = MutableStateFlow<NumberFact?>(null)
    val fact: StateFlow<NumberFact?> = _fact

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchNumberFact() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _fact.value = getNumberFactUseCase(numberInput.value)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

}