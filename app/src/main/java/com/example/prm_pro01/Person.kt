package com.example.prm_pro01

data class Person( var firstName: String, var lastName:String, var d: Double) {
    var debt: Double


    init {
        this.firstName = firstName
        this.lastName = lastName
        debt = d


    }

    constructor(p: Person): this(p.firstName,p.lastName,p.debt){

    }



}