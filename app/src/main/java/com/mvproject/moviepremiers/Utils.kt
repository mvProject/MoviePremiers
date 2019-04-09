package com.mvproject.moviepremiers

import java.text.DecimalFormat
import java.util.*

fun getCurrentYear() = Calendar.getInstance().get(Calendar.YEAR).toString()

fun getCurrentMonth() = DecimalFormat("00").format(Calendar.getInstance().get(Calendar.MONTH)+1)!!

fun getCurrentDay() = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)