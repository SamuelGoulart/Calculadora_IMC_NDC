package br.senai.sp.jandira.imcapp20_a.ui

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.dao.UsuarioDao
import br.senai.sp.jandira.imcapp20_a.model.Usuario
import kotlinx.android.synthetic.main.activity_novo_usuario.*
import java.util.*

class NovoUsuarioActivity : AppCompatActivity() {

    //latenit vaz a inicialização ocorrer mais tarde
    private lateinit var editTextNome: EditText
    private lateinit var editTextPeso: EditText
    private lateinit var editTextAltura: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextProfissao: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var editTextDataNascimento: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_usuario)

        editTextNome = findViewById(R.id.et_nome)
        editTextPeso = findViewById(R.id.et_peso)
        editTextAltura = findViewById(R.id.et_altura)
        editTextEmail = findViewById(R.id.et_email)
        editTextProfissao = findViewById(R.id.et_profissao)
        editTextSenha = findViewById(R.id.et_senha)
        editTextDataNascimento = findViewById(R.id.et_data_nascimento)

        //Criar um calendário
        val calendario = Calendar.getInstance()
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONDAY)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        // Abrir um componente DatePickerDialog
        et_data_nascimento.setOnClickListener{
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{ view, _ano, _mes, _dia ->
                    var diaZero = "$_dia"
                    var mesZero = "$_mes"
                    if (_dia < 10){
                        diaZero = "0$_dia"
                    }

                    if (_mes < 9){
                         mesZero = "0${_mes + 1}"
                    }
                et_data_nascimento.setText("$diaZero/$mesZero/$_ano")
            },ano, mes, dia)
            dpd.show()
        }


        bt_gravar.setOnClickListener {
            if (validaForm() == false){

                val usuario = Usuario(
                    0,
                    et_email.text.toString(),
                    et_senha.text.toString(),
                    et_nome.text.toString(),
                    et_profissao.text.toString(),
                    et_altura.text.toString().toDouble(),
                    et_data_nascimento.text.toString(),
                    'M',
                    null)

                val dao = UsuarioDao(this, usuario)
                dao.gravar()

                Toast.makeText(this, "Dados gravados com sucesso!!", Toast.LENGTH_SHORT).show()

                finish()
            }
        }

    }

    private fun validaForm() : Boolean{

        var error = false

        if (editTextNome.text.isEmpty()){
            editTextNome.error = "Digite seu nome"
            error = true
        }

        if (editTextPeso.text.isEmpty()){
            editTextPeso.error = "Digite seu peso!"
            error = true
        }

        if (editTextAltura.text.isEmpty()){
            editTextAltura.error = "Digite sua altura"
            error = true
        }

        if (editTextEmail.text.isEmpty()){
            editTextEmail.error = "Digite seu email"
            error = true
        }

        if (editTextProfissao.text.isEmpty()){
            editTextProfissao.error = "Digite o seu email"
        }

        if (editTextSenha.text.isEmpty()){
            editTextSenha.error = "Digite sua senha"
        }

        if (editTextDataNascimento.text.isEmpty()){
            editTextDataNascimento.error = "Digite sua idade"
        }

        return error
    }

}