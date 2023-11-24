package tui

import domain.*
import java.util.concurrent.TimeUnit

object Printer {

    private const val ESC_RESET = "\u001B[0m"
    private const val ESC_BOLD = "\u001B[1m"
    private const val ESC_DIM = "\u001B[2m"

    private const val ESC_BLACK = "\u001B[30m"
    private const val ESC_RED = "\u001B[31m"
    private const val ESC_GREEN = "\u001B[32m"
    private const val ESC_YELLOW = "\u001B[33m"
    private const val ESC_BLUE = "\u001B[34m"
    private const val ESC_MAGENTA = "\u001B[35m"
    private const val ESC_CYAN = "\u001B[36m"
    private const val ESC_WHITE = "\u001B[37m"

    private const val ESC_UP = "\u001B[1F"
    private const val ESC_SET = "\u001B[2;1H"
    private const val ESC_CLEAN = "\u001B[2J"
    private const val ESC_HIDE = "\u001B[?25L"

    private const val CHAR_NONE = "-"
    private const val CHAR_PADDING = "  "
    private const val CHAR_BLOCK_LIGHT = "░"
    private const val CHAR_BLOCK_HEAVY = "█"

    private const val BAR_LENGTH = 25

    fun print(current: Current) {
        val tui = buildString {
            append(getConnectionMessage(current.state))
            append(getStateMessage(current.state))
            append("\n")
            append(getFileMessage(current.job?.file))
            append(getFilamentMessage(current.job?.filament))
            append(getTimeMessage(current))
            append("\n")
            append(getProgressMessage(current.progress))
        }
        reset()
        println(tui)
    }

    fun reset() {
        print(ESC_CLEAN)
        print(ESC_SET)
    }

    private fun getConnectionMessage(state: State) = buildString {
        val connection = when (state) {
            State.Offline -> "offline".red().bold()
            State.Unknown -> "error".white().bold().dim()
            else -> "online".green().bold()
        }
        append(CHAR_PADDING)
        append("Connection: ".cyan())
        append(connection)
        append("\n")
    }

    private fun getStateMessage(state: State) = buildString {
        val status = when (state) {
            State.Operational -> "idle".green().bold()
            State.Printing -> "printing".blue().bold()
            State.Starting -> "starting".magenta().bold()
            State.Finishing -> "finishing".magenta().bold()
            State.Paused -> "pause".yellow().bold()
            State.Pausing -> "pause".yellow().bold()
            State.Cancelling -> "cancel".yellow().bold().dim()
            State.Error -> "fail".red().bold()
            State.Offline -> dash()
            State.Unknown -> dash()
        }
        append(CHAR_PADDING)
        append("Printer state: ".cyan())
        append(status)
        append("\n")
    }

    private fun getFileMessage(file: File?) = buildString {
        val fileMessage = file?.name?.white() ?: dash()
        append(CHAR_PADDING)
        append("File: ".cyan())
        append(fileMessage)
        append("\n")
    }

    private fun getTimeMessage(current: Current) = buildString {
        val timeEstValue = current.job?.estimatedPrintTime?.toLong()?.format()?.white() ?: dash()
        append(CHAR_PADDING, "Time est: ".cyan(), timeEstValue, "\n")

        val timePrintValue = current.progress?.printTime?.format()?.white() ?: dash()
        append(CHAR_PADDING, "Time print: ".cyan(), timePrintValue, "\n")

        val timeLeftValue = current.progress?.printTimeLeft?.format()?.white() ?: dash()
        append(CHAR_PADDING, "Time left: ".cyan(), timeLeftValue, "\n")
    }

    private fun getFilamentMessage(filament: Filament?) = buildString {
        val meters = filament?.length?.div(1000)
        val filamentMessage = meters?.let { "%.2f m".format(it).white() } ?: dash()
        append(CHAR_PADDING)
        append("Filament: ".cyan())
        append(filamentMessage)
        append("\n")
    }

    private fun getProgressMessage(progress: Progress?) = buildString {
        val percentage = progress?.completion?.div(100) ?: return ""
        val filled = (percentage * BAR_LENGTH).toInt()
        val empty = BAR_LENGTH - filled
        val bar = buildString {
            repeat(filled) {
                append(CHAR_BLOCK_HEAVY)
            }
            repeat(empty) {
                append(CHAR_BLOCK_LIGHT)
            }
        }
        val percents = "%.2f".format(percentage * 100) + "%"

        append(CHAR_PADDING)
        append(bar.white())
        append(CHAR_PADDING)
        append(percents.black().bold())
        append("\n")
    }

    private fun hideCursor() {
//        Runtime.getRuntime().exec("tput civis").waitFor()
//        Process("tput", "civis", redirect = true).execute()
        print(ESC_HIDE)
        System.out.flush()
    }

    private fun Long.format(): String {
        val hours = TimeUnit.SECONDS.toHours(this)
        val minutes = TimeUnit.SECONDS.toMinutes(this)
        return buildString {
            if (hours > 0) append(hours, " hours ")
            append(minutes - hours * 60, " minutes")
        }
    }

    private fun dash() = CHAR_NONE.black().bold()

    private fun String.bold() = "$ESC_BOLD$this$ESC_RESET"

    private fun String.dim() = "$ESC_DIM$this$ESC_RESET"

    private fun String.red() = "$ESC_RED$this$ESC_RESET"

    private fun String.cyan() = "$ESC_CYAN$this$ESC_RESET"

    private fun String.blue() = "$ESC_BLUE$this$ESC_RESET"

    private fun String.black() = "$ESC_BLACK$this$ESC_RESET"

    private fun String.green() = "$ESC_GREEN$this$ESC_RESET"

    private fun String.white() = "$ESC_WHITE$this$ESC_RESET"

    private fun String.yellow() = "$ESC_YELLOW$this$ESC_RESET"

    private fun String.magenta() = "$ESC_MAGENTA$this$ESC_RESET"

}
