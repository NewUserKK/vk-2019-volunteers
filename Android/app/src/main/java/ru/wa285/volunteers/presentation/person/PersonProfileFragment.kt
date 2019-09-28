package ru.wa285.volunteers.presentation.person

import org.kodein.di.generic.instance
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.event.EventRepository
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.domain.museum.MuseumRepository
import ru.wa285.volunteers.presentation.common.AbstractFragment
import ru.wa285.volunteers.presentation.event.EventListRecyclerViewAdapter


class ProfileFragment : AbstractFragment() {

    override val layoutResId: Int = R.layout.fragment_profile
    
}
