package com.shudley.lottocombinationgenerator.utils

object CombinationGenerator {

    fun generate(
        numbers: List<Int>,
        outputSize: Int
    ): List<List<Int>> {

        val results = mutableListOf<List<Int>>()

        fun combine(
            start: Int,
            current: MutableList<Int>
        ) {

            if (current.size == outputSize) {
                results.add(current.toList())
                return
            }

            for (i in start until numbers.size) {
                current.add(numbers[i])
                combine(i + 1, current)
                current.removeAt(current.lastIndex)
            }
        }

        combine(0, mutableListOf())

        return results
    }
}