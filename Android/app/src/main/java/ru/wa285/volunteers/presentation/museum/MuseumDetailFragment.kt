package ru.wa285.volunteers.presentation.museum

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_museum_detail.view.*
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.museum.model.Museum
import ru.wa285.volunteers.presentation.common.AbstractFragment
import ru.wa285.volunteers.presentation.common.view.NamePicture

class MuseumDetailFragment : AbstractFragment() {

    override val layoutResId: Int = R.layout.fragment_museum_detail

    private val args: MuseumDetailFragmentArgs by navArgs()
    lateinit var museum: Museum

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        museum = args.museum
    }

    override fun View.setupFragment() {
        museum_detail_name.text = museum.name
        museum_detail_avatar_view.value = NamePicture(museum.name, museum.logoUri)
//        museum_detail_description.text = museum.description
    }
}
