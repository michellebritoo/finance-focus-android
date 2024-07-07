package br.com.michellebrito.financefocus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import br.com.michellebrito.financefocus.databinding.ActivityMainBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showLoading() {
        binding.progressCircular.apply {
            isVisible = true
            isIndeterminate = true
        }
    }

    fun hideLoading() {
        binding.progressCircular.apply {
            isVisible = false
            isIndeterminate = false
        }
    }

    fun getDeviceToken(callback: (String) -> Unit) {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(task.result.toString())
                }
            }
    }
}
