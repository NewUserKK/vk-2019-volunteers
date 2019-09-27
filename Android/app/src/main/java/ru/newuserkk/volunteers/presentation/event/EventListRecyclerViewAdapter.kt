package ru.newuserkk.volunteers.presentation.event

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_event_participant.view.*
import kotlinx.android.synthetic.main.item_list_event.view.*
import kotlinx.android.synthetic.main.item_list_participant.view.*
import ru.newuserkk.volunteers.R
import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.domain.museum.model.Museum
import ru.newuserkk.volunteers.presentation.common.view.AvatarView

typealias EventItem = Pair<AvatarView, String>

class EventListRecyclerViewAdapter(private val values: List<Event>) : RecyclerView.Adapter<EventListRecyclerViewAdapter.ViewHolder>() {

    var onClickListener: ((EventParticipantItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_event, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            name.text = item.name
            place.text = item.museum.address
            date.text = "${item.dateStart} — ${item.dateEnd}"
//            itemView.setOnClickListener {
//                onClickListener?.invoke(item)
//            }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.item_list_event_name
        val place: TextView = view.item_list_event_place
        val date: TextView = view.item_list_event_date
    }
}