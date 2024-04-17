package br.com.michellebrito.financefocus.goal.list.presentation

import androidx.fragment.app.Fragment
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentListGoalBinding
import br.com.michellebrito.financefocus.goal.list.model.ListGoalItemModel
import by.kirich1409.viewbindingdelegate.viewBinding

class ListGoalFragment : Fragment(R.layout.fragment_list_goal) {
    private val binding: FragmentListGoalBinding by viewBinding()

    override fun onResume() {
        super.onResume()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        rvGoalList.adapter = ListGoalAdapter(
            listOf(
                ListGoalItemModel("TV", "20/07/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
                ListGoalItemModel("TV", "20/07/2022"),
                ListGoalItemModel("Celular", "20/10/2022"),
            )
        )
    }
}
