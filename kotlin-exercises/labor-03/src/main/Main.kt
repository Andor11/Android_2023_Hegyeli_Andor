package main

fun main() {
    val itemRepository = ItemRepository()
    val itemService = ItemService(itemRepository)
    val itemController = ItemController(itemService)

    println("Welcome to the Quiz App!")

    var numQuestions = 1

    while (numQuestions != 0) {
        println("Enter the number of questions you want (in 1-${itemRepository.size()} range, 0 to exit): ")
        numQuestions = readlnOrNull()?.toIntOrNull() ?: 0

        if (numQuestions !in 1..itemRepository.size()){
            if (numQuestions == 0){
                break;
            }
            println(" $numQuestions is invalid");
            continue;
        }

        if (numQuestions > 0) {
            itemController.quiz(numQuestions)
        } else if (numQuestions < 0) {
            println("Invalid input. Please enter a non-negative number of questions.")
        }
    }

    println("Thank you for using the Quiz App!")
}
