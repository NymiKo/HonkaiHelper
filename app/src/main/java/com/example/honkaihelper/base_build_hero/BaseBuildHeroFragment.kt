package com.example.honkaihelper.base_build_hero

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.App
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.base_build_hero.adapters.DecorationsAdapter
import com.example.honkaihelper.base_build_hero.adapters.RelicsAdapter
import com.example.honkaihelper.base_build_hero.adapters.WeaponsAdapter
import com.example.honkaihelper.base_build_hero.data.model.Weapon
import com.example.honkaihelper.databinding.FragmentBaseBuildHeroBinding
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.info_about_hero.data.model.Decoration
import com.example.honkaihelper.info_about_hero.data.model.Relic

class BaseBuildHeroFragment :
    BaseFragment<FragmentBaseBuildHeroBinding>(FragmentBaseBuildHeroBinding::inflate) {

    private val viewModel by viewModels<BaseBuildHeroViewModel> { viewModelFactory }

    private lateinit var mAdapterWeapons: WeaponsAdapter
    private lateinit var mAdapterRelics: RelicsAdapter
    private lateinit var mAdapterDecorations: DecorationsAdapter
    private val idHero get() = requireArguments().getInt(ID_HERO)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.baseBuildHeroComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFullBaseBuildHero(idHero)
    }

    override fun setupView() {
        setupToolbar()
        setupAdapters()
        setupWeaponRecyclerView()
        setupRelicRecyclerView()
        setupDecorationRecyclerView()
    }

    override fun uiStateHandle() {
        viewModel.fullBaseBuildHero.observe(viewLifecycleOwner) {
            mAdapterWeapons.list = it.weapons
        }
    }

    private fun setupToolbar() {
        binding.toolbarBaseBuildHero.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupAdapters() {
        mAdapterWeapons = WeaponsAdapter()
        mAdapterRelics = RelicsAdapter()
        mAdapterDecorations = DecorationsAdapter()
    }

    private fun setupWeaponRecyclerView() {
        binding.recyclerWeaponBaseBuildHero.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            adapter = mAdapterWeapons
        }
    }

    private fun setupRelicRecyclerView() {
        binding.recyclerRelicBaseBuildHero.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            adapter = mAdapterRelics
        }
        setListRelic()
    }

    private fun setupDecorationRecyclerView() {
        binding.recyclerDecorationBaseBuildHero.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            adapter = mAdapterDecorations
        }
        setListDecoration()
    }

    private fun setListRelic() {
        mAdapterRelics.list = listOf(
            Relic(
                1,
                "Вестник, блуждающий в хакерском пространстве",
                "Повышает скорость на 6%.",
                "Повышает скорость всех союзников на 12%, когда владелец применяет сверхспособность к союзнику, на 1 ход. Эффект не складывается.",
                "http://f0862137.xsph.ru/images/relics/Messenger_Traversing_Hackerspace.webp"
            ),
            Relic(
                2,
                "Головорез бандитской пустыни",
                "Повышает мнимый урон на 10%.",
                "Когда владелец наносит урон противнику с ослаблениями, его крит. шанс повышается на 10%, а когда владелец наносит урон противнику со статусом Заключение, его крит. урон повышается на 20%.",
                "http://f0862137.xsph.ru/images/relics/Wastelander_of_Banditry_Desert.webp"
            ),
            Relic(
                3,
                "Вор падающего метеора",
                "Повышает эффект пробития на 16%.",
                "Повышает эффект пробития владельца на 16%. Когда владелец пробивает уязвимость противника, восстанавливает 3 ед. энергии.",
                "http://f0862137.xsph.ru/images/relics/Thief_of_Shooting_Meteor.webp"
            ),
            Relic(
                4,
                "Орёл сумеречного рубежа",
                "Повышает ветряной урон на 10%.",
                "После использования сверхспособности действие владельца продвигается вперёд на 25%.",
                "http://f0862137.xsph.ru/images/relics/Eagle_of_Twilight_Line.webp"
            )
        )
    }

    private fun setListDecoration() {
        mAdapterDecorations.list = listOf(
            Decoration(
                1,
                "Белобог Архитекторов",
                "Повышает защиту владельца на 15%. Если шанс попадания эффектов владельца выше или равен 50%, то его защита повышается на доп. 15%.",
                "http://f0862137.xsph.ru/images/decorations/Celestial_Differentiator.webp"
            ),
            Decoration(
                2,
                "Звездистая арена",
                "Повышает крит. шанс владельца на 8%. Если крит. шанс владельца не меньше 70%, урон от его базовой атаки и навыка повышается на 20%.",
                "http://f0862137.xsph.ru/images/decorations/Rutilant_Arena.webp"
            ),
            Decoration(
                3,
                "Талия - край бандитов",
                "Повышает эффект пробития владельца на 16%. Если скорость владельца выше или равна 145 ед., то его эффект пробития повышается на доп. 20%.",
                "http://f0862137.xsph.ru/images/decorations/Talia_Kingdom_of_Banditry.webp"
            )
        )
    }

    override fun onDestroyView() {
        binding.recyclerWeaponBaseBuildHero.adapter = null
        binding.recyclerRelicBaseBuildHero.adapter = null
        binding.recyclerDecorationBaseBuildHero.adapter = null
        super.onDestroyView()
    }

    companion object {
        private const val ID_HERO = "id"

        @JvmStatic
        fun newInstance(idHero: Int): Bundle {
            return bundleOf(ID_HERO to idHero)
        }
    }
}