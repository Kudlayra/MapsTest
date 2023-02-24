package com.example.mapstest.app.presentation.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mapstest.R
import com.example.mapstest.app.MapsTestApplication
import com.example.mapstest.app.presentation.ui.view_models.MainViewModel
import com.example.mapstest.app.presentation.ui.view_models.MainViewModelFactory
import com.example.mapstest.data.models.Address
import com.example.mapstest.databinding.FragmentMainBinding
import javax.inject.Inject


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<MainFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        doInjections()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLocationPermission()
        addClickListeners()
        setObservers()
    }

    private fun addClickListeners() {
        binding.showMapBtn.setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToMapFragment(viewModel.currentLocation.value)
            findNavController().navigate(action)
        }
    }

    private fun setObservers() {
        viewModel.currentLocation.observe(viewLifecycleOwner) { location ->
            updateUi(location)
        }
    }

    private fun updateUi(location: Address?) {
        location?.let {
            binding.tvAddressInfo.text =
                String.format(getString(R.string.address),
                    location.latitude,
                    location.longitude)
        } ?: run { binding.tvAddressInfo.text = getString(R.string.location_not_found) }
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
        }
    }

    private fun doInjections() {
        (requireContext().applicationContext as MapsTestApplication).appComponent.inject(this)
        args.address?.let {
            viewModel.setCurrentLocation(it)
        }
    }

    companion object {
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    }
}