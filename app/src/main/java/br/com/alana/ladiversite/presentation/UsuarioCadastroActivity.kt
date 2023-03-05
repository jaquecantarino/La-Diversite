package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import br.com.alana.ladiversite.R
import br.com.alana.ladiversite.databinding.ActivityUsuarioCadastroBinding
import br.com.alana.ladiversite.utils.ValidadorDados
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.android.HandlerDispatcher
import org.w3c.dom.Text

//Arquivo de ação, controla o efetuar cadastro - classe de cadastro
class UsuarioCadastroActivity : AppCompatActivity() { //extende o arquivo AppCompat, que é um arquivo padrão do android (quais configs ele traz?)

    private val binding: ActivityUsuarioCadastroBinding by lazy { ActivityUsuarioCadastroBinding.inflate(layoutInflater) } //instancia o visual da pagina, que vem da pasta layout
    private lateinit var auth: FirebaseAuth //instancia o Firebase

    //metodo create, aqui ele ta instanciando a parte da tela
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        auth = Firebase.auth //validador

        binding.cadastrarBtn.setOnClickListener {
           cadastrarUser()
        }

    }
    //metodo oficial, onde os atributos são definidos, neste caso e-mail e senha,
    private fun efetuaCadastro(email: String, senha: String ){
        binding.cadastrarBtn.isEnabled = false
        if (!ValidadorDados.isValidEmail(email)){
            binding.emailCadastro.error = getText(R.string.email_valid)
            binding.cadastrarBtn.isEnabled = true
        } else if (!ValidadorDados.isValidSenha(senha)){
            binding.senhaCadastro.error = getText(R.string.senha_valid)
            binding.cadastrarBtn.isEnabled = true
        } else {
            auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this){
                    task ->
                if (task.isSuccessful){
                    binding.animCad.visibility = View.VISIBLE
                    Toast.makeText(this, getText(R.string.cadastro_ok),
                        Toast.LENGTH_SHORT).show()
                    delay()
                } else {
                    Toast.makeText(this, getText(R.string.cadastro_nok),
                        Toast.LENGTH_SHORT).show()
                    clearFields() //depois do login efetuado limpa os campo com o metodo criado na linha 87
                    binding.cadastrarBtn.isEnabled = true
                }
            }
        }
    }

    private fun cadastrarUser(){
        //inicio das validações para cadastro
        if (TextUtils.isEmpty(binding.emailCadastro.text)){
            binding.emailCadastro.error = getText(R.string.email_obrigatorio) //validação de string vem do xml da pasta values no res
            binding.cadastrarBtn.isEnabled = true
        } else if (TextUtils.isEmpty(binding.senhaCadastro.text)) {
            binding.senhaCadastro.error = getText(R.string.senha_obrigatorio)
            binding.cadastrarBtn.isEnabled = true //isEnabled confere se o preenchimento automático está ativado
        } else {
            efetuaCadastro(binding.emailCadastro.text.toString(),
                binding.senhaCadastro.text.toString())
        }
    }

    private fun delay(){ //? - tempo para carregar a pagina
        Handler().postDelayed({
            finish()
        }, 2000)
    }

    private fun clearFields(){ //metodo criado para limpar os campos
        binding.emailCadastro.text.clear()
        binding.senhaCadastro.text.clear()
    }

    //metodo criado para chamada na tela inicial pelo botão
    companion object {
        fun startCadastroUser(context: Context): Intent {
            val intent = Intent(context, UsuarioCadastroActivity::class.java)
            return intent
        }
    }
}