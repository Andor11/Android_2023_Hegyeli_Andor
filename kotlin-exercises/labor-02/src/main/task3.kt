package main

import java.time.DateTimeException
import kotlin.random.Random

fun task3(){
    val validDates = mutableListOf<Date>()

    while (validDates.size < 10) {
        val year = Random.nextInt(2000, 2100+1)
        val month = Random.nextInt(1, 20+1)
        val day = Random.nextInt(1, 40+1)

        try {
            val generatedDate = Date(year, month, day)
            if (generatedDate.isValidDate()) {
                validDates.add(generatedDate)
            } else {
                println("Invalid date: ${generatedDate.year}-${generatedDate.month}-${generatedDate.day}")
            }
        } catch (e: DateTimeException) {
            println("Error while creating date: $e")
        }
    }

    //println("Valid dates: $validDates")
    println("\nValid dates:")
    validDates.forEachIndexed { index, date ->
        println("${index+1}. ${date.year}-${date.month}-${date.day}")
    }

    validDates.sort()

    println("\nSorted dates:")
    validDates.forEachIndexed { index, date ->
        println("${index+1}. is ${date.year}-${date.month}-${date.day}")
    }

    val reversedDates = validDates.reversed()

    println("\nReversed dates:")
    reversedDates.forEach { date ->
        println(" ${date.year}-${date.month}-${date.day}")
    }

    validDates.sortWith(dateComparator)
    println("\nCustom Sorted dates:")
    validDates.forEachIndexed { index, date ->
        println("${index+1}. ${date.year}-${date.month}-${date.day}")
    }

}