package br.com.michellebrito.financefocus.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.michellebrito.financefocus.home.domain.HomeRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository): ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth

    fun refreshDeviceToken(token: String) {
        auth.currentUser?.getIdToken(true)?.addOnCompleteListener {
            val authorization = it.result.token.toString()
            repository.setAuthorizationToken(authorization)
            viewModelScope.launch {
                repository.sendDeviceToken(authorization, token)
            }
        }
    }
}
