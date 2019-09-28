package ru.wa285.volunteers.presentation.person

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_achievement.view.*
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.achievement.model.Achievement

class ProfileAchievementListRecyclerViewAdapter(private val values: List<Achievement>) : RecyclerView.Adapter<ProfileAchievementListRecyclerViewAdapter.ViewHolder>() {

    var onClickListener: ((Achievement) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_achievement, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            Picasso.get()
                .load(item.logoUri)
                .into(imageView)
            itemView.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.achievement_image_view
    }
}