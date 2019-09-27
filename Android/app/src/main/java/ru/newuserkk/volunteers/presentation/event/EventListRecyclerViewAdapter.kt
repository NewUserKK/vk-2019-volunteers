package ru.newuserkk.volunteers.presentation.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_event_participant.view.*
import kotlinx.android.synthetic.main.item_list_participant.view.*
import ru.newuserkk.volunteers.R
import ru.newuserkk.volunteers.presentation.common.view.AvatarView

typealias EventItem = Pair<AvatarView, String>

class EventListRecyclerViewAdapter(private val values: List<EventParticipantItem>) : RecyclerView.Adapter<EventListRecyclerViewAdapter.ViewHolder>() {

    var onClickListener: ((EventParticipantItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_event_participant, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            avatarView.person = item.first.person
            name.text = item.second
//            itemView.setOnClickListener {
//                onClickListener?.invoke(item)
//            }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val avatarView: AvatarView = view.participant_avatar_view
        val name: TextView = view.participant_avatar_name
    }
}