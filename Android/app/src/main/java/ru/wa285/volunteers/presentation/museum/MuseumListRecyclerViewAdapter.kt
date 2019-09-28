package ru.wa285.volunteers.presentation.museum

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_museum.view.*
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.museum.model.Museum

class MuseumListRecyclerViewAdapter(private val values: List<Museum>) : RecyclerView.Adapter<MuseumListRecyclerViewAdapter.ViewHolder>() {

    var onClickListener: ((Museum) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_museum, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            nameView.text = item.name
            placeView.text = item.address
            itemView.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.item_list_museum_name
        val placeView: TextView = view.item_list_museum_place
    }
}