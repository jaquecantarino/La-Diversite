package br.com.alana.ladiversite.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.AcolhidaVhBinding
import br.com.alana.ladiversite.databinding.DelegaciaVhBinding
import br.com.alana.ladiversite.utils.Discador

class DelegaciaAdapter(
    private val context: Context,
    private val delegacia: ArrayList<String?>,
    private val enderecoDeleg: ArrayList<String?>,
    private val telefoneDelegacia: ArrayList<String?>,
    private val publicoDelegacia: ArrayList<String?>,
    private val atividadesDelegacia: ArrayList<String?>
):
    RecyclerView.Adapter<DelegaciaAdapter.DelegaciaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DelegaciaViewHolder {
        val itemLista = DelegaciaVhBinding.inflate(LayoutInflater.from(context), parent, false)
        return DelegaciaViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: DelegaciaViewHolder, position: Int) {
        holder.delegacia.text = delegacia[position]
        holder.endereco.text = enderecoDeleg[position]
        holder.publicoAlvo.text = publicoDelegacia[position]
        holder.telefone.text = telefoneDelegacia[position]
        holder.atividades.text = atividadesDelegacia[position]

        holder.card.setOnClickListener{
            val delega = delegacia[position]
            Toast.makeText(context, "Ligando para...\n$delega", Toast.LENGTH_SHORT).show()
            Discador.ligarTelefone(telefoneDelegacia[position], context)
        }
    }

    override fun getItemCount() = delegacia.size

    inner class DelegaciaViewHolder(binding: DelegaciaVhBinding): RecyclerView.ViewHolder(binding.root){
        val delegacia = binding.nomeDelegacia
        val endereco = binding.enderecoDelegacia
        val telefone = binding.telefoneDelegacia
        val publicoAlvo = binding.publicoAlvoDelegacia
        val atividades = binding.atividadesDelegacia
        val card = binding.cardDelegacia
    }

}