package com.example.tanorami.info_about_weapon.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.info_about_weapon.presentation.InfoAboutWeaponViewModel
import com.google.android.material.transition.MaterialContainerTransform
import javax.inject.Inject

class InfoAboutWeaponFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val idWeapon get() = requireArguments().getInt(ID_WEAPON)
    private val viewModel by viewModels<InfoAboutWeaponViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.weaponInfoComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.navHostFragment
            duration = 500
            scrimColor = Color.TRANSPARENT
        }
        viewModel.getWeapon(idWeapon)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    InfoAboutWeaponScreen(
                        idWeapon = idWeapon,
                        viewModel = viewModel,
                        navController = findNavController()
                    )
                }
            }
        }
    }

    companion object {
        private const val ID_WEAPON = "id_weapon"

        fun newInstance(idWeapon: Int): Bundle {
            return bundleOf(ID_WEAPON to idWeapon)
        }
    }
}