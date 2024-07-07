package br.com.michellebrito.financefocus.welcome.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.welcome.domain.WelcomeRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class WelcomeViewModel(private val repository: WelcomeRepository): ViewModel() {
    private val _isUserAuthenticated = MutableLiveData<Boolean>()
    val isUserAuthenticated: LiveData<Boolean> get() = _isUserAuthenticated
    private var auth: FirebaseAuth = Firebase.auth

    init {
        storeAuthorization()
    }

    private fun storeAuthorization() {
        auth.currentUser?.getIdToken(true)?.addOnCompleteListener {
            repository.setAuthorizationToken(it.result.token.toString())
        }
    }

    fun getAuthInfo() {
        viewModelScope.launch {
            _isUserAuthenticated.value = repository.isLoggedUser()
        }
    }

    fun refreshDeviceToken(token: String) {
        viewModelScope.launch {
            repository.sendDeviceToken(token)
        }
    }
}
