package br.com.michellebrito.financefocus.goal.create.createfirststep.presentation

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentCreateGoalBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateGoalFragment : Fragment(R.layout.fragment_create_goal) {
    private val binding: FragmentCreateGoalBinding by viewBinding()
    private val viewModel: CreateGoalViewModel by viewModel()

    override fun onResume() {
        super.onResume()
        setupListeners()
        subscribeViewModelEvents()
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        ETTitle.doAfterTextChanged { viewModel.onTitleChanged(it.toString()) }
        ETValue.doAfterTextChanged { viewModel.onValueChanged(it.toString()) }
    }

    private fun subscribeViewModelEvents() {
        viewModel.enableButton.observe(this) { result ->
            binding.btnContinue.isEnabled = result
        }
    }
}
