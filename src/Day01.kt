fun main() {
    fun parseRotation1(initialPosition: Int, rotation: String): Pair<Int,Int> {
        val direction = rotation.first()
        val numberToMove = rotation.drop(1).toInt()

        var currentPosition = initialPosition
        var numberOfTimesZero = 0

        repeat(numberToMove) {
            when (direction) {
                'R' -> currentPosition++
                'L' -> currentPosition--
            }

            if (currentPosition < 0) {
                currentPosition = 99
            } else if (currentPosition > 99) {
                currentPosition = 0
            }
        }

        if (currentPosition == 0) {
            numberOfTimesZero++
        }

        return Pair(currentPosition, numberOfTimesZero)
    }

    fun part1(input: List<String>): Int {
        var currentPosition = 50
        var numberOfTimesZero = 0

        input.forEach { currentLine ->
            val (nextPosition, additionalTimesZero) = parseRotation1(currentPosition, currentLine)

            currentPosition = nextPosition
            numberOfTimesZero += additionalTimesZero
        }

        return numberOfTimesZero
    }

    fun parseRotation2(initialPosition: Int, rotation: String): Pair<Int,Int> {
        val direction = rotation.first()
        val numberToMove = rotation.drop(1).toInt()

        var currentPosition = initialPosition
        var numberOfTimesZero = 0

        repeat(numberToMove) {
            when (direction) {
                'R' -> currentPosition++
                'L' -> currentPosition--
            }

            if (currentPosition < 0) {
                currentPosition = 99
            } else if (currentPosition > 99) {
                currentPosition = 0
            }

            if (currentPosition == 0) {
                numberOfTimesZero++
            }
        }

        return Pair(currentPosition, numberOfTimesZero)
    }

    fun part2(input: List<String>): Int {
        var currentPosition = 50
        var numberOfTimesZero = 0

        input.forEach { currentLine ->
            val (nextPosition, additionalTimesZero) = parseRotation2(currentPosition, currentLine)

            currentPosition = nextPosition
            numberOfTimesZero += additionalTimesZero
        }

        return numberOfTimesZero
    }

    val input = readInput("Day01")
//    val testInput = readInput("Day01_test")

    val password1 = part1(input)
    println("The first password is: $password1")
    val password2 = part2(input)
    println("The second password is: $password2")
}
