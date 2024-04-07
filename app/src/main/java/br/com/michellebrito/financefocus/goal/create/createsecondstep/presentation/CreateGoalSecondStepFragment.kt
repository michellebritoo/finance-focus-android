package br.com.michellebrito.financefocus.goal.create.createsecondstep.presentation

import androidx.fragment.app.Fragment
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentCreateGoalSecondStepBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateGoalSecondStepFragment : Fragment(R.layout.fragment_create_goal_second_step) {
    private val binding: FragmentCreateGoalSecondStepBinding by viewBinding()
    private val viewModel: CreateGoalSecondStepViewModel by viewModel()

}