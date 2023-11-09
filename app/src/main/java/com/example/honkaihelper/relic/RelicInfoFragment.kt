package com.example.honkaihelper.relic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.databinding.FragmentRelicInfoBinding

class RelicInfoFragment : BaseFragment<FragmentRelicInfoBinding>(FragmentRelicInfoBinding::inflate) {

    private val idRelic get() = requireArguments().getInt(ID_RELIC)

    override fun setupView() {

    }

    override fun uiStateHandle() {

    }

    companion object {
        private const val ID_RELIC = "id_relic"

        fun newInject(idRelic: Int): Bundle {
            return bundleOf(ID_RELIC to idRelic)
        }
    }
}