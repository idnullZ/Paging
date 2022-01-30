package com.torvald.paging.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Context.showMessage(text:String){

    Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
}

fun loger(value:String){

    Log.d("cjhgidshfbjssdkjcba",value)
}
object Constants {

    const val BASE_URL: String = "https://rickandmortyapi.com/api/"
    const val END_POINT: String = "character"

}