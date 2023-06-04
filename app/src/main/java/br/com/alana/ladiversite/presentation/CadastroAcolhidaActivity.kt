package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityCadastroAcolhidaBinding
import br.com.alana.ladiversite.utils.PhoneNumberFormatType
import br.com.alana.ladiversite.utils.PhoneNumberFormatter
import br.com.alana.ladiversite.utils.Utils
import br.com.alana.ladiversite.utils.Utils.Companion.removeNonDigits
import br.com.alana.ladiversite.utils.ValidadorDados
import com.example.mobcomponents.customtoast.CustomToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.ref.WeakReference

class CadastroAcolhidaActivity : AppCompatActivity() {
    private val binding: ActivityCadastroAcolhidaBinding by lazy { ActivityCadastroAcolhidaBinding.inflate(layoutInflater) }
    private val dataBaseFire = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setFullScreen(this@CadastroAcolhidaActivity)
        setContentView(binding.root)
        alterLabelForAdmin()
        getDadosAcolhidaFromAdmin()
        phoneMask()
        binding.cadastrarCasaBtn.setOnClickListener {
            if (binding.edtCasa.text.isEmpty()){
                binding.edtCasa.error = getText(R.string.casa_obrig)
                camposWarning("Verifique o preenchimento do campo NOME DA CASA!")
            } else if(!ValidadorDados.isValidEmailOnly(binding.edtEmail.text.toString())){
            } else if(!binding.edtEmail.text.isEmpty() && !ValidadorDados.isValidEmailOnly(binding.edtEmail.text.toString())){
                binding.edtEmail.error = getText(R.string.email_valid)
                camposWarning("Verifique o preenchimento do campo E-MAIL!")
            } else if (binding.edtTelefone.text.isEmpty() && binding.edtTelefone.text.length < 10) {
                binding.edtTelefone.error = getText(R.string.telefone_obrig)
                camposWarning("Verifique o preenchimento do campo TELEFONE!")
            } else {
                val phone = removeNonDigits(binding.edtTelefone.text.toString())
                val telefone = "+55 $phone"
                if (isUpdate()){
                    val oldCasa = intent.getStringExtra("nomeCasa")
                    if (oldCasa.toString() != binding.edtCasa.text.toString())
                        apagaCasa(oldCasa.toString())
                }
                cadastrarAcolhida(
                    binding.edtCasa.text.toString(),
                    verifyYesNo(),
                    binding.edtAtividades.text.toString(),
                    binding.edtEmail.text.toString(),
                    binding.edtEndereco.text.toString(),
                    binding.edtPublico.text.toString(),
                    telefone,
                    isAdmin(),
                    true
                )
                startActivity(AcolhidaActivity.startAcolhida(this).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            }
        }
    }

    fun verifyYesNo(): String{
        return if (binding.sim.isChecked) "Sim" else "Não"
        if (binding.sim.isChecked) {
            return "Sim"
        } else if (binding.nao.isChecked) {
            return "Não"
        } else {
            return ""
        }
    }

    fun alterLabelForAdmin() {
        if (isAdmin()) {
            binding.labelSugestao.text = getString(R.string.cadastrar_casa_admin)
            binding.cadastrarCasaBtn.text = getString(R.string.cadastrar_admin_btn)
        }
    }

    private fun isAdmin(): Boolean{
        val fireUser = FirebaseAuth.getInstance().currentUser?.uid
        return fireUser!! == "wWYLeLPAb5SMsBob5b7j6oRprwZ2"
    }

    private fun isUpdate(): Boolean{
        return intent.hasExtra("nomeTela")
    }

    private fun apagaCasa(casa: String){
        dataBaseFire.collection("casas").document(casa).delete().addOnSuccessListener {}
            .addOnFailureListener { CustomToast.warning(this, it.toString()) }
    }
    private fun cadastrarAcolhida(casa: String, acolhimento: String, atividades: String, email: String, endere: String, publico: String, tel: String, visivel: Boolean, ativo: Boolean) {
        val casaMap = hashMapOf(
            "nome" to casa,
            "acolhimento" to acolhimento,
            "atividades" to atividades,
            "email" to email,
            "endereco" to endere,
            "publico" to publico,
            "telefone" to tel,
            "visivel" to visivel,
            "ativo" to ativo
        )
        dataBaseFire.collection("casas").document(casa)
            .set(casaMap).addOnCompleteListener {
                CustomToast.success(this, "Enviado com sucesso!")
            }.addOnFailureListener {
                CustomToast.error(this, "Erro no envio dos dados!")
            }
    }

    private fun getDadosAcolhidaFromAdmin(){
        if (intent.hasExtra("nomeTela")){
            binding.cadastrarCasaBtn.text = getString(R.string.cadastrar_casa)
            binding.labelSugestao.text = intent.getStringExtra("nomeTela")
            binding.edtCasa.setText(intent.getStringExtra("nomeCasa"))
            binding.toggleSimNao.check(verifyYesNoForAdmin())
            binding.edtAtividades.setText(intent.getStringExtra("atividades"))
            binding.edtEmail.setText(intent.getStringExtra("email"))
            binding.edtEndereco.setText(intent.getStringExtra("endereco"))
            binding.edtPublico.setText(intent.getStringExtra("publico"))
            binding.edtTelefone.setText(intent.getStringExtra("telefone"))
        }
    }

    fun verifyYesNoForAdmin(): Int{
        return if (intent.getStringExtra("acolhimento") == "Sim") binding.sim.id else binding.nao.id
    }

    fun phoneMask(){
        val editText: EditText = binding.edtTelefone
        val country = PhoneNumberFormatType.PT_BR
        val phoneFormatter = PhoneNumberFormatter(WeakReference(editText), country)
        editText.addTextChangedListener(phoneFormatter)
    }

    private fun camposWarning(msg: String){
        CustomToast.warning(this, msg)
    }

    companion object {
        fun startCadastroAcolhida(context: Context): Intent {
            return Intent(context, CadastroAcolhidaActivity::class.java)
        }
        fun startAcolhidaEdicao(context: Context,
                                nomeTela: String?,
                                nomeCasa: String?,
                                acolhimento: String?,
                                atividades: String?,
                                email: String?,
                                endereco: String?,
                                publico: String?,
                                telefone: String?,
        ): Intent {
            val intentDetalhes = Intent(context, CadastroAcolhidaActivity::class.java)
            intentDetalhes.apply {
                putExtra("nomeTela", nomeTela)
                putExtra("nomeCasa", nomeCasa)
                putExtra("acolhimento", acolhimento)
                putExtra("atividades", atividades)
                putExtra("email", email)
                putExtra("endereco", endereco)
                putExtra("publico", publico)
                putExtra("telefone", telefone)
            }
            return intentDetalhes
        }
    }
}