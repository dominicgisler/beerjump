package app.beerjump.model

class Score(val username: String, val promille: Double, val score: Int, val own: Boolean) {
    var synced = false
}