package iii_conventions

import kotlin.comparisons.compareValuesBy

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return compareValuesBy(this, other, MyDate::year, MyDate::month, MyDate::dayOfMonth)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = object : Iterator<MyDate> {
        var currentDate = start

        override fun hasNext(): Boolean {
            return currentDate <= endInclusive
        }

        override fun next(): MyDate {
            val returnDate = currentDate
            currentDate = currentDate.nextDay()
            return returnDate
        }
    }
}
