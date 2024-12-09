fun main() {

    val searchedText = "XMAS"

    val directions = listOf(
        0 to 1, // Vertical bas
        0 to -1, // Vertical haut
        1 to 0, // Horizontal gauche
        -1 to 0, // Horizontal droite,
        1 to 1, // Diagonale bas droite
        -1 to 1, // Diagonale bas gauche
        1 to -1, // Diagonale haut droite
        -1 to -1 // Diagonale haut gauche
    )

    val crossElements = listOf('M', 'S')

    fun searchXmas(array: List<CharArray>, x: Int, y: Int, direction: Pair<Int, Int>): Boolean {
        var index = 0

        val limits = array.lastIndex to array[0].lastIndex

        var currentX = x
        var currentY = y

        do {
            if (currentX !in 0..limits.second) return false
            if (currentY !in 0..limits.first) return false

            if (array[currentY][currentX] != searchedText[index]) {
                return false
            }

            index++

            currentX += direction.first
            currentY += direction.second

        } while (index <= searchedText.lastIndex)

        return true
    }

    fun part1(input: List<String>): Int {
        val array = input.map { it.toCharArray() }

        var count = 0
        array.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char != searchedText[0]) return@forEachIndexed

                directions.forEach { direction ->
                    if (searchXmas(array, x, y, direction)) {
                        count++
                    }
                }
            }
        }

        return count
    }

    fun part2(input: List<String>): Int {
        val array = input.map { it.toCharArray() }

        var count = 0

        // On skip les premières et dernières lignes et colonnes (impossible que l'un des 'A' qui s'y trouve soit l'origine d'une croix sans sortir du tableau)
        (1..<array.lastIndex).forEach { y ->
            (1..<array[y].lastIndex).forEach outer@ { x ->
                if (array[y][x] != 'A') {
                    return@outer
                }

                // Diagonale \
                if (!listOf(array[y + 1][x + 1], array[y - 1][x - 1]).containsAll(crossElements)) {
                    return@outer
                }

                // Diagonale /
                if (!listOf(array[y + 1][x - 1], array[y - 1][x + 1]).containsAll(crossElements)) {
                    return@outer
                }

                count++
            }
        }

        return count
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}