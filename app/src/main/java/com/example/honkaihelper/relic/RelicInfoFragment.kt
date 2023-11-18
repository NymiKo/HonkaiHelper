package com.example.honkaihelper.relic

import android.content.Context
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.databinding.FragmentRelicInfoBinding
import com.example.honkaihelper.utils.fromHtml
import com.example.honkaihelper.utils.loadImageWithoutScale
import com.google.android.material.transition.MaterialContainerTransform

class RelicInfoFragment : BaseFragment<FragmentRelicInfoBinding>(FragmentRelicInfoBinding::inflate) {

    private val idRelic get() = requireArguments().getInt(ID_RELIC)
    private val viewModel by viewModels<RelicInfoViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.relicInfoComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 500
        }
        viewModel.getRelic(idRelic)
    }

    override fun setupView() {
        setupToolbar()
        getRelic()
    }

    override fun uiStateHandle() {

    }

    private fun setupToolbar() {
        binding.toolbarInfoAboutRelic.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getRelic() {
        viewModel.relic.observe(viewLifecycleOwner) {
            binding.imageRelic.loadImageWithoutScale(it.image)
            binding.toolbarInfoAboutRelic.title = it.title
            binding.textEffectDescriptionTwoPartsRelic.text = fromHtml(getString(R.string.effect_relic_two_parts, it.descriptionTwoParts))
            binding.textEffectDescriptionFourPartsRelic.text = fromHtml(getString(R.string.effect_relic_four_parts, it.descriptionFourParts))
        }
    }

    companion object {
        private const val ID_RELIC = "id_relic"

        fun newInject(idRelic: Int): Bundle {
            return bundleOf(ID_RELIC to idRelic)
        }
    }
}