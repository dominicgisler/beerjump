package app.beerjump.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import app.beerjump.R
import app.beerjump.model.KeyValuePair
import kotlinx.android.synthetic.main.statistics_row.view.*

class StatisticsAdapter(
    var statistics: ArrayList<KeyValuePair>,
    val context: Context
) : BaseAdapter() {
    var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return statistics.size
    }

    override fun getItem(index: Int): KeyValuePair {
        return statistics.get(index)
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
            view = layoutInflater.inflate(R.layout.statistics_row, oldView)
        } else {
            view = oldView
        }

        val stats = getItem(index)
        view.statsText.text = stats.key
        view.statsCount.text = stats.value

        return view
    }

    override fun isEnabled(position: Int): Boolean {
        return false
    }
}

