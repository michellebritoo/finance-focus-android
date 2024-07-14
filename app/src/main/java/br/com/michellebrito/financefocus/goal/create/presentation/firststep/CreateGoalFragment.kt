package br.com.michellebrito.financefocus.goal.create.presentation.firststep

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentCreateGoalBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
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
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
        btnContinue.setOnClickListener { onContinueButtonClicked() }
        tilTitle.editText?.addTextChangedListener { tilTitle.error = null }
        tilValue.editText?.addTextChangedListener { tilValue.error = null }
    }

    private fun subscribeViewModelEvents() {
        viewModel.viewState.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { state ->
                when (state) {
                    is CreateGoalFirstStepEvent.ShowInputNameError -> showInputTitleError()
                    is CreateGoalFirstStepEvent.ShowInputValueError -> showInputValueError()
                    is CreateGoalFirstStepEvent.GoToNextStep -> goToNextStep()
                }
            }
        }
    }

    private fun showInputTitleError() {
        binding.tilTitle.error = getString(R.string.create_goals_title_error)
    }

    private fun showInputValueError() {
        binding.tilValue.error = getString(R.string.create_goals_value_error)
    }

    private fun onContinueButtonClicked() = with(binding) {
        if (ETTitle.text?.isNotEmpty() == true && ETValue.text?.isNotEmpty() == true) {
            viewModel.onContinueButtonClicked(
                ETTitle.text.toString(),
                ETValue.text.toString().replace("[^\\d,]".toRegex(), "").replace(",", ".").toFloat()
            )
        } else {
            showSnackbarError()
        }
    }

    private fun showSnackbarError() {
        this@CreateGoalFragment.view?.let {
            Snackbar.make(it, R.string.create_goals_generic_input_error, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun goToNextStep() = binding.apply{
        val description = if (ETDescription.text?.isNotEmpty() == true) {
            ETDescription.text
        } else {
            ""
        }
        val value = ETValue.text.toString().replace("[^\\d,]".toRegex(), "").replace(",", ".").toFloat()
        val action = CreateGoalFragmentDirections.createGoalToCreateGoalSecondStep(
            binding.ETTitle.text.toString(),
            description.toString(),
            value
        )
        findNavController().navigate(action)
    }
}
