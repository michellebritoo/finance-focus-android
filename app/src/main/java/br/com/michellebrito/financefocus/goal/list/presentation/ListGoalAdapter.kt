package br.com.michellebrito.financefocus.goal.list.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.GoalItemBinding
import br.com.michellebrito.financefocus.goal.list.model.ListGoalItemModel
import br.com.michellebrito.financefocus.goal.list.presentation.ListGoalAdapter.GoalListViewHolder

class ListGoalAdapter(
    private val list: List<ListGoalItemModel>
) : Adapter<GoalListViewHolder>() {

    var onItemClick: ((goal: ListGoalItemModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.goal_item, parent, false)

        return GoalListViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: GoalListViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    inner class GoalListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val goalItem = GoalItemBinding.bind(itemView)

        fun bindView(goal: ListGoalItemModel) {
            with(goalItem) {
                tvGoalTitle.text = goal.title
                tvGoalDate.text = goal.date
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(goal)
            }
        }
    }
}