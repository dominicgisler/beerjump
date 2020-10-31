package app.beerjump.model

class HighscoreList() {
    var lastUser = ""
    var scores = ArrayList<Score>()

    fun addScore(score: Score) {
        scores.add(score)
        lastUser = score.username
    }
}