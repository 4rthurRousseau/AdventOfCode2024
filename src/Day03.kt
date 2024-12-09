import java.io.BufferedReader
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.stream.Collectors


fun main() {

    val regex = """mul\([1-9][0-9]*,[1-9][0-9]*\)""".toRegex()
    val enablingDisablingRegex = """do(n't)*\(\)""".toRegex()
    val START_MARKER = "do()"
    val END_MARKER = "don't()"

    fun part1(input: List<String>): Int {
        return input.sumOf {
            regex.findAll(it).map { it.value.replace("mul(", "").replace(")", "") }.sumOf {
                val (x, y) = it.split(",")
                x.toInt() * y.toInt()
            }
        }
    }

    fun part2(input: List<String>): Int {
        val updatedReports = input.map { report ->
            val ranges = mutableListOf<IntRange>()
            var startingIndex: Int? = 0
            enablingDisablingRegex.findAll(report).map { it.value to it.range }.forEach { (value, range) ->
                if (value == START_MARKER && startingIndex == null) {
                    startingIndex = range.last + 1
                } else if (value == END_MARKER && startingIndex != null) {
                    ranges.add(startingIndex!!..range.first - 1)
                    startingIndex = null
                }
            }

            startingIndex?.let {
                ranges.add(it..report.lastIndex)
            }

            println(ranges)

            val updatedReport = StringBuilder()
            ranges.forEach {
                updatedReport.append(report.substring(it))
            }

            println(updatedReport.toString())

            updatedReport.toString()
        }

        return part1(updatedReports)
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    val input = readInput("Day03").joinToString(separator = "")
    part1(listOf(input)).println()
    part2(listOf(input)).println()
}