package domain

enum class State {
    Operational,
    Printing,
    Starting,
    Finishing,
    Paused,
    Pausing,
    Cancelling,
    Error,
    Offline,
    Unknown
}