package com.example.simpleshoppinglist.presentation


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleshoppinglist.data.ShopListRepositoryImpl
import com.example.simpleshoppinglist.domain.EditShopItemUseCase
import com.example.simpleshoppinglist.domain.GetShopListUseCase
import com.example.simpleshoppinglist.domain.RemoveShopItemUseCase
import com.example.simpleshoppinglist.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun removeShopItem(shopItem: ShopItem) {
        removeShopItemUseCase.removeShopItem(shopItem)
        getShopList()
    }

    fun changeEnabledState(shopItem: ShopItem) {
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newShopItem)
        Log.d("MainActivityTest", newShopItem.toString())
        getShopList()
    }

}