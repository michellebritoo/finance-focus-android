package br.com.michellebrito.financefocus.customcomponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.ResCustomDisplayerBinding

class CustomDisplayer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = ResCustomDisplayerBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setupLayout(attrs)
    }

    fun setText(text: String) {
        binding.tvTextDisplayer.text = text
    }

    private fun setupLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.CustomDisplayer
            )

            binding.tvTextDisplayer.text = attributes.getString(R.styleable.CustomDisplayer_text)

            val isWarning = attributes.getBoolean(R.styleable.CustomDisplayer_typeWarning, false)
            if (isWarning) {
                setBackgroundResource(R.drawable.custom_displayer_warning_bg)
                binding.root.updateLayoutParams {
                    height = context.resources.getDimensionPixelSize(R.dimen.margin_eighty)
                }
            } else {
                setBackgroundResource(R.drawable.custom_displayer_bg)
            }

            attributes.recycle()
        }
    }
}