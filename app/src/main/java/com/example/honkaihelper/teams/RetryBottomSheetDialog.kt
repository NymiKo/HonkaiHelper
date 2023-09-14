package com.example.honkaihelper.teams

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.honkaihelper.databinding.DialogRetryBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.ClassCastException

class RetryBottomSheetDialog: BottomSheetDialogFragment() {

    private var _binding: DialogRetryBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var callback: RetryDialogCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogRetryBottomSheetBinding.inflate(inflater, container, false)
        dialog?.window?.setDimAmount(0F)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogInterface = super.onCreateDialog(savedInstanceState)
        dialogInterface.setOnShowListener {
            (view?.parent as ViewGroup).background = ColorDrawable(Color.TRANSPARENT)
        }
        return dialogInterface
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    fun setCallback(callback: RetryDialogCallback) {
        this.callback = callback
    }

    private fun setupView() {
        binding.buttonRetry.setOnClickListener {
            callback?.onRetryClick()
            dismiss()
        }
    }

    companion object {
        const val TAG = "RetryBottomSheetDialog"
    }

    interface RetryDialogCallback {
        fun onRetryClick()
    }
}