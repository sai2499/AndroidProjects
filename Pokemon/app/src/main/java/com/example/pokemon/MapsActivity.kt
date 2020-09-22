package com.example.pokemon

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception
import java.security.Permission

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        checkPermission();
        loadPokemon()
    }

    var ACCESSLOCATION = 123
    fun checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    ACCESSLOCATION
                )
                return
            }
        }
        GetUserLocation()
    }

    fun GetUserLocation() {
        Toast.makeText(this, "User location access on", Toast.LENGTH_LONG).show()
        var myLocation = MyloactionListener()
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f, myLocation)
        var mythread = myThread()
        mythread.start()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            ACCESSLOCATION -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    GetUserLocation()
                } else {
                    Toast.makeText(this, "Cannot access location", Toast.LENGTH_LONG).show()
                }
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    var location: Location? = null

    inner class MyloactionListener : LocationListener {

        constructor() {
            location = Location("Start")
            location!!.longitude = 0.0
            location!!.latitude = 0.0
        }

        override fun onLocationChanged(p0: Location) {
            location = p0
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onProviderDisabled(provider: String) {

        }

    }
    var oldLocation:Location?=null
    inner class myThread : Thread {
        constructor() : super() {
            oldLocation= Location("Start")
            oldLocation!!.latitude=0.0
            oldLocation!!.longitude=0.0
        }

        override fun run() {
            while (true) {
                try {
                    if(oldLocation!!.distanceTo(location)==0f){
                        continue
                    }
                    oldLocation=location
                    runOnUiThread {
                        mMap!!.clear()
                        val Pune = LatLng(location!!.latitude, location!!.longitude)
                        mMap!!.addMarker(
                            MarkerOptions().position(Pune).title("Marker in Pune")
                                .snippet("Here is my location")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario))
                        )
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Pune, 5F))

                        for(i in 0..listPokemon.size-1){
                            var newPokemon=listPokemon[i]
                            if(newPokemon.isCatch==false)
                            {
                                val pokemonLoc = LatLng(newPokemon.location!!.latitude!!,newPokemon.location!!.longitude!!)
                                mMap!!.addMarker(
                                    MarkerOptions().position(Pune).title(newPokemon.name!!)
                                        .snippet(newPokemon.des!!)
                                        .icon(BitmapDescriptorFactory.fromResource(newPokemon.image!!))
                                )
                                if(location!!.distanceTo(newPokemon.location)<2)
                                {
                                    newPokemon.isCatch=true
                                    listPokemon[i]=newPokemon
                                    playerPower+=newPokemon.power!!
                                    Toast.makeText(applicationContext,"Your new pokemon"+playerPower,Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                    Thread.sleep(1000)

                } catch (e: Exception) {

                }
            }
        }
    }

    var playerPower=0.0
    var listPokemon=ArrayList<Pokemon>()
    fun loadPokemon(){
        listPokemon.add(Pokemon(R.drawable.charmander,"Charmander","Here is Japan",55.0, 37.77,-122.4018,false))
        listPokemon.add(Pokemon(R.drawable.bulbasaur,"Bulbasaur","Here is Usa",90.5, 37.7949,-122.4104,false))
        listPokemon.add(Pokemon(R.drawable.squirtle,"Squirtle","Here is Iraq",33.5, 37.7816 ,-122.4122,false))
    }
}