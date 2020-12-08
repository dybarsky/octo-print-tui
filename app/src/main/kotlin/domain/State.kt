package domain

enum class State {
    Operational,
    Printing,
    Paused,
    Pausing,
    Cancelling,
    Error,
    Offline,
    Unknown
}