package util

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

object Log {

    private val file = File("${System.getenv("TMPDIR")}/octo-print-tui.append")

    val redirect: ProcessBuilder.Redirect = ProcessBuilder.Redirect.appendTo(file)

    fun reset() = file.writeText("Starting...\n")

    fun load() = file.readLines().forEach(::println)

    fun append(message: String) = file.appendText("$message\n")

    fun panic(throwable: Throwable) {
        file.appendText("PANIC!\n")
        ByteArrayOutputStream(1024).use {
            throwable.printStackTrace(PrintStream(it))
            file.appendText(it.toString())
        }
    }
}
