package com.example.onlineshop.extensions

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenStarted {
        this@launchWhenStarted.collect()
    }
}

fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
    val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
    val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return layoutManager.getPosition(snapView)
}

fun String.addToEndPrice(itemPrice: String): String {
    return (this.toDouble() + itemPrice.toDouble()).toString()
}

fun String.removeFromEndPrice(itemPrice: String): String {
    return if (this.toDouble() - itemPrice.toDouble() >= 0) {
        (this.toDouble() - itemPrice.toDouble()).toString()
    } else {
        this
    }
}