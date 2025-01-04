package com.example.firstappjetpackcompose

data class Person(
    val name: String,
    val salary: Int,
    val phone: String
){
    override fun toString(): String {
        return "$"
    }
}