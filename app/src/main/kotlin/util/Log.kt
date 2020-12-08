package util

import java.io.File

object Log {

    private val file = File("${System.getenv("TMPDIR")}/gr.append")

    val redirect: ProcessBuilder.Redirect = ProcessBuilder.Redirect.appendTo(file)

    fun reset() = file.writeText("")

    fun load() = file.readLines().forEach(::println)

    fun append(message: String) = file.appendText("\n##### $message #####\n\n")
}