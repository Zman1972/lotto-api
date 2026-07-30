package com.shudley.lottocombinationgenerator

object CombinationGenerator {

    fun generate(numbers: List<Int>, choose: Int): List<List<Int>> {

        val results = mutableListOf<List<Int>>()

        if (choose <= 0 || choose > numbers.size) {
            return results
        }

        fun combine(start: Int, current: MutableList<Int>) {

            if (current.size == choose) {
                results.add(current.toList())
                return
            }

            for (i in start until numbers.size) {
                current.add(numbers[i])
                combine(i + 1, current)
                current.removeAt(current.size - 1)
            }
        }

        combine(0, mutableListOf())

        return results
    }
}