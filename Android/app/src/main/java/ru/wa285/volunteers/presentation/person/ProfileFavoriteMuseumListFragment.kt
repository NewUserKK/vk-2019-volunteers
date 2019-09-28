package ru.wa285.volunteers.presentation.person


import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_museum_list.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.museum.MuseumRepository
import ru.wa285.volunteers.domain.museum.model.Museum
import ru.wa285.volunteers.presentation.BottomNavigationHostFragmentDirections
import ru.wa285.volunteers.presentation.common.AbstractFragment
import ru.wa285.volunteers.presentation.museum.MuseumListRecyclerViewAdapter


class ProfileFavoriteMuseumListFragment : AbstractFragment() {

    override val layoutResId: Int =
        R.layout.fragment_profile_favourite_museum_list

    private val museumRepository: MuseumRepository by kodein.instance()

    private val museumList = mutableListOf<Museum>()
    lateinit var museumRecyclerViewAdapter: MuseumListRecyclerViewAdapter

    override fun View.setupFragment() {
        museumRecyclerViewAdapter = MuseumListRecyclerViewAdapter(museumList).apply {
            onClickListener = {
                navigateToMuseumDetails(it)
            }
        }
        museum_list.adapter = museumRecyclerViewAdapter
        fillMuseumList()
    }

    private fun View.fillMuseumList() {
        launch {
            val result = withContext(Dispatchers.IO) {
                museumRepository.getAll()
            }
            when (result) {
                is OperationResult.Success -> {
                    museumList += result.value
                    museumRecyclerViewAdapter.notifyDataSetChanged()
                }
                is OperationResult.Failure -> {
                    Toast.makeText(context, result.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun navigateToMuseumDetails(museum: Museum) {
        val action = BottomNavigationHostFragmentDirections
            .actionBottomNavigationHostFragmentToMuseumDetailFragment(museum)
        requireParentFragment().findNavController().navigate(action)
    }
}
