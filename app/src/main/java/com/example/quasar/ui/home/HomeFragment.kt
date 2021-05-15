package com.example.quasar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quasar.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create view model of this fragment
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // Inflate layout
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root  // root view of layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getApod().observe(viewLifecycleOwner, {
            binding.apod = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}