package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityContatoEmergenciaBinding
import br.com.alana.ladiversite.utils.PhoneNumberFormatType
import br.com.alana.ladiversite.utils.PhoneNumberFormatter
import br.com.alana.ladiversite.utils.Utils
import br.com.alana.ladiversite.utils.Utils.Companion.removeNonDigits
import br.com.alana.ladiversite.utils.ValidadorDados
import com.example.mobcomponents.customtoast.CustomToast
import java.lang.ref.WeakReference


class ContatoEmergenciaActivity : AppCompatActivity() {

    private val binding: ActivityContatoEmergenciaBinding by lazy { ActivityContatoEmergenciaBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@ContatoEmergenciaActivity)
        setContentView(binding.root)

        binding.edtNomeContato.setText(verificaNome())
        binding.edtTelefoneContato.setText(verificaTel())

        phoneMask()
        isCadastrado()

        binding.cadastrarContatoBtn.setOnClickListener {
            verificaCampos()
        }

        binding.atualizarContatoBtn.setOnClickListener {
            if (binding.atualizarContatoBtn.isVisible){
                validaECadastra()
            }
        }
    }

    private fun verificaNome(): String?{
        val sharedPreferences = getSharedPreferences("contato", MODE_PRIVATE)
        return sharedPreferences.getString("contatoNome", "")
    }

    private fun verificaCampos(){
        if (binding.cadastrarContatoBtn.text.toString() == getText(R.string.excluir)){
            CustomToast.info(this, "Contato excluído!")
            cadastrarContato("", "")
        } else {
            validaECadastra()
        }
    }

    private fun validaECadastra(){
        if (binding.edtNomeContato.text.toString().isEmpty()) {
            CustomToast.warning(this, "Campo nome nao pode estar vazio!")
        } else if (removeNonDigits(binding.edtTelefoneContato.text.toString()).length < 10){
            CustomToast.warning(this, "Campo telefone incorreto!")
        } else {
            if (binding.atualizarContatoBtn.isVisible){
                CustomToast.success(this, "Contato atualizado com sucesso!")
            } else {
                CustomToast.success(this, "Contato cadastrado com sucesso!")
            }
            cadastrarContato(binding.edtNomeContato.text.toString(), binding.edtTelefoneContato.text.toString())
        }
    }

    private fun verificaTel(): String?{
        val sharedPreferences = getSharedPreferences("contato", MODE_PRIVATE)
        return sharedPreferences.getString("contatoTelefone", "")
    }

    private fun isCadastrado(){
        if (binding.edtNomeContato.text.isNotEmpty() && binding.edtTelefoneContato.text.isNotEmpty()){
            binding.cadastrarContatoBtn.text = getText(R.string.excluir)
            binding.atualizarContatoBtn.visibility = View.VISIBLE
        } else {
            binding.cadastrarContatoBtn.text = getText(R.string.confirmar)
            binding.atualizarContatoBtn.visibility = View.GONE
        }
    }

    fun phoneMask(){
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


