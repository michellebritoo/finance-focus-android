package br.com.michellebrito.financefocus.customcomponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.ResCustomButtonBinding

class CustomButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var title: String = ""

    private val binding = ResCustomButtonBinding.inflate(LayoutInflater.from(context), this, true)

    private var state: CustomButtonState = CustomButtonState.Enable
        set(value) {
            field = value
            refreshState()
        }

    init {
        setLayout(attrs)
    }

    fun enableButton() {
        state = CustomButtonState.Enable
    }

    fun disableButton() {
        state = CustomButtonState.Disable
    }

    fun setText(text: String) {
        binding.tvTitleButton.text = text
    }

    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.CustomButton
            )

            val titleResId = attributes.getResourceId(R.styleable.CustomButton_title, 0)
            if (titleResId != 0) {
                title = context.getString(titleResId)
            }

            val typeNormal = attributes.getBoolean(R.styleable.CustomButton_typeNormal, true)
            if (typeNormal) {
                setBackgroundResource(R.drawable.custom_button_background)
                binding.tvTitleButton.setTextColor(context.getColor(R.color.white))
            } else {
                setBackgroundResource(R.drawable.custom_button_background_white)
                binding.tvTitleButton.setTextColor(context.getColor(R.color.primary))
            }

            val isEnabled = attributes.getBoolean(R.styleable.CustomButton_isEnabled, true)
            if (isEnabled) {
                enableButton()
            } else {
                disableButton()
            }

            attributes.recycle()
        }
    }

    private fun refreshState() {
        isEnabled = state.isEnabled
        isClickable = state.isEnabled
        binding.tvTitleButton.text = title
        refreshDrawableState()
    }

    sealed class CustomButtonState(val isEnabled: Boolean) {
        data object Enable : CustomButtonState(isEnabled = true)
        data object Disable : CustomButtonState(isEnabled = false)
    }
}