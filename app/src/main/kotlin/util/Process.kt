package util

class Process(
        private val command: String,
        private val args: String? = null,
        private val redirect: Boolean = true
) {

    private operator fun List<String>.plus(optional: String?): List<String> =
            if (optional == null) this else toMutableList().plusElement(optional)

    private fun ProcessBuilder.redirect() = apply {
        if (redirect) {
            redirectOutput(Log.redirect)
            redirectError(Log.redirect)
        }
    }

    fun execute(): java.lang.Process {
        val args = args?.split(' ') ?: emptyList()
        val cmd = command.split(' ') + args
        return ProcessBuilder()
                .redirect()
                .command(cmd)
                .start()
    }
}