package ru.wa285.volunteers

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_active_event.*
import kotlinx.android.synthetic.main.fragment_event_participant.*
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.presentation.active.ActiveEventPagerAdapter
import ru.wa285.volunteers.presentation.common.AbstractFragment
import ru.wa285.volunteers.presentation.event.participant.EventParticipantFragmentArgs

class ActiveEventParticipantFragment : AbstractFragment() {

    //TODO: скопировал с EventParticipantFragment, чо происходит-то
    override val layoutResId: Int = R.layout.fragment_active_event_participant

    private val args: EventParticipantFragmentArgs by navArgs()

    lateinit var event: Event

    private lateinit var viewPagerAdapter: ActiveEventPagerAdapter

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
        viewPagerAdapter = ActiveEventPagerAdapter(childFragmentManager)
        event_participant_view_pager.adapter = viewPagerAdapter
        event_tab_layout.setupWithViewPager(event_participant_view_pager)
    }

}
