fun main() {
    fun operate(charOperator: String, a: Long, b: Long): Long {
        return when (charOperator) {
            "+" -> a + b
            "*" -> a * b
            else -> throw IllegalArgumentException("Unsupported operator: $charOperator")
        }
    }

    fun part1(input: List<String>): Long {
        val operandColumns = mutableListOf<List<String>>()

        for (i in 0 until input.size - 1) {
            val line = input[i].trim().split("\\s+".toRegex())

            operandColumns.add(line)
        }

        val operatorRow = input[input.size - 1].trim().split("\\s+".toRegex())

        val rowSize = operatorRow.size

        var grandTotal: Long = 0

        for (problemNumber in 0 until rowSize) {
            val currentOperator = operatorRow[problemNumber]
            val problemOperands = mutableListOf<Long>()

            for (i in 0 until operandColumns.size) {
                val currentOperand = operandColumns[i][problemNumber]

                problemOperands.add(currentOperand.toLong())
            }

            val problemTotal = problemOperands.reduce { acc, operand -> operate(currentOperator, acc, operand) }

            grandTotal += problemTotal
        }

        return grandTotal
    }

    fun part2(input: List<String>): Long {
        val columnSize = input.size
        val rowSize = input[0].length

        val operatorRow = input[input.size - 1].trim().split("\\s+".toRegex()).asReversed()
        val allOperands = mutableListOf<List<Long>>()

        var currentProblemNumber = 0
        var currentProblemOperands = mutableListOf<Long>()

        for (colIdx in rowSize - 1 downTo 0) {
            var operandString = ""

            for (rowIdx in 0 until columnSize - 1) {
                val currentChar = input[rowIdx][colIdx]
                operandString = "$operandString${currentChar}"
            }

            if (operandString.isBlank()) {
                currentProblemNumber += 1
                allOperands.add(currentProblemOperands)
                currentProblemOperands = mutableListOf()
            } else if (colIdx == 0) {
                currentProblemOperands.add(operandString.trim().toLong())
                allOperands.add(currentProblemOperands)
            } else {
                currentProblemOperands.add(operandString.trim().toLong())
            }
        }

        var grandTotal: Long = 0

        for (i in 0 until allOperands.size) {
            val currentTotal = allOperands[i].reduce { acc, operand -> operate(operatorRow[i], acc, operand) }

            grandTotal += currentTotal
        }

        return grandTotal
    }

    val input = readInput("Day06")
//    val testInput = readInput("Day06_test")

    part1(input).println()
    part2(input).println()
}
