package main

import java.util.*

class ItemController(private val itemService: ItemService) {
    fun quiz(numQuestions: Int) {
        val selectedItems = itemService.selectRandomItems(numQuestions)
        var correctAnswers = 0

        for ((index, item) in selectedItems.withIndex()) {
            println("Question ${index + 1}: ${item.question}")
            println("Options:")
            for ((optionIndex, option) in item.options.withIndex()) {
                println("${getOptionLetter(optionIndex)}) $option")
            }
            val userChoice = readlnOrNull()
            val userChoiceIndex = letterToIndex(userChoice)

            if (userChoiceIndex == item.correctOptionIndex) {
                println("Correct!\n")
                correctAnswers++
            } else {
                println("Incorrect. The correct answer is: ${item.options[item.correctOptionIndex]}\n")
            }
        }

        println("Quiz Results: $correctAnswers/$numQuestions correct answers.")
    }

    private fun getOptionLetter(index: Int): Char {
        return ('a' + index).uppercaseChar()
    }

    private fun letterToIndex(letter: String?): Int {
        if (!letter.isNullOrEmpty()) {
            val uppercaseLetter = letter.uppercase(Locale.getDefault())
            return uppercaseLetter[0] - 'A'
        }
        return -1
    }
}
