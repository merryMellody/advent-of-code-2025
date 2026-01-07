fun main() {
    fun isRollAccessible(input: List<String>, rowCoordinate: Int, colCoordinate: Int): Boolean {
        if (input[rowCoordinate][colCoordinate] == '.') return false

        val rowMax = input.size
        val colMax = input[0].length

        var adjacentRolls = 0

        for (i in rowCoordinate - 1..rowCoordinate + 1) {
            for (j in colCoordinate - 1..colCoordinate + 1) {
                if (i >= 0 && j >= 0 && i < rowMax && j < colMax) {
                    if (i != rowCoordinate || j != colCoordinate) {
                        if (input[i][j] == '@') {
                            adjacentRolls += 1
                        }
                    }
                }
            }
        }

        return adjacentRolls < 4
    }

    fun part1(input: List<String>): Int {
        var accessibleRolls = 0

        for (i in 0 until input.size) {
            for (j in 0 until input[i].length) {
                if (isRollAccessible(input, i, j)) {
                    accessibleRolls += 1
                }
            }
        }

        return accessibleRolls
    }

    fun part2(input: List<String>): Int {
        var previousRollMap = input
        var newRollMap = mutableListOf<String>()

        var hasMoreAccessibleRows = true
        var finalAccessibleRolls = 0

        while(hasMoreAccessibleRows) {
            var accessibleRolls = 0

            for (i in 0 until previousRollMap.size) {
                var newMapRow = ""

                for (j in 0 until previousRollMap[i].length) {
                    if (isRollAccessible(previousRollMap, i, j)) {
                        accessibleRolls += 1
                        newMapRow += 'x'
                    } else {
                        newMapRow += previousRollMap[i][j]
                    }
                }

                newRollMap.add(newMapRow)
            }

            hasMoreAccessibleRows = previousRollMap != newRollMap

            previousRollMap = newRollMap
            newRollMap = mutableListOf()

            if (!hasMoreAccessibleRows) {
                finalAccessibleRolls = accessibleRolls
            }
        }

        return finalAccessibleRolls
    }

    val input = readInput("Day04")
//    val testInput = readInput("Day04_test")

    part1(input).println()
    part2(input).println()
}
