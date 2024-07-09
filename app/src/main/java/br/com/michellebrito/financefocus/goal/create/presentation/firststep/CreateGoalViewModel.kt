package br.com.michellebrito.financefocus.goal.create.presentation.firststep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateGoalViewModel: ViewModel() {
    val enableButton: LiveData<Boolean> get() = _enableButton
    private val _enableButton = MutableLiveData<Boolean>()

    private var title: String = ""
    private var value: String = ""

    fun onTitleChanged(value: String) {
        this.title = value
        validateInputs()
    }

    fun onValueChanged(value: String) {
        this.value = value
        validateInputs()
    }

    private fun validateInputs() {
        _enableButton.value = title.isNotBlank() && value.isNotBlank()
    }
}
