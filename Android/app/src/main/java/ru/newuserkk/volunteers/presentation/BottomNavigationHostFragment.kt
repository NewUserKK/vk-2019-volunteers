package ru.newuserkk.volunteers.presentation

import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_bottom_navigation_host.view.*
import ru.newuserkk.volunteers.R
import ru.newuserkk.volunteers.presentation.common.AbstractFragment
import ru.newuserkk.volunteers.presentation.event.CurrentEventsFragment
import ru.newuserkk.volunteers.presentation.event.EventListFragment
import ru.newuserkk.volunteers.presentation.museum.MuseumListFragment
import ru.newuserkk.volunteers.presentation.person.ProfileFragment

class BottomNavigationHostFragment : AbstractFragment() {

    override val layoutResId: Int = R.layout.fragment_bottom_navigation_host
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            loadFragment(item.itemId)
            true
        }

    var lastActiveFragmentTag: String? = null

    override fun View.setupFragment() {
        bottom_navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun loadFragment(itemId: Int) {
        val tag = itemId.toString()
        val fragment = childFragmentManager.findFragmentByTag(tag) ?: when (itemId) {
            R.id.eventListFragment -> {
                EventListFragment()
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
            if (lastFragment != null)
                transaction.hide(lastFragment)
        }

        if (!fragment.isAdded) {
            transaction.add(R.id.bottom_navigation_fragment_container, fragment, tag)
        } else {
            transaction.show(fragment)
        }

        transaction.commit()
        lastActiveFragmentTag = tag
    }
}
