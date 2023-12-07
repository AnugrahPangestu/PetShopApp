package com.example.aplikasipetshop.ui.maps

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.aplikasipetshop.R
import com.example.aplikasipetshop.databinding.ActivityMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        getMyLocation()


        val pupu = LatLng(-2.9815416597834656, 104.70631633032775)
        val goygoy = LatLng(-2.9926275183397015, 104.72704457049153)
        val cipo = LatLng(-2.980780928366392, 104.7239587488994)
        val yongmi = LatLng(-2.98958280720724, 104.73714497545603)
        val cicio = LatLng(-2.998708508889904, 104.72878338577218)
        val marisa = LatLng(-2.98772828402075, 104.71868430997392)
        val evo = LatLng(-2.9719282942322827, 104.7401407269492)
        val helo = LatLng(-2.976031292042448, 104.73729836847357)
        mMap.addMarker(
            MarkerOptions()
                .position(pupu)
                .title("Pupu Petshop")
                .snippet("Jl. Tj. Barangan, Bukit Baru, Kec. Ilir Bar. I, Kota Palembang, Sumatera Selatan 30000")
        )
        mMap.addMarker(
            MarkerOptions()
                .position(goygoy)
                .title("GoyGoy Petshop")
                .snippet("Bukit Lama, Kec. Ilir Bar. I, Kota Palembang, Sumatera Selatan")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(cipo)
                .title("Cipo Petshop")
                .snippet("Jl. Demang Lebar Daun No.101, Demang Lebar Daun, Kec. Ilir Bar. I, Kota Palembang, Sumatera Selatan 30131")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(yongmi)
                .title("Yongmi Petshop")
                .snippet("Jalan Jaksa Agung R Suprapto no 953C, 26 Ilir D. I, Kec. Ilir Bar. I, Kota Palembang, Sumatera Selatan 30136")
        )

        mMap.addMarker(
            MarkerOptions()
                .position(cicio)
                .title("Cicio Petshop")
                .snippet("Bukit Lama, Kec. Ilir Bar. I, Kota Palembang, Sumatera Selatan 30137")
        )
        mMap.addMarker(
            MarkerOptions()
                .position(marisa)
                .title("Marisa Petshop")
                .snippet("Jl. Macan Lindungan No.39, Bukit Baru, Kec. Ilir Bar. I, Kota Palembang, Sumatera Selatan 30131")
        )
        mMap.addMarker(
            MarkerOptions()
                .position(evo)
                .title("Evo Pets shop")
                .snippet("Jl. Sumpah Pemuda No.K2, Lorok Pakjo, Kec. Ilir Bar. I, Kota Palembang, Sumatera Selatan 30126")
        )
        mMap.addMarker(
            MarkerOptions()
                .position(helo)
                .title("Hello Petshop")
                .snippet("Lrg. Muhajirin IV, Lorok Pakjo, Kec. Ilir Bar. I, Kota Palembang, Sumatera Selatan 30126")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pupu))

        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }
    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

}