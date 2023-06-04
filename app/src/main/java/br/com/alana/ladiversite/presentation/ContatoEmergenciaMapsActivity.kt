package br.com.alana.ladiversite.presentation

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.telephony.SmsManager
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityContatoEmergenciaMapsBinding
import br.com.alana.ladiversite.utils.Discador
import br.com.alana.ladiversite.utils.Utils
import com.example.mobcomponents.customtoast.CustomToast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


class ContatoEmergenciaMapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private val binding: ActivityContatoEmergenciaMapsBinding by lazy { ActivityContatoEmergenciaMapsBinding.inflate(layoutInflater) }
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var addresses: List<Address>
    private var enderecoEmergencia: String? = null

    private var latEndereco: Double = 0.0
    private var lonEndereco: Double = 0.0

    lateinit var preferencias: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@ContatoEmergenciaMapsActivity)
        setContentView(binding.root)

        //verifica se o servico de locale está ligado
        if (!isLocationEnabled(this))
            CustomToast.warning(this, "É necessário ligar o serviço de localização antes!")

        //inicializo o acesso ao SHAREDPREFs - dados do contato de emergencia
        preferencias = PreferenceManager.getDefaultSharedPreferences(this)

        //inicializo o MAPA -
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //inicializa fine location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        checkPermissions()

        binding.sendWhats.setOnClickListener {
            val msgMap = "Olá! Estou neste endereço e preciso de ajuda!"
            val mensageSms = "http://maps.google.com/maps?q=${latEndereco},${lonEndereco}&iwloc=A"

            sendSMSMessage(mensageSms)
            sendSMSMessage(msgMap)
            sendWhatsAppMessage()
        }

        binding.cardviewCall.setOnClickListener { Discador.ligarTelefone("015"+getTelefoneContato(), this, this@ContatoEmergenciaMapsActivity) }
    }


    private fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.SEND_SMS,
                Manifest.permission.ACCESS_FINE_LOCATION),
                ASK_MULTIPLE_PERMISSION_REQUEST_CODE)
        }
    }


    fun sendSMSMessage(msg: String){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), PERMISSION_SEND_SMS)
        } else {
            try {
                val sms: SmsManager = SmsManager.getDefault()
                sms.sendTextMessage(
                    "+55" + getTelefoneContato(),
                    null,
                    msg,
                    null,
                    null)

                CustomToast.success(this, "SMS enviado!")

            } catch (exception: Exception) {
                CustomToast.error(this, exception.toString())
            }
        }
    }

    fun sendWhatsAppMessage(){
        val mensagemEmergencia = "Olá! Estou no endereço abaixo e preciso da sua ajuda: \n ${enderecoEmergencia} \n\n" +
                "http://maps.google.com/maps?q=${latEndereco},${lonEndereco}&iwloc=A"
        acionarContatoWhatsApp(this, "55" + getTelefoneContato().toString(), mensagemEmergencia)
    }

    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return LocationManagerCompat.isLocationEnabled(locationManager)
    }

    fun getTelefoneContato(): String?{
            preferencias = getSharedPreferences("contato", MODE_PRIVATE)
            return preferencias.getString("contatoTelefone", "")
    }


    fun acionarContatoWhatsApp(context: Context, mobileNumber: String?, mensagem: String) {
        val url = "https://api.whatsapp.com/send?phone=${mobileNumber}&text=${mensagem}"
        val intent = Intent(Intent.ACTION_VIEW).apply {
            this.data = Uri.parse(url)
            this.`package` = "com.whatsapp"
        }

        try {
            context.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            CustomToast.error(this, "WhatsApp não instalado neste aparelho!")
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setupMap()
    }

    private fun setupMap() {
        //verifica se tem permissao de acesso a localizacao
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //solicita permissao ao usuario
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

            if(location != null){
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLong)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 18f))

                //popula variavel enderecoEmergencia com base na lat e long
                val geocoder = Geocoder(this, Locale.getDefault())
                addresses = geocoder.getFromLocation(lastLocation.latitude, lastLocation.longitude, 1) as List<Address>
                enderecoEmergencia = addresses[0].getAddressLine(0)
                latEndereco = lastLocation.latitude
                lonEndereco = lastLocation.longitude

            }
        }
    }


    private fun placeMarkerOnMap(currentLatLong: LatLng) {
        val markerOptions = MarkerOptions().position(currentLatLong)
        markerOptions.title("$currentLatLong")
        mMap.addMarker(markerOptions)
    }

    override fun onMarkerClick(p0: Marker) = false

    companion object {
        private const val LOCATION_REQUEST_CODE = 1
        private const val PERMISSION_SEND_SMS = 123
        private const val ASK_MULTIPLE_PERMISSION_REQUEST_CODE = 30

        fun startMapEmergencia(context: Context): Intent {
            return Intent(context, ContatoEmergenciaMapsActivity::class.java)
        }
    }
}