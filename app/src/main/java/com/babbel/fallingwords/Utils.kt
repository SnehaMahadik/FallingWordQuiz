package com.babbel.fallingwords

import android.content.Context
import android.util.Log
import com.babbel.fallingwords.data.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type
import kotlin.random.Random

object Utils {

    fun getRandomWord(context: Context, fileName: String): Word? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }

        val listWords = object : TypeToken<List<Word>>() {}.type
        val list : List<Word> = Gson().fromJson(jsonString, listWords)
        val randomIndex= Random(System.currentTimeMillis()).nextInt(1, list.size)
        return list[randomIndex]
    }
}