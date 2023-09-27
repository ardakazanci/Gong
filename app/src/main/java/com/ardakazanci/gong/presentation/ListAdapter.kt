package com.ardakazanci.gong.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ardakazanci.gong.R
import com.ardakazanci.gong.core.presentation.setOnThrottledClickListener
import com.ardakazanci.gong.databinding.ItemListBinding
import com.ardakazanci.gong.domain.SatelliteListDomainModel

class ListAdapter(private val onClick: (String,String) -> Unit) :
    ListAdapter<SatelliteListDomainModel.SatelliteListDomainModelItem, ListViewHolder>(
        ListDiffCallBack()
    ) {

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.from(parent)
    }
}

class ListDiffCallBack :
    DiffUtil.ItemCallback<SatelliteListDomainModel.SatelliteListDomainModelItem>() {

    override fun areItemsTheSame(
        oldItem: SatelliteListDomainModel.SatelliteListDomainModelItem,
        newItem: SatelliteListDomainModel.SatelliteListDomainModelItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: SatelliteListDomainModel.SatelliteListDomainModelItem,
        newItem: SatelliteListDomainModel.SatelliteListDomainModelItem
    ): Boolean {
        return oldItem == newItem
    }
}

class ListViewHolder(
    private val binding: ItemListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        data: SatelliteListDomainModel.SatelliteListDomainModelItem,
        onClick: (String,String) -> Unit
    ) {
        with(binding) {
            root.setOnThrottledClickListener { onClick.invoke(data.id.toString(),data.name) }
            with(binding) {
                title.text = data.name
                takeIf { data.active }?.run {
                    flag.setImageDrawable(
                        AppCompatResources.getDrawable(
                            root.context,
                            R.drawable.green_shape
                        )
                    )
                    desc.text = "Active"
                }
                takeUnless { data.active }?.run {
                    flag.setImageDrawable(
                        AppCompatResources.getDrawable(
                            root.context,
                            R.drawable.red_shape
                        )
                    )
                    desc.text = "Passive"
                }


            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): ListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemListBinding.inflate(layoutInflater, parent, false)
            return ListViewHolder(binding)
        }
    }
}
