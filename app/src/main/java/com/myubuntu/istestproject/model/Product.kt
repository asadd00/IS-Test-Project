package com.myubuntu.istestproject.model

class Product(){
    var id: Int = 0
    var name: String = ""
    var description: String = ""
    var price: String = ""
    var itemRate: Double = 0.0

    var list: ArrayList<Product> = ArrayList()
    var hasError = false
    var message = ""

    constructor(id: Int, name: String, description: String, price: String, itemRate: Double):this() {
        this.id = id
        this.name = name
        this.description = description
        this.price = price
        this.itemRate = itemRate
    }
}