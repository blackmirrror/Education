package ru.blackmirrror.rxjavaexamples.features.requests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.blackmirrror.rxjavaexamples.R
import ru.blackmirrror.rxjavaexamples.domain.models.DiscountCard

class DiscountCardAdapter :
    ListAdapter<DiscountCard, DiscountCardAdapter.DiscountCardViewHolder>(DiscountCardItemCallback()) {

    class DiscountCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val shop = itemView.findViewById<TextView>(R.id.tv_shop)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_discount_card, parent, false)
        return DiscountCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiscountCardViewHolder, position: Int) {
        val card = getItem(position)
        holder.shop.text = card.shop
    }
}