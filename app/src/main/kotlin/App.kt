@file:JvmName("App")

import kotlinx.coroutines.runBlocking
import network.Client
import util.Config
import kotlin.system.exitProcess

fun main(args: Array<String>): Unit = runBlocking {
    val (url, key) = Config.instance()
    val client = Client(url, key)




    val current = client.getCurrent()
    println(current)

    val version = client.getVersion()
    println(version)

    exitProcess(0)
}