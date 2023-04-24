package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alana.ladiversite.data.adapter.AcolhidaAdapter
import br.com.alana.ladiversite.data.model.AcolhidaModel
import br.com.alana.ladiversite.databinding.ActivityAcolhidaBinding
import com.example.mobcomponents.customtoast.CustomToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class AcolhidaActivity : AppCompatActivity() {

    private val binding: ActivityAcolhidaBinding by lazy { ActivityAcolhidaBinding.inflate(layoutInflater) }
    private val recycler: RecyclerView by lazy { binding.recyclerAcolhida }
    private var adapterAcolhidas: AcolhidaAdapter? = null
    private lateinit var casaLista: ArrayList<AcolhidaModel>
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        verifyAdmin()

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)

        casaLista = arrayListOf()

        recuperaDadosFirestore()

        binding.cadastroCasa.setOnClickListener {
            startActivity(CadastroAcolhidaActivity.startCadastroAcolhida(this))
        }

        binding.aprovarBtn.setOnClickListener {
            startActivity(AdminActivity.startAdmin(this))
        }
    }

    private fun recuperaDadosFirestore(){
        db.collection("casas").get().addOnSuccessListener { document ->
            if (!document.isEmpty){
                for (dados in document.documents){
                    val casas: AcolhidaModel? = dados.toObject(AcolhidaModel::class.java)
                    if (casas != null){
                        if (casas.visivel == true){
                            casaLista.add(casas)
                        }
                    }
                }
                adapterAcolhidas = AcolhidaAdapter(this, casaLista)
                recycler.adapter = adapterAcolhidas
            }
        }
            .addOnFailureListener {
                CustomToast.error(this, it.toString())
            }
    }

    private fun verifyAdmin(){
        val fireUser = FirebaseAuth.getInstance().currentUser?.uid
        if (fireUser!!.equals("wWYLeLPAb5SMsBob5b7j6oRprwZ2")){
            binding.aprovarBtn.visibility = View.VISIBLE
        }
    }

    companion object {
        fun startAcolhida(context: Context): Intent {
            return Intent(context, AcolhidaActivity::class.java)
        }
    }
}

