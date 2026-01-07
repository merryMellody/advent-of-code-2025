fun main() {
    fun getLargestJoltage1(bankLine: String): Int {
        var largestJoltage = 0

        for (i in 0 until bankLine.length) {
            val firstChar = bankLine[i]

            for (j in i + 1 until bankLine.length) {
                val secondChar = bankLine[j]

                val newNumber = "$firstChar$secondChar".toInt()
                if (newNumber > largestJoltage) {
                    largestJoltage = newNumber
                }
            }
        }

        return largestJoltage
    }

    fun part1(input: List<String>): Long {
        var joltageTotal: Long = 0

        input.forEach { bankLine ->
            joltageTotal += getLargestJoltage1(bankLine)
        }

        return joltageTotal
    }

    fun getLargestJoltage2(bankLine: String): Long {
        var largestJoltage = ""
        var lastNumberChosen = -1

        while (largestJoltage.length < 12) {
            val numberLeft = 11 - largestJoltage.length
            val leftBound = lastNumberChosen + 1
            val rightBound = bankLine.length - numberLeft

            val numbersInBounds: List<Int> = bankLine.substring(leftBound, rightBound).map { it.digitToInt() }

            val maxInBounds = numbersInBounds.max()
            val maxIndex = numbersInBounds.indexOf(maxInBounds)
            lastNumberChosen += maxIndex + 1

            largestJoltage = "$largestJoltage$maxInBounds"
        }

        return largestJoltage.toLong()
    }

    fun part2(input: List<String>): Long {
        var joltageTotal: Long = 0

        input.forEach { bankLine ->
            joltageTotal += getLargestJoltage2(bankLine)
        }

        return joltageTotal
    }

    val input = readInput("Day03")
//    val testInput = readInput("Day03_test")

    part1(input).println()
    part2(input).println()
}
