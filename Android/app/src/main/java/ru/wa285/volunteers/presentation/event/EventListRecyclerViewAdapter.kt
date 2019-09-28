package ru.wa285.volunteers.presentation.event

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_event.view.*
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.event.model.Event

class EventListRecyclerViewAdapter(private val values: List<Event>) :
    RecyclerView.Adapter<EventListRecyclerViewAdapter.ViewHolder>() {

    var onClickListener: ((Event) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_event, parent, false)
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
            placeView.text = item.museum.address
            dateView.text = "${item.dateStart} — ${item.dateEnd}"
            // TODO: placeholder
            Picasso.get()
                .load(item.avatarUri)
                .into(imageView)
            itemView.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.item_list_event_name
        val placeView: TextView = view.item_list_event_place
        val dateView: TextView = view.item_list_event_date
        val imageView: ImageView = view.item_list_event_image
    }
}