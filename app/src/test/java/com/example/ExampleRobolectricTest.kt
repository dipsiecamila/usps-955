package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.Category
import com.example.data.CraftRole
import com.example.data.QuestionBank
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("USPS 955", appName)
    }

    @Test
    fun `question bank contains questions for all categories`() {
        val questions = QuestionBank.allQuestions
        assertTrue(questions.isNotEmpty())

        Category.entries.forEach { category ->
            val catQuestions = QuestionBank.getQuestionsByCategory(category)
            assertTrue("Expected questions for category $category", catQuestions.isNotEmpty())
        }
    }

    @Test
    fun `question bank covers all postal crafts`() {
        CraftRole.entries.forEach { craft ->
            val craftQuestions = QuestionBank.getQuestionsForCraft(craft)
            assertTrue("Expected questions for craft $craft", craftQuestions.isNotEmpty())
        }
    }
}
