package com.example.tanorami.utils

import android.content.Context
import android.widget.Toast

fun toast(context: Context, message: Int) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()