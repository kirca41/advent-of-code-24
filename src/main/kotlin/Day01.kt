import kotlin.math.abs

fun main() {
    fun unzipInput(input: List<String>) =
        input
            .map { line -> line.split("\\s+".toRegex()).map { word -> word.toInt() }.toList() }
            .map { (num1, num2) -> Pair(num1, num2)}
            .unzip()

    fun part1(input: List<String>) {
        val (left, right) = unzipInput(input)

        left.sorted().zip(right.sorted())
            .sumOf { (first, second) -> abs(first - second) }
            .println()
    }

    fun part2(input: List<String>) {
        val (left, right) = unzipInput(input)

        left
            .sumOf { leftNum -> leftNum * right.count { it == leftNum } }
            .println()
    }

    val input = getLinesFromFile("Day01")
    part1(input)
    part2(input)
}