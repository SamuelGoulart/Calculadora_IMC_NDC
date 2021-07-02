package br.senai.sp.jandira.imc_app_b.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.imc_app_b.R
import kotlinx.android.synthetic.main.activity_login.*

class activity_login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tv_criar_conta.setOnClickListener {
            val intent = Intent(this, novoUsuarioActivity::class.java)
            startActivity(intent)

        }

        button_Entrar.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val login:String = editText_email.text.toString()
        val senha:String = editText_senha.text.toString()

        val dados:SharedPreferences = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)


        val  loginGravada:String = dados.getString("email", "Não existe").toString()
        val  senhaGravada:String = dados.getString("senha", "Não existe").toString()

        if (login == loginGravada && senha == senhaGravada){
            val intent = Intent(this, DashBoardActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Usuário ou senha incorreto", Toast.LENGTH_SHORT).show()
        }

    }
}