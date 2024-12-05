fun main() {

    fun applyIfNeeded(rule: Pair<Int, Int>, update: List<Int>): Boolean {
        if (!update.contains(rule.first) || !update.contains(rule.second))
            return true

        return update.indexOf(rule.first) < update.indexOf(rule.second)
    }

    fun isCorrect(rules: List<Pair<Int, Int>>, update: List<Int>) =
        rules.all { applyIfNeeded(it, update) }

    fun part1(rules: List<Pair<Int, Int>>, updates: List<List<Int>>) {
        updates.sumOf {
            if (isCorrect(rules, it)) {
                it[it.size / 2]
            } else 0
        }.println()
    }

    fun part2(rules: List<Pair<Int, Int>>, updates: List<List<Int>>) {
        fun fixOrder(rules: List<Pair<Int, Int>>, update: List<Int>): List<Int> {
            val rulesToApply = rules
                .filter { (first, second) -> update.contains(first) && update.contains(second) }
            var acc = update
            while (!isCorrect(rulesToApply, acc)) {
                rulesToApply.forEach { (first, second) ->
                    val indexFirst = acc.indexOf(first)
                    val indexSecond = acc.indexOf(second)
                    if (indexFirst > indexSecond) {
                        acc = listOf<Int>()
                            .asSequence()
                            .plus(acc.subList(0, indexSecond))
                            .plus(first)
                            .plus(acc.subList(indexSecond + 1, indexFirst))
                            .plus(second)
                            .plus(acc.subList(indexFirst + 1, acc.size))
                            .toList()
                    }
                }
            }
            return acc
        }

        updates.sumOf {
            if (!isCorrect(rules, it)) {
                val fixed = fixOrder(rules, it)
                fixed[fixed.size / 2]
            } else 0
        }.println()
    }

    val input = getLinesFromFile("Day05")
    val rules = input.filter { it.contains("|") }
        .map {
            val (left, right) = it.split("|").map { it.toInt() }
            left to right
        }
    val updates = input.dropWhile { it.contains("|") || it.isBlank() }
        .map {
            it.split(",").map{ it.toInt() }
        }
    part1(rules, updates)
    part2(rules, updates)
}