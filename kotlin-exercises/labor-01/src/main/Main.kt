package main

import kotlin.random.Random

fun main(args: Array<String>) {
    //println("Hello World!")
    //println("Teszt");

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    //println("Program arguments: ${args.joinToString()}")

    println("Task 1: Write a main function that adds two values (immutable variables) and prints the result using a\n" +
            "String template in the following format: 2 + 3 = 5.")
    println(" ");
    addTwoNumbers(2,3);
    println("----------");
    println(" ");

    println("Task 2: Write a main function that declares an immutable list (listOf) daysOfWeek containing the\n" +
            "days of the week.")
    println(" ")
    val daysOfWeek = listOf<String>("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
    printTheWeek(daysOfWeek);

    println(" ");
    printDayStarWithT(daysOfWeek);

    println(" ");
    printDayContainsLetterLittleE(daysOfWeek);

    println("");
    println("Days with length of 6");
    printDayNameWithLenght6(daysOfWeek);
    println("----------");
    println(" ");

    println("Task 3: Write a function that checks whether a number is prime or not. Write a main function that\n" +
            "prints prime numbers within a range.\n")
    var start = 5;
    var end = 15;
    printPrimeNumberInRange(start,end);
    println("----------");
    println(" ");

    println("Task 4: Write an encode and a corresponding decode function that encodes and respectively\n" +
            "decodes the characters of a string.\n");

    val message = "Hello, World!"
    val shift = 3

    val encodedMessage = messageCoding(message, ::encode, shift)
    val decodedMessage = messageCoding(encodedMessage, ::decode, shift)

    println("Original Message: $message")
    println("Encoded Message: $encodedMessage")
    println("Decoded Message: $decodedMessage")
    println("----------");
    println(" ");

    println("Task 5: Write a compact function that prints the even numbers from a list. Use a list filter!\n")
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    printEvenNumbers(numbers);
    println("----------");
    println(" ");

    println("Task 6: The map() performs the same transformation on every list item and returns the result list.\n");
    val doubledNumbers = numbers.map { it * 2 }

    println("Original list: $numbers")
    println("List with doubled elements: $doubledNumbers")

    val capitalizedDaysOfWeek = daysOfWeek.map { it.toUpperCase() }
    println("Capitalized days of week: $capitalizedDaysOfWeek");

    val firstCharsCapitalized = daysOfWeek.map { it.firstOrNull()?.toLowerCase() }
    println("First letters: $firstCharsCapitalized");

    val lengthOfDays = daysOfWeek.map { it.length };
    println("Length of days: $lengthOfDays");

    val averageLength = lengthOfDays.sum().toDouble() / daysOfWeek.size;
    println("Avg. length of days: $averageLength")
    println("----------");
    println(" ");

    println("Task 7: Mutable list\n")

    val mutableDaysOfWeek = daysOfWeek.toMutableList()
    mutableDaysOfWeek.removeIf { it.contains('n') }
    println("Mutable list after removing days with 'n': $mutableDaysOfWeek")
    for ((index, item) in mutableDaysOfWeek.withIndex()) {
        println("Item at $index is $item")
    }
    mutableDaysOfWeek.sort();
    println("Sorted list: $mutableDaysOfWeek");

    println("----------");
    println(" ");

    println("Task 8: Arrays\n");

    println("Array of 10 with 0-100 randoms: ")
    val random = Random.Default
    val randomIntegers = IntArray(10) { random.nextInt(101) } // Generate 10 random integers between 0 and 100
    randomIntegers.forEach { println(it) }
    val ascending = randomIntegers.sorted();
    println("Ascending sorted lis: $ascending")

    if (containsEvenNumber(randomIntegers)){
        println("Contains even(s)");
    } else {
        println("Doesn't contains even(s)");
    }
    if (allEvenNumber(randomIntegers)){
        println("All evens");
    } else {
        println("Not all evens");
    }

    val avgOfRandoms = randomIntegers.sum().toDouble() / randomIntegers.size;
    println("Generated numbers:")
    randomIntegers.forEach { println(it) }
    println("Average of the list: $avgOfRandoms");
}

fun printPrimeNumberInRange(start: Int, end: Int) {
    var run = end - start;
    var number:Int = start;
//    while (run != 0) {
//        if (isPrime(number)) {
//            println("$number is prime");
//        }
//        number++;
//        run--;
//    }
    while (run != 0) {
        if (number in start..end && isPrime(number)) {
            println("$number is prime");
        }
        run--;
        number++;
    }
}

fun isPrime(number: Int): Boolean {
    if (number <= 1) {
        return false
    }
    if (number <= 3) {
        return true
    }
    if (number % 2 == 0 || number % 3 == 0) {
        return false
    }
    var i = 5
    while (i * i <= number) {
        if (number % i == 0 || number % (i + 2) == 0) {
            return false
        }
        i += 6
    }
    return true
}
fun printDayNameWithLenght6(daysOfWeek: List<String>) {
    for ((index, item) in daysOfWeek.withIndex()) {
        when {
            item.length == 6 -> println("${index+1}. $item")
        }
    }
}

fun printDayContainsLetterLittleE(daysOfWeek: List<String>) {
//    for ((index, item) in daysOfWeek.withIndex()) {
//        when {
//            "e" in item -> println("${index+1}. $item")
//        }
//    }
    val daysContainingE = daysOfWeek.filter { it.contains('e') }
    println("Days containing 'e': $daysContainingE")
}

fun printDayStarWithT(daysOfWeek: List<String>) {
//    for ((index, item) in daysOfWeek.withIndex()) {
//        when {
//            "T" in item -> println("${index+1}. $item")
//        }
//    }
    val daysStartingWithT = daysOfWeek.filter { it.startsWith('T') }
    println("Days starting with 'T': $daysStartingWithT")
}

fun addTwoNumbers (number1: Number, number2: Number){
    val sum = number1.toDouble() + number2.toDouble();
    println("The sum is: $sum");
}
fun printTheWeek(daysOfWeek: List<String>) {
    for ((index, item) in daysOfWeek.withIndex()) println("${index+1}. $item")
}

fun encode(input: String, shift: Int): String {
    return input.map { char ->
        if (char.isLetter()) {
            val isLowerCase = char.isLowerCase()
            val base = if (isLowerCase) 'a' else 'A'
            ((char.toInt() - base.toInt() + shift) % 26 + base.toInt()).toChar()
        } else {
            char
        }
    }.joinToString("")
}

fun decode(input: String, shift: Int): String {
    return encode(input, -shift)
}

fun messageCoding(msg: String, func: (String, Int) -> String, shift: Int): String {
    return func(msg, shift)
}

fun printEvenNumbers(numbers: List<Int>) {
    println("Even numbers:");
    val result = numbers.filter { it % 2 == 0 }.forEach { println(it) }

}

fun containsEvenNumber(arr: IntArray): Boolean {
    return arr.any { it % 2 == 0 }
}

fun allEvenNumber(arr: IntArray): Boolean {
    return arr.all { it % 2 == 0 }
}