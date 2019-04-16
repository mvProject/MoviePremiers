package com.mvproject.moviepremiers.utils

fun String.parseDate() : String {
    var str = this
    try {
        val date = str.split(" ")
        str = date[0]
    }
    finally {
        return str
    }
}