package com.kursatkumsuz.bootcampfinalprojecttravelapp.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.LayoutChooseBottomSheetBinding

class ChooseBottomSheet : BottomSheetDialogFragment() {


    private lateinit var binding : LayoutChooseBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutChooseBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButton()
    }

    private fun initButton() {
        binding.apply {
            createTripButton.setOnClickListener {
                findNavController().navigate(R.id.action_chooseBottomSheet_to_addTripFragment)
            }

            suggestedTripButton.setOnClickListener {
                findNavController().navigate(R.id.action_chooseBottomSheet_to_bottomSheetFragment)
            }
        }
    }
}