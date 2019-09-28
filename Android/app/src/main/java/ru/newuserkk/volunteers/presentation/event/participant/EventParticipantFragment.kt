package ru.newuserkk.volunteers.presentation.event.participant


import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_event_participant.*
import ru.newuserkk.volunteers.R
import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.presentation.common.AbstractFragment

class EventParticipantFragment : AbstractFragment() {

    override val layoutResId: Int = R.layout.fragment_event_participant

    val args: EventParticipantFragmentArgs by navArgs()

    lateinit var event: Event

    private lateinit var viewPagerAdapter: EventParticipantPagerAdapter

    private val currentItem: AbstractFragment
        get() = viewPagerAdapter.instantiateItem(
            event_participant_view_pager,
            event_participant_view_pager.currentItem
        ) as AbstractFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        event = args.event
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = EventParticipantPagerAdapter(childFragmentManager)
        event_participant_view_pager.adapter = viewPagerAdapter
        event_tab_layout.setupWithViewPager(event_participant_view_pager)
    }
}
