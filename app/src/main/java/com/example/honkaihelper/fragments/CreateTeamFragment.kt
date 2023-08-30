package com.example.honkaihelper.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentCreateTeamBinding
import com.example.honkaihelper.models.Hero

class CreateTeamFragment : BaseFragment<FragmentCreateTeamBinding>(FragmentCreateTeamBinding::inflate) {

    private var hero: Hero? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hero
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_team, container, false)
    }

    companion object {

        private const val ARG_PARAM1 = "param1"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateTeamFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}