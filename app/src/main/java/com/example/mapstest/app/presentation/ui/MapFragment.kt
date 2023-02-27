package com.example.mapstest.app.presentation.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mapstest.R
import com.example.mapstest.app.MapsTestApplication
import com.example.mapstest.app.presentation.ui.view_models.MainViewModelFactory
import com.example.mapstest.app.presentation.ui.view_models.MapViewModel
import com.example.mapstest.data.models.Address
import com.example.mapstest.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.ui_view.ViewProvider
import javax.inject.Inject


class MapFragment : Fragment(), LocationListener, InputListener {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private var mapKit: MapKit? = null
    private var mapView: MapView? = null
    private var placemarkIcon: View? = null
    private var needScrollToLocationFlag = true
    private val args by navArgs<MapFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private val viewModel: MapViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        mapKitInit()
        doInjections()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLocationPermission()
        setMapData()
        addClickListeners()
        addObservers()

    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        mapView?.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapTap(map: Map, point: Point) {
        mapView!!.map.mapObjects.clear()
        mapView!!.map.mapObjects.addPlacemark(point, ViewProvider(placemarkIcon))
        needScrollToLocationFlag = false
        viewModel.getAddress(point)
    }

    override fun onMapLongTap(map: Map, point: Point) {}

    override fun onLocationUpdated(location: Location) {
        view?.let {
            if (needScrollToLocationFlag) {
                scrollToPosition(location.position.latitude, location.position.longitude)
            }
            binding.progressBar.progressBarContainer.visibility = View.GONE
            binding.tvAddressInfo.visibility = View.VISIBLE
        }
    }

    private fun scrollToPosition(lat: Double, lon: Double) {
        mapView!!.map.move(
            CameraPosition(Point(lat, lon), DEFAULT_ZOOM, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 1f),
            null)
    }

    private fun setPlacemarkerIcon() {
        placemarkIcon = View(requireContext())
        placemarkIcon?.background =
            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_placemarker_24)
    }

    private fun doInjections() {
        (requireContext().applicationContext as MapsTestApplication).appComponent.inject(this)
        args.address?.let { address -> viewModel.setCurrentLocation(address) }
    }

    private fun setMapData() {
        mapView = binding.mapview
        mapView?.map?.addInputListener(this)
        args.address?.let {
            mapView?.map?.mapObjects?.addPlacemark(Point(it.latitude, it.longitude),
                ViewProvider(placemarkIcon))
            needScrollToLocationFlag = false
        }
    }

    override fun onLocationStatusUpdated(locationStatus: LocationStatus) {}

    private fun addObservers() {
        viewModel.currentLocation.observe(viewLifecycleOwner) { location ->
            updateUi(location)
        }
    }

    private fun addClickListeners() {
        binding.applyBtn.setOnClickListener {
            val action =
                MapFragmentDirections.actionMapFragmentToMainFragment(viewModel.currentLocation.value)
            findNavController().navigate(action)
        }
    }

    private fun updateUi(location: Address?) {
        location?.let {
            binding.tvAddressInfo.text =
                String.format(getString(R.string.address),
                    location.addressName ?: getString(R.string.no_address_data),
                    location.latitude,
                    location.longitude)
            binding.applyBtn.isEnabled = true
        } ?: run { binding.tvAddressInfo.text = getString(R.string.location_not_found) }
        binding.progressBar.progressBarContainer.visibility = View.GONE
        binding.tvAddressInfo.visibility = View.VISIBLE
    }

    private fun isGpsEnabled() {
        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showMessage(getString(R.string.geodata_disabled))
            updateUi(null)
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun mapKitInit() {
        MapKitFactory.initialize(requireContext())
        mapKit = MapKitFactory.getInstance()
        mapKit?.createLocationManager()?.requestSingleUpdate(this)
        setPlacemarkerIcon()
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) updateUi(null)
        isGpsEnabled()
    }

    companion object {
        const val DEFAULT_ZOOM = 14.0f
    }

}