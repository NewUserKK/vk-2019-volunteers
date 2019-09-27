package ru.newuserkk.volunteers.presentation.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_participant.view.*
import ru.newuserkk.volunteers.R
import ru.newuserkk.volunteers.domain.person.model.Person
import ru.newuserkk.volunteers.presentation.common.view.AvatarView

class EventParticipantRecyclerViewAdapter(private val values: List<Person>) : RecyclerView.Adapter<EventParticipantRecyclerViewAdapter.ViewHolder>() {

    var onClickListener: ((Person) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_participant, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            avatarView.person = item
            nameView.text = item.name
//            itemView.setOnClickListener {
//                onClickListener?.invoke(item)
//            }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val avatarView: AvatarView = view.participant_avatar_view
        val nameView: TextView = view.participant_avatar_name
    }
}