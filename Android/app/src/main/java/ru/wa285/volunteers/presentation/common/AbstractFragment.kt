package ru.wa285.volunteers.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import ru.wa285.volunteers.presentation.VolunteersApp
import kotlin.coroutines.CoroutineContext


abstract class AbstractFragment : Fragment(), KodeinAware, CoroutineScope {

    override val kodein: Kodein = VolunteersApp.kodein

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    abstract val layoutResId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(layoutResId, container, false)
        with(rootView) {
            setupFragment()
        }
        return rootView
    }

    open fun View.setupFragment() {}
}