package com.example.honkaihelper.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.honkaihelper.R

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun toast(context: Context, message: Int) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()