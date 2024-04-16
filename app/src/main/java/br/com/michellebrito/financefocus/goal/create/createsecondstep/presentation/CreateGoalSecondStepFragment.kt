package br.com.michellebrito.financefocus.goal.create.createsecondstep.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentCreateGoalSecondStepBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.redmadrobot.inputmask.MaskedTextChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateGoalSecondStepFragment : Fragment(R.layout.fragment_create_goal_second_step) {
    private val binding: FragmentCreateGoalSecondStepBinding by viewBinding()
    private val viewModel: CreateGoalSecondStepViewModel by viewModel()

    override fun onResume() {
        super.onResume()
        setupListeners()
        setupDateTime()
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
    }


    private fun setupDateTime() = with(binding) {
        etDateInit.apply {
            addTextChangedListener(
                MaskedTextChangedListener("[00]/[00]/[0000]", this)
            )
        }
        etDateFinish.apply {
            addTextChangedListener(
                MaskedTextChangedListener("[00]/[00]/[0000]", this)
            )
        }
    }
}