import kotlin.math.abs
import kotlin.math.sqrt

fun main() {
    fun parseAllPairs(input: String): List<Pair<Long, Long>> {
        val stringPairs = input.split(",")
        val allPairs = stringPairs.map { stringPair ->
            val twoStrings = stringPair.split("-").map { it.toLong() }

            Pair(twoStrings.first(), twoStrings.last())
        }

        return allPairs
    }

    fun isNumberValid1(number: Long): Boolean {
        val numberString = number.toString()
        val numberStringLen = numberString.length

        if (numberStringLen % 2 != 0) return true

        val firstHalf = numberString.substring(0, numberStringLen / 2)
        val secondHalf = numberString.substring(numberStringLen / 2)

        return firstHalf != secondHalf
    }

    fun part1(input: List<String>): Long {
        val inputLine = input.first()
        val allPairs = parseAllPairs(inputLine)

        var invalidTotal: Long = 0

        allPairs.forEach { currentPair ->
            var counter = currentPair.first

            while (counter <= currentPair.second) {
                val currentNumber = counter

                if (!isNumberValid1(currentNumber)) {
                    invalidTotal += currentNumber
                }

                counter += 1
            }
        }

        return invalidTotal
    }

    fun getAllLengthFactors(number: Long): List<Int> {
        val numberLength: Int = number.toString().length

        if (numberLength == 0) return listOf(0)
        val factors = mutableSetOf<Int>()
        val absNumber = abs(numberLength)

        // Iterate from 1 up to the square root of the absolute number
        for (i in 1..sqrt(absNumber.toDouble()).toInt()) {
            if (absNumber % i == 0) {
                // Add the current divisor
                factors.add(i)
                // Add the complementary divisor (number / i)
                factors.add(absNumber / i)
            }
        }

        // We don't return 1 in this case since we don't use it for the problem
        return factors.filter { it != 1 }.sorted()
    }

    fun isNumberValid2(number: Long): Boolean {
        val lengthFactors = getAllLengthFactors(number)

        val numberString = number.toString()
        val numberStringLen = numberString.length

        var numberIsValid = true

        lengthFactors.forEach { currentFactor ->
            val substringLength = numberStringLen / currentFactor
            val substringList = mutableListOf<String>()

            for (i in 1..currentFactor) {
                val substringStart = (i - 1) * substringLength
                val substringEnd = i * substringLength

                substringList.add(numberString.substring(substringStart, substringEnd))
            }

            if (substringList.distinct().size == 1) {
                numberIsValid = false
            }
        }

        return numberIsValid
    }

    fun part2(input: List<String>): Long {
        val inputLine = input.first()
        val allPairs = parseAllPairs(inputLine)

        var invalidTotal: Long = 0

        allPairs.forEach { currentPair ->
            var counter = currentPair.first

            while (counter <= currentPair.second) {
                val currentNumber = counter

                if (!isNumberValid2(currentNumber)) {
                    invalidTotal += currentNumber
                }

                counter += 1
            }
        }

        return invalidTotal
    }

    val input = readInput("Day02")
//    val testInput = readInput("Day02_test")

    part1(input).println()
    part2(input).println()
}
