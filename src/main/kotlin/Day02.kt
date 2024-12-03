fun main() {
    fun isSafe(line: List<Int>): Boolean {
        val windowedDiffs = line
            .windowed(2)
            .map { (first, second) -> first - second }

        return windowedDiffs
            .all { it in 1..3 } || windowedDiffs.all { it in -3..-1 }
    }

    fun part1(input: List<String>) {
        input
            .map { line -> line.split("\\s+".toRegex()).map { it.toInt() }.toList() }
            .count { isSafe(it) }.println()
    }

    fun isTolerated(line: List<Int>): Boolean {
        for (i in line.indices) {
            if (isSafe(line.filterIndexed { index, _ -> index != i }))
                return true
        }

        return false
    }

    fun part2(input: List<String>) {
        input
            .map { line -> line.split("\\s+".toRegex()).map { it.toInt() }.toList() }
            .count { isSafe(it) || isTolerated(it) }.println()
    }

    val input = getLinesFromFile("Day02")
    part1(input)
    part2(input)
}