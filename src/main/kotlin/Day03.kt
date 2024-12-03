fun main() {

    fun part1(input: String) {
        val mulRegex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)".toRegex()
        val bracketRegex = "[0-9]{1,3},[0-9]{1,3}".toRegex()

        val result = mulRegex.findAll(input).map { it.value }.toList()
        result
            .map { expr ->
                val res = bracketRegex.find(expr) ?: return@map 1
                val (first, second) = res.value.split(",").map { it.toInt() }
                first * second
            }.sum()
            .println()
    }

    fun part2(input: String) {
        val validExpressionRegex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\)".toRegex()
        val bracketRegex = "[0-9]{1,3},[0-9]{1,3}".toRegex()
        validExpressionRegex.findAll(input)
            .map { it.value }
            .fold(Pair(0, true)) { acc, curr ->
                val (sum, shouldDo) = acc
                when {
                    curr == "don't()" -> sum to false
                    curr == "do()" -> sum to true
                    shouldDo -> {
                        bracketRegex
                            .find(curr)
                            ?.value?.split(",")?.map { it.toInt() }
                            ?.let { (first, second) -> sum + first * second to shouldDo } ?: acc
                    }
                    else -> acc
                }
            }.println()
    }

    val input = readFileAsString("Day03")
    part1(input)
    part2(input)
}