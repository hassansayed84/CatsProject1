package com.example.catsproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.catsproject.databinding.FragmentFirstBinding
import androidx.fragment.app.activityViewModels
import android.widget.ArrayAdapter




class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    // Assuming you've set up a ViewModelProvider in your fragment
    private val viewModel: CatViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.fetchCatBreeds(requireContext())
    }

    private fun setupObservers() {
        viewModel.catBreeds.observe(viewLifecycleOwner, { breeds ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, breeds)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

