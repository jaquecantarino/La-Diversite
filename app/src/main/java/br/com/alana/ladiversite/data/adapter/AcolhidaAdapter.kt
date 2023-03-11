package br.com.alana.ladiversite.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.alana.ladiversite.databinding.AcolhidaVhBinding
import br.com.alana.ladiversite.presentation.AcolhidaDetalheActivity

class AcolhidaAdapter(
    private val context: Context,
    private val nomeAcolhida: ArrayList<String?>,
    private val enderecoAcolhida: ArrayList<String?>,
    private val telefoneAcolhida: ArrayList<String?>,
    private val emailAcolhida: ArrayList<String?>,
    private val publicoAcolhida: ArrayList<String?>,
    private val acolhimentoAcolhida: ArrayList<String?>,
    private val atividadesAcolhida: ArrayList<String?>
):
    RecyclerView.Adapter<AcolhidaAdapter.AcolhidaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcolhidaViewHolder {
        val itemLista = AcolhidaVhBinding.inflate(LayoutInflater.from(context), parent, false)
        return AcolhidaViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: AcolhidaViewHolder, position: Int) {
        holder.nomeCasa.text = nomeAcolhida[position]
        holder.endereco.text = enderecoAcolhida[position]
        holder.publicoAlvo.text = publicoAcolhida[position]
        holder.email.text = emailAcolhida[position]
        holder.telefone.text = telefoneAcolhida[position]
        holder.acolhimento.text = acolhimentoAcolhida[position]
        holder.atividades.text = atividadesAcolhida[position]

        holder.card.setOnClickListener{
            context.startActivity(AcolhidaDetalheActivity.startAcolhidaDetalhe(
                context,
                nomeAcolhida[position],
                enderecoAcolhida[position],
                publicoAcolhida[position],
                telefoneAcolhida[position],
                emailAcolhida[position],
                atividadesAcolhida[position]))
        }
    }

    override fun getItemCount() = nomeAcolhida.size

    inner class AcolhidaViewHolder(binding: AcolhidaVhBinding): RecyclerView.ViewHolder(binding.root){
        val nomeCasa = binding.nomeCasa
        val endereco = binding.enderecoAcolhida
        val publicoAlvo = binding.publicoAlvo
        val card = binding.cardAcolhida
        val telefone = binding.telefoneDetalheAcolhida
        val email = binding.emailDetalheAcolhida
        val acolhimento = binding.acolhimentoDetalheAcolhida
        val atividades = binding.atividadesDetalheAcolhida
    }

}