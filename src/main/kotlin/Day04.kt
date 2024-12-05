fun main() {

    fun part1(input: List<List<Char>>) {
        fun checkLeft(input: List<List<Char>>, i: Int, j: Int) =
            j >= 3
                    && input[i][(j - 1).coerceAtLeast(0)] == 'M'
                    && input[i][(j - 2).coerceAtLeast(0)] == 'A'
                    && input[i][(j - 3).coerceAtLeast(0)] == 'S'

        fun checkTopLeft(input: List<List<Char>>, i: Int, j: Int) =
            i >= 3 && j >= 3
                    && input[(i - 1).coerceAtLeast(0)][(j - 1).coerceAtLeast(0)] == 'M'
                    && input[(i - 2).coerceAtLeast(0)][(j - 2).coerceAtLeast(0)] == 'A'
                    && input[(i - 3).coerceAtLeast(0)][(j - 3).coerceAtLeast(0)] == 'S'

        fun checkTop(input: List<List<Char>>, i: Int, j: Int) =
            i >= 3
                    && input[(i - 1).coerceAtLeast(0)][j] == 'M'
                    && input[(i - 2).coerceAtLeast(0)][j] == 'A'
                    && input[(i - 3).coerceAtLeast(0)][j] == 'S'

        fun checkTopRight(input: List<List<Char>>, i: Int, j: Int) =
            i >= 3 && j < input.first().size - 3
                    && input[(i - 1).coerceAtLeast(0)][(j + 1).coerceAtMost(input.first().size - 1)] == 'M'
                    && input[(i - 2).coerceAtLeast(0)][(j + 2).coerceAtMost(input.first().size - 1)] == 'A'
                    && input[(i - 3).coerceAtLeast(0)][(j + 3).coerceAtMost(input.first().size - 1)] == 'S'

        fun checkRight(input: List<List<Char>>, i: Int, j: Int) =
            j < input.first().size - 3
                    && input[i][(j + 1).coerceAtMost(input.first().size - 1)] == 'M'
                    && input[i][(j + 2).coerceAtMost(input.first().size - 1)] == 'A'
                    && input[i][(j + 3).coerceAtMost(input.first().size - 1)] == 'S'

        fun checkBottomRight(input: List<List<Char>>, i: Int, j: Int) =
            i < input.size - 3 && j < input.first().size - 3
                    && input[(i + 1).coerceAtMost(input.size - 1)][(j + 1).coerceAtMost(input.first().size - 1)] == 'M'
                    && input[(i + 2).coerceAtMost(input.size - 1)][(j + 2).coerceAtMost(input.first().size - 1)] == 'A'
                    && input[(i + 3).coerceAtMost(input.size - 1)][(j + 3).coerceAtMost(input.first().size - 1)] == 'S'

        fun checkBottom(input: List<List<Char>>, i: Int, j: Int) =
            i < input.size - 3
                    && input[(i + 1).coerceAtMost(input.size - 1)][j] == 'M'
                    && input[(i + 2).coerceAtMost(input.size - 1)][j] == 'A'
                    && input[(i + 3).coerceAtMost(input.size - 1)][j] == 'S'

        fun checkBottomLeft(input: List<List<Char>>, i: Int, j: Int) =
            i < input.size - 3 && j >= 3
                    && input[(i + 1).coerceAtMost(input.size - 1)][(j - 1).coerceAtLeast(0)] == 'M'
                    && input[(i + 2).coerceAtMost(input.size - 1)][(j - 2).coerceAtLeast(0)] == 'A'
                    && input[(i + 3).coerceAtMost(input.size - 1)][(j - 3).coerceAtLeast(0)] == 'S'

        var count = 0
        for (i in input.indices) {
            for (j in 0..<input.first().size) {
                if (input[i][j] == 'X') {
                    if (checkLeft(input, i, j)) count++
                    if (checkTopLeft(input, i, j)) count++
                    if (checkTop(input, i, j)) count++
                    if (checkTopRight(input, i, j)) count++
                    if (checkRight(input, i, j)) count++
                    if (checkBottomRight(input, i, j)) count++
                    if (checkBottom(input, i, j)) count++
                    if (checkBottomLeft(input, i, j)) count++
                }
            }
        }

        println(count)
    }

    fun part2(input: List<List<Char>>) {
        fun check(input: List<List<Char>>, i: Int, j: Int): Boolean {
            val mainDiagonal = setOf(input[i - 1][j - 1], input[i + 1][j + 1])
            val otherDiagonal = setOf(input[i - 1][j + 1], input[i + 1][j - 1])
            val validSet = setOf('S', 'M')
            return mainDiagonal == validSet && mainDiagonal == otherDiagonal
        }

        var count = 0
        for (i in 1..<input.size - 1) {
            for (j in 1..<input.first().size - 1) {
                if (input[i][j] == 'A') {
                    if (check(input, i, j)) count++
                }
            }
        }

        println(count)
    }

    val input = getCharMatrixFromFile("Day04")
    part1(input)
    part2(input)
}