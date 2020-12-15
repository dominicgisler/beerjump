package app.beerjump.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import app.beerjump.R
import app.beerjump.model.Score
import kotlinx.android.synthetic.main.score_cell.view.*

class ScoreAdapter(
    var scores: ArrayList<Score>,
    val context: Context
) : BaseAdapter() {
    var layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return scores.size
    }

    override fun getItem(index: Int): Score {
        return scores.get(index)
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getView(
        index: Int, oldView: View?,
        viewGroup: ViewGroup?
    ): View {
        val view: View
        if (oldView == null) {
            view = layoutInflater.inflate(R.layout.score_cell, null)
        } else {
            view = oldView
        }

        val score = getItem(index)

        var tfs : Int = Typeface.NORMAL
        if (score.own) {
            tfs = Typeface.BOLD
        }

        view.username.text = "${(index + 1)}.\u00A0${score.username}"
        view.promille.text = String.format("%.2f‰", score.promille)
        view.score.text = score.score.toString()
        view.username.setTypeface(null, tfs)
        view.promille.setTypeface(null, tfs)
        view.score.setTypeface(null, tfs)

        return view
    }
}

