package com.babbel.fallingwords

import android.content.Context
import com.babbel.fallingwords.model.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object Utils {

    fun getWordList(context: Context, fileName: String): List<Word>? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }

        val listWords = object : TypeToken<List<Word>>() {}.type
        return Gson().fromJson(jsonString, listWords)
    }
}