@file:JvmName("App")

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import network.Client
import tui.Printer
import util.Config
import util.Log

fun main(args: Array<String>): Unit =
    runBlocking {
        Log.reset()
        Printer.reset()

        val (url, key, gap) = Config.instance()
        val client = Client(url, key)

        while (true) {
            val current = client.getCurrent()
            Printer.print(current)
            delay(gap.toLong() * 1000)
        }
    }
