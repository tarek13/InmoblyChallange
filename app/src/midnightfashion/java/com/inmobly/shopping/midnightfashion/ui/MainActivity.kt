package com.inmobly.shopping.midnightfashion.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.inmobly.common_ui.utils.extensions.gone
import com.inmobly.common_ui.utils.extensions.visible
import com.inmobly.shopping.R
import com.inmobly.shopping.databinding.ActivityMainBinding
import com.inmobly.shopping.dresscode.utils.extensions.getCurrentFragment
import com.inmobly.shopping.midnightfashion.ui.favorites.FavouriteFragment
import com.inmobly.shopping.midnightfashion.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var navController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupView()
    }

    private fun setupView() {
        setupNavigationComponent()
        binding.mainBottomNav.setOnItemSelectedListener() {
            when(it.itemId){
                com.inmobly.common_ui.R.id.home_action->{
                    if(getCurrentFragment() !is HomeFragment) {
                        navController?.navigate(R.id.homeFragment)

                    }
                }
                /*R.id.cart_action->{

                }*/
                com.inmobly.common_ui.R.id.fav_action->{
                    if(getCurrentFragment() !is FavouriteFragment) {
                        navController?.navigate(R.id.favoritesFragment)

                    }
                }


            }

            true
        }
        binding.mainBottomNav.selectedItemId=com.inmobly.common_ui.R.id.home_action
    }



    private fun setupNavigationComponent() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        if (navHostFragment != null) {
            navController = navHostFragment.navController
        }
        navController?.let { setupWithNavController(binding.mainBottomNav, it)
            binding.mainBottomNav.setupWithNavController(it)
        }


        navController!!.addOnDestinationChangedListener{_, destination, _ ->
            if(destination.id==R.id.productDetailsFragment){
                binding.mainBottomNav.gone()
            }else {
                binding.mainBottomNav.visible()
            }
        }
    }


}