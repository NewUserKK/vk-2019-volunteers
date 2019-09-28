package ru.newuserkk.volunteers.presentation.event.participant

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.kodein.di.generic.instance
import ru.newuserkk.volunteers.R
import ru.newuserkk.volunteers.presentation.VolunteersApp

class EventParticipantPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val resources: Resources by VolunteersApp.kodein.instance()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> EventParticipantListFragment()
            1 -> EventParticipantFriendsFragment()
            else -> error("Unknown fragment id")
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> resources.getString(R.string.event_participant_list_title)
            1 -> resources.getString(R.string.event_participant_friends_title)
            else -> error("Unknown fragment id")
        }
    }
}
