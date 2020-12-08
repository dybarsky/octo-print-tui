@file:JvmName("App")

import kotlinx.coroutines.runBlocking
import network.Client
import kotlin.system.exitProcess

fun main(args: Array<String>): Unit = runBlocking {
    println("oops")

    val url = "http://octopi.local"
    val key = "9480EC7B32E44B7EA63ECF9A3C2E15D5"

    val client = Client(url, key)

    val current = client.getCurrent()
    println(current)


    exitProcess(0)
}