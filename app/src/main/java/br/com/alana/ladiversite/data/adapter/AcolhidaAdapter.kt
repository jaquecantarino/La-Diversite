package br.com.alana.ladiversite.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alana.ladiversite.data.model.AcolhidaModel
import br.com.alana.ladiversite.databinding.AcolhidaVhBinding
import br.com.alana.ladiversite.presentation.AcolhidaDetalheActivity

class AcolhidaAdapter(
    private val context: Context,
    private val casaList: ArrayList<AcolhidaModel>
):
    RecyclerView.Adapter<AcolhidaAdapter.AcolhidaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcolhidaViewHolder {
        val itemLista = AcolhidaVhBinding.inflate(LayoutInflater.from(context), parent, false)
        return AcolhidaViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: AcolhidaViewHolder, position: Int) {
        holder.nomeCasa.text = casaList[position].nome
        holder.endereco.text = casaList[position].endereco
        holder.publicoAlvo.text = casaList[position].publico
        holder.email.text = casaList[position].email
        holder.telefone.text = casaList[position].telefone
        holder.acolhimento.text = casaList[position].acolhimento
        holder.atividades.text = casaList[position].atividades
        holder.visivel.text = casaList[position].visivel.toString()

        holder.card.setOnClickListener{
            context.startActivity(AcolhidaDetalheActivity.startAcolhidaDetalhe(
                context,
                casaList[position].nome,
                casaList[position].endereco,
                casaList[position].publico,
                casaList[position].telefone,
                casaList[position].email,
                casaList[position].acolhimento,
                casaList[position].atividades
                )
            )
        }
    }

    override fun getItemCount() = casaList.size

    inner class AcolhidaViewHolder(binding: AcolhidaVhBinding): RecyclerView.ViewHolder(binding.root){
        val nomeCasa = binding.nomeCasa
        val endereco = binding.enderecoAcolhida
        val publicoAlvo = binding.publicoAlvo
        val card = binding.cardAcolhida
        val telefone = binding.telefoneDetalheAcolhida
        val email = binding.emailDetalheAcolhida
        val acolhimento = binding.acolhimentoDetalheAcolhida
        val atividades = binding.atividadesDetalheAcolhida
        val visivel = binding.visivelToggle
    }

}