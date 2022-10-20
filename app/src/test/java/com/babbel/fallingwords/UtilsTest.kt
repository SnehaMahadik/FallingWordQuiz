package com.babbel.fallingwords

import com.babbel.fallingwords.model.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import java.io.File

class UtilsTest {

    @Test
    fun testGetWordList() {
        val resourceName = "words.json"
        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource(resourceName).file)
        val absolutePath = file.absolutePath
        assertTrue(absolutePath.endsWith("/words.json"))
        val fileContent = this::class.java.classLoader?.getResource("words.json")?.readText()
        val wordsList: List<Word> =
            Gson().fromJson(fileContent, object : TypeToken<List<Word>>() {}.type)
        MatcherAssert.assertThat(wordsList, CoreMatchers.notNullValue())
    }
}