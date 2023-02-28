package br.com.alana.ladiversite.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alana.ladiversite.databinding.ActivityAdminBinding


class AdminActivity : AppCompatActivity() {

    private val binding: ActivityAdminBinding by lazy { ActivityAdminBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerAcolhida.layoutManager = LinearLayoutManager(this@AdminActivity)
        binding.recyclerAcolhida.setHasFixedSize(true)
    }

    companion object {
        fun startAdmin(context: Context): Intent {
            return Intent(context, AdminActivity::class.java)
        }
    }
}