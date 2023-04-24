package br.com.alana.ladiversite.data.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.alana.ladiversite.data.model.AcolhidaModel
import br.com.alana.ladiversite.databinding.AcolhidaAdminVhBinding
import br.com.alana.ladiversite.databinding.AcolhidaVhBinding
import br.com.alana.ladiversite.presentation.*
import com.example.mobcomponents.customtoast.CustomToast
import java.security.KeyStore.TrustedCertificateEntry

class AdminAdapter(
    private val activity: AdminActivity,
    private val context: Context,
    private var casaList: ArrayList<AcolhidaModel>
):
    RecyclerView.Adapter<AdminAdapter.AdminViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
        val itemLista = AcolhidaAdminVhBinding.inflate(LayoutInflater.from(context), parent, false)
        return AdminViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        holder.nomeCasa.text = casaList[position].nome
        holder.endereco.text = casaList[position].endereco
        holder.publicoAlvo.text = casaList[position].publico
        holder.email.text = casaList[position].email
        holder.telefone.text = casaList[position].telefone
        holder.acolhimento.text = casaList[position].acolhimento
        holder.atividades.text = casaList[position].atividades
        holder.visivel.text = casaList[position].visivel.toString()

        holder.aprovarBtn.setOnClickListener {
            activity.bottomApprove(casaList[position].nome!!)
        }

        holder.negarBtn.setOnClickListener {
            activity.bottomDelete(casaList[position].nome!!)
        }

        holder.editarCasaBtn.setOnClickListener {
            activity.startActivity(CadastroAcolhidaActivity.startAcolhidaEdicao(
                context,
                nomeTela = "Edição Acolhida",
                casaList[position].nome,
                casaList[position].acolhimento,
                casaList[position].atividades,
                casaList[position].email,
                casaList[position].endereco,
                casaList[position].publico,
                casaList[position].telefone
            ))
        }

    }

    override fun getItemCount() = casaList.size

    inner class AdminViewHolder(binding: AcolhidaAdminVhBinding): RecyclerView.ViewHolder(binding.root){
        val nomeCasa = binding.nomeCasa
        val endereco = binding.enderecoAcolhida
        val publicoAlvo = binding.publicoAlvo
        val telefone = binding.telefoneEdicaoAcolhida
        val email = binding.emailEdicaoAcolhida
        val acolhimento = binding.acolhimentoEdicaoAcolhida
        val atividades = binding.atividadesEdicaoAcolhida
        val visivel = binding.visivelToggle
        val aprovarBtn = binding.aprovarBtn
        val negarBtn = binding.negarBtn
        val editarCasaBtn = binding.editarCasaBtn
    }

}