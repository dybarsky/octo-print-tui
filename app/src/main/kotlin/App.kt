@file:JvmName("App")

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import network.Client
import util.Config
import util.Log

fun main(args: Array<String>): Unit =
    runBlocking {
        Log.reset()

        val (url, key, gap) = Config.instance()
        val client = Client(url, key)

        while (true) {
            println(client.getVersion())
            println(client.getCurrent())
            delay(gap.toLong() * 1000)
        }
    }
