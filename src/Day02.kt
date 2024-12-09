import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        // Pas d'accès GitHub sur le PC sur lequel j'ai résolu l'énigme, à rappatrier
        return input.size
    }

    fun part2(input: List<String>): Int {
        // Pas d'accès GitHub sur le PC sur lequel j'ai résolu l'énigme, à rappatrier
        return input.size
    }

    // Or read a large test input from the `src/Day01_test.txt.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
