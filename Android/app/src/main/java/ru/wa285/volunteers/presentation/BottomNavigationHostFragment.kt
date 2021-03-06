package ru.wa285.volunteers.presentation

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_bottom_navigation_host.view.*
import ru.wa285.volunteers.R
import ru.wa285.volunteers.presentation.State.lastActiveFragmentId
import ru.wa285.volunteers.presentation.State.lastActiveFragmentTag
import ru.wa285.volunteers.presentation.common.AbstractFragment
import ru.wa285.volunteers.presentation.event.CurrentEventsFragment
import ru.wa285.volunteers.presentation.event.EventListFragment
import ru.wa285.volunteers.presentation.museum.MuseumListFragment
import ru.wa285.volunteers.presentation.person.ProfileFragment

object State {
    var lastActiveFragmentTag: String? = null
    var lastActiveFragmentId: Int = R.id.eventListFragment
}

interface BottomNavFragment {
    fun updateAdapters()
}

class BottomNavigationHostFragment : AbstractFragment() {

    override val layoutResId: Int = R.layout.fragment_bottom_navigation_host
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            loadFragment(item.itemId)
            true
        }


    override fun View.setupFragment() {
        bottom_navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        loadFragment(lastActiveFragmentId)
    }

    private fun loadFragment(itemId: Int) {
        val tag = itemId.toString()
        val fragment: Fragment = childFragmentManager.findFragmentByTag(tag) ?: when (itemId) {
            R.id.eventListFragment -> {
                EventListFragment() as Fragment
            }
            R.id.museumListFragment -> {
                MuseumListFragment()
            }
            R.id.profileFragment -> {
                ProfileFragment()
            }
            R.id.currentEventsFragment -> {
                CurrentEventsFragment()
            }
            else -> error("No fragment with id $itemId")
        }

        val transaction = childFragmentManager.beginTransaction()

        if (lastActiveFragmentTag != null) {
            val lastFragment = childFragmentManager.findFragmentByTag(lastActiveFragmentTag)
            if (lastFragment != null) {
                transaction.hide(lastFragment)
            }
        }

        if (!fragment.isAdded) {
            transaction.add(R.id.bottom_navigation_fragment_container, fragment, tag)
        } else {
            transaction.show(fragment)
        }

        (fragment as BottomNavFragment).updateAdapters()

        transaction.commit()
        lastActiveFragmentTag = tag
        lastActiveFragmentId = itemId
    }
}
