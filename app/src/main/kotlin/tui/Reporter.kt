package tui

import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@Deprecated("Don't use it. It's just an example")
class Reporter(job: String) {

    companion object {
        private const val RED = "\u001B[31m"
        private const val GREEN = "\u001B[32m"
        private const val WHITE = "\u001B[0m"
        private const val MOVE = "\u001B[30D"
        private const val CLEAN = "\u001B[K"

        private const val FAIL = 'x'
        private const val SUCCESS = '✓'
        private const val INDICATOR = '•'
        private const val SPACE = ' '

        private const val WIDTH = 3
        private const val PADDING = 10

        fun cleanln() {
            print(MOVE)
            print(CLEAN)
        }

        private fun Long.format(): String {
            val min = TimeUnit.SECONDS.toMinutes(this)
            val sec = TimeUnit.MINUTES.toSeconds(min)
            return String.format("%02d:%02d", min, this - sec)
        }
    }

    private var index = 0
    private var inc = true
    private val chars = CharArray(WIDTH) { SPACE }
    private val jobName = job.padEnd(PADDING, SPACE)

    var time: Long = 0

    private fun generateBar() {
        if (inc && index == chars.size - 1) {
            inc = false
        }
        if (!inc && index == 0) {
            inc = true
        }
        chars[index] = SPACE
        index = if (inc) index.inc() else index.dec()
        chars[index] = INDICATOR
    }

    private fun printResult(success: Boolean) {
        val result = if (success) {
            "$GREEN$SUCCESS$WHITE"
        } else {
            "$RED$FAIL$WHITE"
        }
        val duration = time.format()
        val string = "[ $result ]  $jobName  $duration"
        println(string)
    }

    private fun printBar() {
        val bar = String(chars)
        val duration = time.format()
        val string = "[$bar]  $jobName  $duration "
        print(string)
    }

    suspend fun displayBar() {
        while (true) {
            cleanln()
            printBar()
            generateBar()
            delay(150)
        }
    }

    fun displayResult(success: Boolean) {
        cleanln()
        printResult(success)
    }
}