package com.git.williamgdev.fr.ui.navigator

import com.git.williamgdev.fr.data.ItemDTO

interface ItemNavigator {
    fun showError(throwable: Throwable?)
    fun displayItemDetail(itemsDTO: List<ItemDTO>?)
}
