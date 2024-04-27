package br.com.michellebrito.financefocus.customcomponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.ResCustomDisplayerBinding

class CustomDisplayer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = ResCustomDisplayerBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setupLayout(attrs)
    }

    private fun setupLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.CustomDisplayer
            )

            binding.tvTextDisplayer.text = attributes.getString(R.styleable.CustomDisplayer_text)


            attributes.recycle()
        }
    }
}