package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.Category
import com.example.data.CraftRole
import com.example.data.QuestionBank
import com.example.data.ReferenceData
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
    fun `question bank contains 120 questions across all categories`() {
        val questions = QuestionBank.allQuestions
        assertEquals(120, questions.size)

        Category.entries.forEach { category ->
            val catQuestions = QuestionBank.getQuestionsByCategory(category)
            assertEquals("Expected 20 questions for category $category", 20, catQuestions.size)
        }
    }

    @Test
    fun `question bank covers all postal crafts`() {
        CraftRole.entries.forEach { craft ->
            val craftQuestions = QuestionBank.getQuestionsForCraft(craft)
            assertTrue("Expected questions for craft $craft", craftQuestions.isNotEmpty())
        }
    }

    @Test
    fun `reference data contains 4 loto drill scenarios and enriched logic gates`() {
        val scenarios = ReferenceData.lotoDrillScenarios
        assertEquals(4, scenarios.size)

        val gates = ReferenceData.logicGates
        assertTrue(gates.any { it.logicTag == "AND" && it.alternativeSymbols.contains("&") })
        assertTrue(gates.any { it.logicTag == "OR" && it.alternativeSymbols.contains("||") })
        assertTrue(gates.any { it.logicTag == "NOT" && it.alternativeSymbols.contains("!") })
        assertTrue(gates.any { it.logicTag == "NOT EQUAL" && it.alternativeSymbols.contains("!=") })
    }
}
