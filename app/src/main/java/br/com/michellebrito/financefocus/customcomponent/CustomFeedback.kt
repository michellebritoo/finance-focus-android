package br.com.michellebrito.financefocus.customcomponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.ResCustomFeedbackBinding

class CustomFeedback @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = ResCustomFeedbackBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setLayout(attrs)
    }

    fun setTitle(text: String) {
        binding.tvTitle.text = text
    }

    fun setDescription(text: String) {
        binding.tvDescription.text = text
    }

    fun setButton(text: String) {
        binding.btnFeedback.setText(text)
    }

    fun setOnButtonClickListener(listener: () -> Unit) {
        binding.btnFeedback.setOnClickListener { listener() }
        binding.topBar.setOnClickListener { listener() }
    }

    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.CustomFeedback
            )

            binding.tvTitle.text = attributes.getString(R.styleable.CustomFeedback_fbtitle)
            binding.tvDescription.text = attributes.getString(R.styleable.CustomFeedback_description)
            binding.btnFeedback.setText(attributes.getString(R.styleable.CustomFeedback_button) ?: "")

            attributes.recycle()
        }
    }
}
