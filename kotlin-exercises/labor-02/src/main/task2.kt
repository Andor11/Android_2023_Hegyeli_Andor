package main

fun String.printMonogram() {
    val monogram = this.split(" ")
        .map { it.first().uppercaseChar() }
        .joinToString("")
    println("Monogram: $monogram")
}

fun List<String>.joinWithSeparator(separator: String): String = joinToString(separator);
fun List<String>.findLongestString(): String? = maxByOrNull { it.length }

fun task2(){
    println("Task2");

    val fullName = "John Doe"
    println("Full Name: $fullName");
    fullName.printMonogram()
    println("-------------\n")

    val list: List<String> = listOf("apple", "pearl", "melon");
    val separator: String = "#"
    val result = list.joinWithSeparator(separator);
    println("The original list: $list \n The new list: $result")
    println("-------------\n")

    println("The original list: $list")
    val longest = list.findLongestString()
    println("Longest: $longest")
    println("-------------\n")


}