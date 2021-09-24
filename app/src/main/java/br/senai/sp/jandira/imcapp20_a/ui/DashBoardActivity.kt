package br.senai.sp.jandira.imcapp20_a.ui

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.dao.BiometriaDao
import br.senai.sp.jandira.imcapp20_a.dao.UsuarioDao
import br.senai.sp.jandira.imcapp20_a.model.Biometria
import br.senai.sp.jandira.imcapp20_a.model.Usuario
import br.senai.sp.jandira.imcapp20_a.utils.converterBase64ParaBitmap
import kotlinx.android.synthetic.main.activity_dash_board.*
import java.time.LocalDate

class DashBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        genero_preenchido()

        // Remover a AppBar
        supportActionBar!!.hide()

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

        val dao = BiometriaDao(this, null)
        val confirmado = dao.gravarDadosNoSharedPreferences()

        if (confirmado){
            val dados = getSharedPreferences("dados_biometria", Context.MODE_PRIVATE)

            val peso = dados.getInt("peso", 0)

            if (peso == 0) {
                abrir_dialog()
            }
        }

        preencherDashBoard()
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
        val editText = view.findViewById<EditText>(R.id.editTextpeso)
        //val spinAtividadde: Spinner = findViewById(R.id.spinner_nivel_atividades)

        val buttonGravar = view.findViewById<Button>(R.id.btn_submit_peso)

        alertDialog.setView(view)

        val dialog = alertDialog.create()
        dialog.show()


        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

        val email = dados.getString("email", "")
        val senha = dados.getString("senha", "")
        val nome = dados.getString("nome", "")
        val profissao = dados.getString("profissao", "")
//      val altura = dados.getFloat("altura", "0.0")
        val dataNascimento = dados.getString("idade", "")
        val sexo = dados.getString("sexo", "F")

        val usuario = Usuario(
            0,
            email.toString(),
            senha.toString(),
            nome.toString(),
            profissao.toString(),
            1.74,
            dataNascimento.toString(),
            'M'
        )



       // var nivelAtividade: Int = 0
        //nivelAtividade = spinAtividadde!!.selectedItemPosition


        buttonGravar.setOnClickListener {
            val biometria = Biometria(
                0,
                editText.text.toString().toDouble(),
                0,
                LocalDate.now(),
                usuario)

            val dao = BiometriaDao( this, biometria)
            dao.gravarBiometria()

            Toast.makeText(this, "Dados gravados com sucesso!!", Toast.LENGTH_SHORT).show()

            dialog.cancel()
        }

    }


}