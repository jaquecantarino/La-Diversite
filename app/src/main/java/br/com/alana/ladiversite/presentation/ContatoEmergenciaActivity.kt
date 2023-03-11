package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityContatoEmergenciaBinding
import com.example.mobcomponents.customtoast.CustomToast

class ContatoEmergenciaActivity : AppCompatActivity() {

    private val binding: ActivityContatoEmergenciaBinding by lazy {
        ActivityContatoEmergenciaBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        binding.edtNomeContato.setText(verificaNome())
        binding.edtTelefoneContato.setText(verificaTel())
        isCadastrado()
        binding.cadastrarContatoBtn.setOnClickListener {
            if (binding.cadastrarContatoBtn.text.toString() == getText(R.string.excluir)){
                cadastrarContato("", "")
            } else {
                cadastrarContato(binding.edtNomeContato.text.toString(),
                    binding.edtTelefoneContato.text.toString())
            }
        }
    }
    
    private fun verificaNome(): String?{
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        return sharedPreferences.getString("contatoNome", "")
    }

    private fun verificaTel(): String?{
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        return sharedPreferences.getString("contatoTelefone", "")
    }

    private fun isCadastrado(){
        if (binding.edtNomeContato.text.isNotEmpty() && binding.edtTelefoneContato.text.isNotEmpty()){
            binding.cadastrarContatoBtn.text = getText(R.string.excluir)
        } else {
            binding.cadastrarContatoBtn.text = getText(R.string.confirmar)
        }
    }

    private fun cadastrarContato(nome: String, telefone: String) {
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("contatoNome", nome)
        editor.putString("contatoTelefone", telefone)
        editor.commit()
        finish()
        CustomToast.success(this, "Contato atualizado!")
    }

    companion object {
        fun startContatoEmergencia(context: Context): Intent {
            return Intent(context, ContatoEmergenciaActivity::class.java)
        }
    }

}