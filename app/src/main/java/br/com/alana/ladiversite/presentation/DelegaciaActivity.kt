package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alana.ladiversite.data.adapter.DelegaciaAdapter
import br.com.alana.ladiversite.databinding.ActivityDelegaciaBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class DelegaciaActivity : AppCompatActivity() {

    private val binding: ActivityDelegaciaBinding by lazy { ActivityDelegaciaBinding.inflate(layoutInflater) }
    private lateinit var adapter: DelegaciaAdapter

    private var delegacia: ArrayList<String?> = ArrayList()
    private var enderecoDelega: ArrayList<String?> = ArrayList()
    private var telefoneDelegacia: ArrayList<String?> = ArrayList()
    private var publicoDelegacia: ArrayList<String?> = ArrayList()
    private var atividadesDelegacia: ArrayList<String?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        val recyclerDelegacia = binding.recyclerDelegacia
        recyclerDelegacia.layoutManager = LinearLayoutManager(this)
        recyclerDelegacia.setHasFixedSize(true)
        adapter = DelegaciaAdapter(this,
            delegacia,
            enderecoDelega,
            telefoneDelegacia,
            publicoDelegacia,
            atividadesDelegacia)
        getListaDelegacias()
        recyclerDelegacia.adapter = adapter
    }

    private fun getListaDelegacias() {
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val userArray = obj.getJSONArray("delegacias")
            for (i in 0 until userArray.length()){
                val userDetail = userArray.getJSONObject(i)
                delegacia.add(userDetail.getString("nome"))
                enderecoDelega.add(userDetail.getString("endereco"))
                telefoneDelegacia.add(userDetail.getString("telefone"))
                publicoDelegacia.add(userDetail.getString("publico-alvo"))
                atividadesDelegacia.add(userDetail.getString("atividades"))
            }
        } catch (e: JSONException){
            e.printStackTrace()
        }
    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("delegacias.json")
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
        fun startDelegacia(context: Context): Intent {
            return Intent(context, DelegaciaActivity::class.java)
        }
    }
}