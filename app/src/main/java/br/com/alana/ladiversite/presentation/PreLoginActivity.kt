package br.com.alana.ladiversite.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import br.com.alana.ladiversite.databinding.ActivityPreLoginBinding

//classe da tela inicial, onde temos os botões login, cadastro e emergencia 190
class PreLoginActivity : AppCompatActivity() {

    private val binding: ActivityPreLoginBinding by lazy { ActivityPreLoginBinding.inflate(layoutInflater) }
    //metodo padrão de toda classe android (?)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        //instanciando os botões as ações que podemos executar dentro dessa tela
        binding.loginBtn.setOnClickListener { goToLogin() } //linha 27
        binding.cadastreBtn.setOnClickListener { goToCadastroUser() } //linha 31
        binding.emergenciaBtn.setOnClickListener { call190() } // linha 35

    }

    fun goToLogin(){ //ação do botão na tela inicial, nesse caso ao clicar no botão chama a classe login
        startActivity(LoginActivity.startLogin(this)) //o startActivity inicia uma instancia, nesse caso chamamos a ação de login
    }

    fun goToCadastroUser(){ //ação do botão dentro da tela inicial, nesse caso ao clicar no botão chama ação cadastro
        startActivity(UsuarioCadastroActivity.startCadastroUser(this))//o startActivity inicia uma instancia, nesse caso chamamos a classe de cadastro e em seguida usamos o metodo dela para iniciar a ação de cadastro
    }

    fun call190(){ //botão de emergencia
        val phone = "190" //preenche o campo com valor do numero da policia
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null)) //cria o intent adicionando a ação de abrir o discador com o ACTION_DIAL
        startActivity(intent) //o startActivity inicia uma instancia, nesse caso o intent criado na linha 34 que abre o discador
    }
}