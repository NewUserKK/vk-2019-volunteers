package ru.wa285.volunteers.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import ru.wa285.volunteers.R
import ru.wa285.volunteers.presentation.common.NavigationResultListener
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity(), NavHost {

    private val navHostFragmentId: Int = R.id.nav_host_fragment
    private lateinit var navigationController: NavController

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationController = Navigation.findNavController(this, navHostFragmentId)
        main_app_bar_layout.setExpanded(false)
        main_collapsing_toolbar_layout.isTitleEnabled = false
        setupToolbar(main_collapsing_toolbar)

    }

    private fun setupToolbar(toolbar: Toolbar) {
        this.toolbar = toolbar
        setSupportActionBar(toolbar)
        val appBarConfiguration = AppBarConfiguration(navGraph = navigationController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun getNavController(): NavController {
        return navigationController
    }

    fun switchToCollapsingToolbar(imageUri: String) {
        main_app_bar_layout.setExpanded(true)
        Picasso.get()
            .load(imageUri)
            .into(main_collapsing_toolbar_image)
    }

    fun switchToRegularToolbar() {
        main_app_bar_layout.setExpanded(false)
    }

    /**
     * https://medium.com/google-developer-experts/using-navigation-architecture-component-in-a-large-banking-app-ac84936a42c2
     */
    fun navigateBackWithResult(action: String, result: Bundle) {
        val childFragmentManager =
            supportFragmentManager.findFragmentById(navHostFragmentId)?.childFragmentManager
        var backStackListener: FragmentManager.OnBackStackChangedListener by Delegates.notNull()
        backStackListener = FragmentManager.OnBackStackChangedListener {
            (childFragmentManager?.fragments?.get(0) as NavigationResultListener).onNavigationResult(
                action,
                result
             )
            childFragmentManager.removeOnBackStackChangedListener(backStackListener)
        }
        childFragmentManager?.addOnBackStackChangedListener(backStackListener)
        navController.popBackStack()
    }
}
