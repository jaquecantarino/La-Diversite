package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityContatoEmergenciaBinding
import br.com.alana.ladiversite.utils.PhoneNumberFormatType
import br.com.alana.ladiversite.utils.PhoneNumberFormatter
import br.com.alana.ladiversite.utils.Utils.Companion.removeNonDigits
import com.example.mobcomponents.customtoast.CustomToast
import java.lang.ref.WeakReference

class ContatoEmergenciaActivity : AppCompatActivity() {

    private val binding: ActivityContatoEmergenciaBinding by lazy {
        ActivityContatoEmergenciaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(binding.root)

        binding.edtNomeContato.setText(verificaNome())
        binding.edtTelefoneContato.setText(verificaTel())

        phoneMask()
        isCadastrado()

        binding.cadastrarContatoBtn.setOnClickListener {
            verificaCampos()
        }

        binding.atualizarContatoBtn.setOnClickListener {
            if (binding.atualizarContatoBtn.visibility == View.VISIBLE) {
                CustomToast.success(this, "Contato atualizado!")
                cadastrarContato(
                    binding.edtNomeContato.text.toString(),
                    binding.edtTelefoneContato.text.toString()
                )
            }

        }
    }

    private fun verificaNome(): String? {
        val sharedPreferences = getSharedPreferences("contato", MODE_PRIVATE)
        return sharedPreferences.getString("contatoNome", "")
    }

    private fun verificaCampos() {
        if (binding.cadastrarContatoBtn.text.toString() == getText(R.string.excluir)) {
            CustomToast.info(this, "Contato excluído!")
            cadastrarContato("", "")
        } else {
            CustomToast.success(this, "Contato adicionado!")
            cadastrarContato(
                binding.edtNomeContato.text.toString(),
                binding.edtTelefoneContato.text.toString()
            )
        }
    }


    private fun verificaTel(): String? {
        val sharedPreferences = getSharedPreferences("contato", MODE_PRIVATE)
        return sharedPreferences.getString("contatoTelefone", "")
    }

    private fun isCadastrado() {
        if (binding.edtNomeContato.text.isNotEmpty() && binding.edtTelefoneContato.text.isNotEmpty()) {
            binding.cadastrarContatoBtn.text = getText(R.string.excluir)
            binding.atualizarContatoBtn.visibility = View.VISIBLE
        } else {
            binding.cadastrarContatoBtn.text = getText(R.string.confirmar)
            binding.atualizarContatoBtn.visibility = View.GONE
        }
    }

    fun phoneMask() {
        val editText: EditText = binding.edtTelefoneContato
        val country = PhoneNumberFormatType.PT_BR
        val phoneFormatter = PhoneNumberFormatter(WeakReference(editText), country)
        editText.addTextChangedListener(phoneFormatter)
    }


    private fun cadastrarContato(nome: String, telefone: String) {
        val sharedPreferences = getSharedPreferences("contato", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("contatoNome", nome)
        editor.putString("contatoTelefone", removeNonDigits(telefone))
        editor.apply()
        finish()
    }

    companion object {
        fun startContatoEmergencia(context: Context): Intent {
            return Intent(context, ContatoEmergenciaActivity::class.java)
        }
    }

}