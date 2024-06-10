package br.com.michellebrito.financefocus.login.data

import br.com.michellebrito.financefocus.common.domain.PreferenceStorage
import br.com.michellebrito.financefocus.login.domain.LoginRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginRepositoryImpl(private val preferenceStorage: PreferenceStorage) : LoginRepository {
    private var auth: FirebaseAuth = Firebase.auth

    override fun authWithEmailAndPassword(
        email: String,
        password: String,
        callback: (Boolean) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    storeLoggedInfoIntoStorage(task)
                }
                callback(task.isSuccessful)
            }
    }

    private fun storeLoggedInfoIntoStorage(task: Task<AuthResult>) {
        preferenceStorage.putBoolean(
            IS_LOGGED.plus(task.result.user?.uid),
            task.isSuccessful
        )
        task.result.user?.let { preferenceStorage.putString(CURRENT_USER, it.uid) }
    }

    private companion object {
        const val IS_LOGGED = "is_logged"
        const val CURRENT_USER = "current_user"
    }
}
