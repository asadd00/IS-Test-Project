package com.myubuntu.istestproject.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myubuntu.istestproject.model.Product
import com.myubuntu.istestproject.repository.ProductRepository

class ProductViewModel: ViewModel() {
    private lateinit var productRepo: ProductRepository

    fun init(){
        productRepo = ProductRepository()
    }

    fun getProductList(context: Context): MutableLiveData<Product>{
        return productRepo.getProductList(context)
    }
}