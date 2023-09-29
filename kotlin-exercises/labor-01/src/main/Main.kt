package main

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
    println("Days with T start: ");
    printDayStarWithT(daysOfWeek);

    println(" ");
    println("Days contain e letter: ");
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

fun codeString(input: String): String {
    var result: String = input;
   // result = result <<;

    return result;
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
    for ((index, item) in daysOfWeek.withIndex()) {
        when {
            "e" in item -> println("${index+1}. $item")
        }
    }
}

fun printDayStarWithT(daysOfWeek: List<String>) {
    for ((index, item) in daysOfWeek.withIndex()) {
        when {
            "T" in item -> println("${index+1}. $item")
        }
    }
}

fun addTwoNumbers (number1: Number, number2: Number){
    val sum = number1.toDouble() + number2.toDouble();
    println("The sum is: $sum");
}
fun printTheWeek(daysOfWeek: List<String>) {
    for ((index, item) in daysOfWeek.withIndex()) println("${index+1}. $item")
}
