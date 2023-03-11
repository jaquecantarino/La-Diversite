package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alana.ladiversite.data.adapter.AcolhidaAdapter
import br.com.alana.ladiversite.databinding.ActivityAcolhidaBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class AcolhidaActivity : AppCompatActivity() {

    private val binding: ActivityAcolhidaBinding by lazy { ActivityAcolhidaBinding.inflate(layoutInflater) }
    private lateinit var adapterAcolhidas: AcolhidaAdapter

    private var nomeAcolhida: ArrayList<String?> = ArrayList()
    private var enderecoAcolhida: ArrayList<String?> = ArrayList()
    private var telefoneAcolhida: ArrayList<String?> = ArrayList()
    private var emailAcolhida: ArrayList<String?> = ArrayList()
    private var publicoAcolhida: ArrayList<String?> = ArrayList()
    private val acolhimentoAcolhida: ArrayList<String?> = ArrayList()
    private val atividadesAcolhida: ArrayList<String?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        val recyclerAcolhida = binding.recyclerAcolhida
        recyclerAcolhida.layoutManager = LinearLayoutManager(this)
        recyclerAcolhida.setHasFixedSize(true)
        adapterAcolhidas = AcolhidaAdapter(
            this,
            nomeAcolhida,
            enderecoAcolhida,
            telefoneAcolhida,
            publicoAcolhida,
            emailAcolhida,
            acolhimentoAcolhida,
            atividadesAcolhida)
        getListAcolhidas()
        recyclerAcolhida.adapter = adapterAcolhidas
    }

    private fun getListAcolhidas(){
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val userArray = obj.getJSONArray("acolhidas")
            for (i in 0 until userArray.length()){
                val userDetail = userArray.getJSONObject(i)
                nomeAcolhida.add(userDetail.getString("nome"))
                enderecoAcolhida.add(userDetail.getString("endereco"))
                publicoAcolhida.add(userDetail.getString("publico-alvo"))
                telefoneAcolhida.add(userDetail.getString("telefone"))
                emailAcolhida.add(userDetail.getString("e-mail"))
                acolhimentoAcolhida.add(userDetail.getString("acolhimento"))
                atividadesAcolhida.add(userDetail.getString("atividades"))
            }
        } catch (e: JSONException){
            e.printStackTrace()
        }
    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("acolhidas.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }

    companion object {
        fun startAcolhida(context: Context): Intent {
            return Intent(context, AcolhidaActivity::class.java)
        }
    }
}

