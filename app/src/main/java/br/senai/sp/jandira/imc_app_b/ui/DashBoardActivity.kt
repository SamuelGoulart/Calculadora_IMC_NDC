package br.senai.sp.jandira.imc_app_b.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.imc_app_b.R
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        carregarDados()
    }

    private fun carregarDados() {
        val  dados:SharedPreferences = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE )

        tvNomePerfil.text = dados.getString("nome", "Não encontrado")
        tv_profissao.text = dados.getString("profissao", "Não encontrado")

        tv_peso.text = dados.getFloat("peso", 0.0f).toString()
        tv_idade.text = dados.getInt("idade", 0).toString()
    }
}