package app.beerjump.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import app.beerjump.R
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterAdapter(
    var characters: ArrayList<String>,
    val context: Context
) : BaseAdapter() {
    var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return characters.size
    }

    override fun getItem(index: Int): String {
        return characters[index]
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
            view = layoutInflater.inflate(R.layout.character_item, oldView)
        } else {
            view = oldView
        }

        view.player.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                context.resources.getIdentifier(getItem(index).format("down"), "drawable", context.packageName)
            )
        )
        view.bar.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_disappearing_bar))

        return view
    }
}

