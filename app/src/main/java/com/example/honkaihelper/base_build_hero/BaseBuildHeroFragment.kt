package com.example.honkaihelper.base_build_hero

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.base_build_hero.adapters.WeaponsAdapter
import com.example.honkaihelper.base_build_hero.data.model.Weapon
import com.example.honkaihelper.databinding.FragmentBaseBuildHeroBinding

class BaseBuildHeroFragment :
    BaseFragment<FragmentBaseBuildHeroBinding>(FragmentBaseBuildHeroBinding::inflate) {

    private lateinit var mAdapterWeapons: WeaponsAdapter

    override fun setupView() {
        setupToolbar()
        setupAdapters()
        setupWeaponRecyclerView()
    }

    override fun uiStateHandle() {

    }

    private fun setupToolbar() {
        binding.toolbarBaseBuildHero.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupAdapters() {
        mAdapterWeapons = WeaponsAdapter()
    }

    private fun setupWeaponRecyclerView() {
        binding.recyclerWeaponBaseBuildHero.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            adapter = mAdapterWeapons
        }
        setListWeapon()
    }

    private fun setListWeapon() {
        mAdapterWeapons.list = listOf(
            Weapon(
                1,
                "Меч",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/3/3a/Световой_конус_Просто_надо_подождать_Карточка.png/revision/latest?cb=20230721121734&path-prefix=ru",
                1,
                1
            ),
            Weapon(
                2,
                "Момент победы",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/a/af/Световой_конус_Момент_победы_Карточка.png/revision/latest?cb=20230710214745&path-prefix=ru",
                6,
                2
            ),
            Weapon(
                2,
                "Решимость блестит подобно жемчужинам пота",
                "https://static.wikia.nocookie.net/honkai-star-rail/images/4/44/Световой_конус_Решимость_блестит_подобно_жемчужинам_пота_Карточка.png/revision/latest?cb=20230710214809&path-prefix=ru",
                5,
                1
            )
        )
    }

    override fun onDestroyView() {
        binding.recyclerWeaponBaseBuildHero.adapter = null
        super.onDestroyView()
    }
}