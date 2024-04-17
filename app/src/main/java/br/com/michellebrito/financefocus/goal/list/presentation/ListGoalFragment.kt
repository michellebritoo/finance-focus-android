package br.com.michellebrito.financefocus.goal.list.presentation

import androidx.fragment.app.Fragment
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentListGoalBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class ListGoalFragment : Fragment(R.layout.fragment_list_goal) {
    private val binding: FragmentListGoalBinding by viewBinding()

    override fun onResume() {
        super.onResume()
    }

    private fun setupListeners() = with(binding) {

    }
}
