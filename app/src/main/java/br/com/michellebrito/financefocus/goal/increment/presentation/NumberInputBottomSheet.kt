package br.com.michellebrito.financefocus.goal.increment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.ResBottomSheetDifferentValueBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.NumberFormat
import java.util.Locale

class NumberInputBottomSheet(private val onConfirm: (Double) -> Unit) : BottomSheetDialogFragment() {

    private val binding: ResBottomSheetDifferentValueBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.res_bottom_sheet_different_value, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        confirmButton.setOnClickListener { onConfirmButtonClicked() }
        tilDifferentValue.editText?.addTextChangedListener { tilDifferentValue.error = null }
    }

    private fun onConfirmButtonClicked() = with(binding) {
        val inputText = etDifferentValue.text.toString()
        val format = NumberFormat.getInstance(Locale("pt", "BR"))
        val isValidFormat = inputText.matches(Regex("^[0-9]{1,3}(\\.[0-9]{3})*(,[0-9]{2})?\$"))

        val number = try { format.parse(inputText)?.toDouble() } catch (e: Exception) { null }

        if (number != null && isValidFormat) {
            onConfirm(number)
            dismiss()
        } else {
            tilDifferentValue.error = getString(R.string.increment_goal_bottom_sheet_error)
        }
    }
}
