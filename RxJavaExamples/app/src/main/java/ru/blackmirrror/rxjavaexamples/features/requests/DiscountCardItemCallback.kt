package ru.blackmirrror.rxjavaexamples.features.requests

import androidx.recyclerview.widget.DiffUtil
import ru.blackmirrror.rxjavaexamples.domain.models.DiscountCard

class DiscountCardItemCallback: DiffUtil.ItemCallback<DiscountCard>() {
    override fun areItemsTheSame(oldItem: DiscountCard, newItem: DiscountCard): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DiscountCard, newItem: DiscountCard): Boolean {
        return oldItem == newItem
    }
}