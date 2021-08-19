package br.senai.sp.jandira.imcapp20_a.ui

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

        bt_gravar.setOnClickListener {
            validaForm()
            // Testar se o formulário está preenchido corretamente


            // *** Criar o sharedPreferences
//            val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
//
//            val editor = dados.edit()
//            editor.putString("nome", et_nome.text.toString())
//            editor.putString("profissao", et_profissao.text.toString())
//            editor.putInt("peso", et_peso.text.toString().toInt())
//            editor.putInt("idade", et_data_nascimento.text.toString().toInt())
//            editor.putString("email", et_email.text.toString())
//            editor.putString("senha", et_senha.text.toString())
//            editor.apply()
            // Gravar o novo usuário no banco de dados SQLite
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