import domain.Current
import domain.Filament
import domain.File
import domain.Job
import domain.Progress

fun dummyData() = Current(
    raw = "Printing",
    job = Job(
        estimatedPrintTime = 8811.55,
        file = File(
            name = "raspberry_pi_case.gcode",
            size = 14234L,
            date = 1378847754L
        ),
        filament = Filament(
            tool0 = Filament.Tool(
                length = 810.98,
                volume = 5.67
            )
        )
    ),
    progress = Progress(
        completion = 0.2298468264184775,
        printTime = 276,
        printTimeLeft = 912
    )
)
