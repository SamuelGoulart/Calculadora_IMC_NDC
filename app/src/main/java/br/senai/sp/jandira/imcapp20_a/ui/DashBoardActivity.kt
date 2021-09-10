package br.senai.sp.jandira.imcapp20_a.ui

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.utils.converterBase64ParaBitmap
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.layout_alert_dialog.*

class DashBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        preencherDashBoard()
        genero_preenchido()

        tv_logout.setOnClickListener {
            val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
            val editor = dados.edit()
            editor.putBoolean("lembrar", false)
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun preencherDashBoard() {
        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

        tv_profile_name.text = dados.getString("nome", "")
        tv_profile_occupation.text = dados.getString("profissao", "")
        tv_weight.text = dados.getInt("peso", 0).toString()
        tv_age.text = dados.getString("idade", "")

        val imagemBase64 = dados.getString("foto", "")
        val imageBitmap = converterBase64ParaBitmap(imagemBase64)

        iv_profile.setImageBitmap(imageBitmap)

        // *** Colocar foto do Github no ImageView
//        val url = "https://avatars.githubusercontent.com/u/14265058?v=4"
//        Glide.with(this).load(url).into(iv_profile)

    }

    private fun genero_preenchido() {
        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

        val genero = dados.getString("genero", "")

        if (genero == "") {
            abrir_dialog()
        }
    }


    private fun abrir_dialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage(R.string.titulo_dialog)

            .setPositiveButton(R.string.signin,
                DialogInterface.OnClickListener { dialog, id ->
                    abrir_dialog_peso()
                })

            .setNegativeButton(R.string.cancel,
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

        val dialog = alertDialog.create()
        dialog.show()

    }

    private fun abrir_dialog_peso() {
        val alertDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.layout_alert_dialog, null)

        alertDialog.setView(view)

            .setPositiveButton(R.string.signin,
                DialogInterface.OnClickListener { dialog, id ->
                })

            .setNegativeButton(R.string.cancel,
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

        val dialog = alertDialog.create()
        dialog.show()

    }


}