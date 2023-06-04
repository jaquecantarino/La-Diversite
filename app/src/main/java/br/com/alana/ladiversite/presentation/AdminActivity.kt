package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.data.adapter.AdminAdapter
import br.com.alana.ladiversite.data.model.AcolhidaModel
import br.com.alana.ladiversite.databinding.ActivityAdminBinding
import br.com.alana.ladiversite.utils.Utils
import com.example.mobcomponents.customtoast.CustomToast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.FirebaseFirestore

class AdminActivity : AppCompatActivity() {

    private val binding: ActivityAdminBinding by lazy { ActivityAdminBinding.inflate(layoutInflater) }
    private var adapterAcolhidas: AdminAdapter? = null
    private lateinit var casaLista: ArrayList<AcolhidaModel>
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@AdminActivity)
        setContentView(binding.root)

        binding.recyclerAcolhida.layoutManager = LinearLayoutManager(this@AdminActivity)
        binding.recyclerAcolhida.setHasFixedSize(true)

        casaLista = arrayListOf()
        recuperaDadosFirestore()
    }

    fun bottomDelete(casa: String){
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_dialog, null)
        val btnAprovar = view.findViewById<Button>(R.id.confimar_btn)
        val btnNegar = view.findViewById<Button>(R.id.cancelar_btn)

        btnNegar.setOnClickListener {
            dialog.dismiss()
        }

        btnAprovar.setOnClickListener {
            dialog.dismiss()
            negarCasa(casa)
            finish()
            startActivity(AcolhidaActivity.startAcolhida(this).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }

        dialog.setCancelable(true)
        dialog.setContentView(view)
        dialog.show()
    }

    fun bottomApprove(casa: String){
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_dialog, null)
        val btnAprovar = view.findViewById<Button>(R.id.confimar_btn)
        val btnNegar = view.findViewById<Button>(R.id.cancelar_btn)
        val labelOK = view.findViewById<TextView>(R.id.bottom_title_label)
        val txtYN = view.findViewById<TextView>(R.id.txt_yes_no)
        val txtLI = view.findViewById<TextView>(R.id.txt_label_inferior)

        labelOK.text = getString(R.string.confirmar_aprova_o)
        txtYN.text = getString(R.string.quer_mesmo_aprovar_essa_casa)
        txtLI.visibility = View.INVISIBLE

        btnNegar.setOnClickListener {
            dialog.dismiss()
        }

        btnAprovar.setOnClickListener {
            dialog.dismiss()
            aprovarCasa(casa)
            finish()
            startActivity(AcolhidaActivity.startAcolhida(this).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }

        dialog.setCancelable(true)
        dialog.setContentView(view)
        dialog.show()
    }

    fun aprovarCasa(casa: String) {
        db.collection("casas").document(casa).update("visivel", true)
            .addOnSuccessListener {
                CustomToast.success(this, "Casa aprovada com sucesso!")
            }.addOnFailureListener {
                CustomToast.warning(this, it.toString())
            }
    }

    fun negarCasa(casa: String) {
        db.collection("casas").document(casa).update("ativo", false)
            .addOnSuccessListener {
                CustomToast.success(this, "Casa negada e armazenada no BD!")
            }.addOnFailureListener {
                CustomToast.warning(this, it.toString())
            }
    }

    private fun recuperaDadosFirestore(){
        db.collection("casas").get().addOnSuccessListener { document ->
            if (!document.isEmpty){
                for (dados in document.documents){
                    val casas: AcolhidaModel? = dados.toObject(AcolhidaModel::class.java)
                    if (casas != null){
                        if (casas.visivel == false && casas.ativo == true){
                            casaLista.add(casas)
                        }
                    }
                }

                if (casaLista.size == 0) {
                    binding.recyclerAcolhida.visibility = View.GONE
                    binding.messageEmpty.visibility = View.VISIBLE
                    CustomToast.info(this, "Não há aprovações a serem feitas!!")
                } else {
                    binding.recyclerAcolhida.visibility = View.VISIBLE
                    binding.messageEmpty.visibility = View.GONE
                    adapterAcolhidas = AdminAdapter(this,this, casaLista)
                    binding.recyclerAcolhida.adapter = adapterAcolhidas
                }
            }
        }
            .addOnFailureListener {
                CustomToast.error(this, "Erro na recuperacao dos dados!")
            }
    }

    companion object {
        fun startAdmin(context: Context): Intent {
            return Intent(context, AdminActivity::class.java)
        }
    }
}