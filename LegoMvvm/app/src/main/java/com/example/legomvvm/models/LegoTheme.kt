package com.example.legomvvm.models

data class LegoTheme (
    val id : Int,
    val name : String,
    val parentId : Int? = null
)