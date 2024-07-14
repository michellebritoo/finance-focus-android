package br.com.michellebrito.financefocus.goal.increment.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.IncrementItemBinding
import br.com.michellebrito.financefocus.goal.increment.domain.ListIncrementItemModel
import br.com.michellebrito.financefocus.goal.increment.presentation.ListIncrementAdapter.IncrementListViewHolder

class ListIncrementAdapter(private val list: List<ListIncrementItemModel>
) : RecyclerView.Adapter<IncrementListViewHolder>() {

    var onItemClick: ((goal: ListIncrementItemModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncrementListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.increment_item, parent, false)

        return IncrementListViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: IncrementListViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    inner class IncrementListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val incrementItem = IncrementItemBinding.bind(itemView)

        fun bindView(item: ListIncrementItemModel) {
            val context = itemView.context

            with(incrementItem) {
                tvIncrementValue.text = context.getString(R.string.monetary_format, String.format("%.2f", item.value))
                if (item.completed) {
                    btnStatus.setImageResource(R.drawable.ic_check_ok)
                } else {
                    btnStatus.setImageResource(R.drawable.ic_check_ok_grey)
                }
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }
}