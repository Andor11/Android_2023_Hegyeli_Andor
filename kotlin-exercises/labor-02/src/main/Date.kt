package main
import java.time.LocalDate

fun Date.isLeapYear(): Boolean {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
}

fun Date.isValidDate(): Boolean {
    return try {
        LocalDate.of(year, month, day)
        true
    } catch (e: Exception) {
        false
    }
}

val dateComparator = Comparator<Date> { date1, date2 ->
    if (date1.year != date2.year) {
        date1.year - date2.year
    } else if (date1.month != date2.month) {
        date1.month - date2.month
    } else {
        date1.day - date2.day
    }
}

data class Date(
    val year: Int = LocalDate.now().year,
    val month: Int = LocalDate.now().monthValue,
    val day: Int = LocalDate.now().dayOfMonth): Comparable<Date> {
    override fun compareTo(other: Date): Int {
        if (year != other.year) {
            return year - other.year
        }
        if (month != other.month) {
            return month - other.month
        }
        return day - other.day
    }
}

