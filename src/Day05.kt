fun main() {
    fun buildIngredientLists(input: List<String>): Pair<List<LongRange>,List<Long>> {
        val ingredientNumberRanges = mutableListOf<LongRange>()
        val ingredientIdList = mutableListOf<Long>()

        var foundBlankLine = false

        input.forEach { currentLine ->
            if (currentLine == "") {
                foundBlankLine = true
            } else if (!foundBlankLine) {
                val (start, end) = currentLine.split("-").map { it.toLong() }
                val numberRange = start..end

                ingredientNumberRanges.add(numberRange)
            } else {
                val currentNumber = currentLine.toLong()

                ingredientIdList.add(currentNumber)
            }
        }

        return Pair(ingredientNumberRanges, ingredientIdList)
    }

    fun part1(input: List<String>): Int {
        val (ingredientNumberRanges, ingredientIdList) = buildIngredientLists(input)

        var freshIngredientsCount = 0

        ingredientIdList.forEach { currentIngredientId ->
            val isFresh = ingredientNumberRanges.any { range -> currentIngredientId in range }
            if (isFresh) {
                freshIngredientsCount += 1
            }
        }

        return freshIngredientsCount
    }

    fun mergeRanges(ranges: List<LongRange>): List<LongRange> {
        if (ranges.isEmpty()) return emptyList()

        // 1. Sort by the start of the range
        val sortedRanges = ranges.sortedBy { it.first }
        val merged = mutableListOf<LongRange>()

        var currentStart = sortedRanges[0].first
        var currentEnd = sortedRanges[0].last

        for (i in 1 until sortedRanges.size) {
            val nextRange = sortedRanges[i]

            // 2. Check for overlap (or adjacency, e.g., 1..5 and 6..10)
            // If next start is <= currentEnd + 1, they are contiguous or overlapping
            if (nextRange.first <= currentEnd + 1) {
                currentEnd = maxOf(currentEnd, nextRange.last)
            } else {
                // 3. No overlap, push the finished range and start a new one
                merged.add(currentStart..currentEnd)
                currentStart = nextRange.first
                currentEnd = nextRange.last
            }
        }

        merged.add(currentStart..currentEnd)
        return merged
    }

    fun part2(input: List<String>): Long {
        val (ingredientNumberRanges, _) = buildIngredientLists(input)

        val mergedIngredientNumberRanges = mergeRanges(ingredientNumberRanges)

        var freshIngredientsCount: Long = 0

        mergedIngredientNumberRanges.forEach { numberRange ->
            val longCount = numberRange.last - numberRange.first + 1L

            freshIngredientsCount += longCount
        }

        return freshIngredientsCount
    }

    val input = readInput("Day05")
//    val testInput = readInput("Day05_test")

    part1(input).println()
    part2(input).println()
}
