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
import org.kodein.di.generic.instance
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.domain.person.PersonRepository
import ru.wa285.volunteers.presentation.VolunteersApp
import ru.wa285.volunteers.presentation.VolunteersApp.Companion.kodein
import ru.wa285.volunteers.presentation.common.hide
import ru.wa285.volunteers.presentation.common.show
import ru.wa285.volunteers.presentation.common.toLocalizedString

data class EventAdapterItem(val event: Event, var favorite: Boolean)

class EventListRecyclerViewAdapter(private val values: List<EventAdapterItem>) :
    RecyclerView.Adapter<EventListRecyclerViewAdapter.ViewHolder>() {

    var onClickListener: ((Event) -> Unit)? = null
    var onFavouriteClickListener: ((Event, Boolean) -> Unit)? = null

    private val personRepository: PersonRepository by kodein.instance()

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
        val event = item.event
        holder.apply {
            nameView.text = event.name
            placeView.text = event.museum.address
            val startDate = event.dateStart.toLocalizedString(VolunteersApp.locale)
            val endDate = event.dateEnd.toLocalizedString(VolunteersApp.locale)
            dateView.text = "$startDate — $endDate"
            Picasso.get()
                .load(event.avatarUri)
                .placeholder(R.drawable.placeholder_no_image)
                .into(imageView)

            favouriteView.setOnClickListener {
                item.favorite = !item.favorite
                onFavouriteClickListener?.invoke(event, item.favorite)
                notifyItemChanged(adapterPosition)
            }
            val logged = personRepository.getLoggedUser()
            if (logged == null) {
                favouriteView.hide()
            } else {
                favouriteView.show()
                if (item.favorite) {
                    favouriteView.setImageResource(R.drawable.ic_favourite_filled_24px)
                    favouriteView.setColorFilter(imageView.resources.getColor(R.color.red))
                } else {
                    favouriteView.setImageResource(R.drawable.ic_favourite_outline_24px)
                    favouriteView.setColorFilter(imageView.resources.getColor(R.color.black))

                }
            }

            itemView.setOnClickListener {
                onClickListener?.invoke(event)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.item_list_event_name
        val placeView: TextView = view.item_list_event_place
        val dateView: TextView = view.item_list_event_date
        val imageView: ImageView = view.item_list_event_image
        val favouriteView: ImageView = view.item_list_event_favourite
    }
}