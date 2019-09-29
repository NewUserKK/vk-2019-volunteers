package ru.wa285.volunteers.presentation.active

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.kodein.di.generic.instance
import ru.wa285.volunteers.ActiveEventParticipantFragment
import ru.wa285.volunteers.R
import ru.wa285.volunteers.presentation.VolunteersApp

class ActiveEventPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val resources: Resources by VolunteersApp.kodein.instance()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ActiveEventTasksFragment()
            1 -> ActiveEventChatFragment()
            2 -> ActiveEventParticipantFragment()
            else -> error("Unknown fragment id")
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Задания"
            1 -> "Чат"
            2 -> "Участники"
            else -> error("Unknown fragment id")
        }
    }
}
