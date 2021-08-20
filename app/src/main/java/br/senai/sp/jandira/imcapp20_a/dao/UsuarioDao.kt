package br.senai.sp.jandira.imcapp20_a.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import br.senai.sp.jandira.imcapp20_a.model.Usuario
import br.senai.sp.jandira.imcapp20_a.utlis.obterDiferencaEntreDatasEmAnos
import kotlinx.android.synthetic.main.activity_dash_board.*
import java.time.LocalDate
import java.time.Period

class UsuarioDao(val context: Context, val usuario: Usuario?) {

    val dbHelper = ImcDataBase.getDatabase(context)

    public fun gravar() {

        // *** obter uma instância do banco para escrita
        val db = dbHelper.writableDatabase

        // *** Criar os valores que serão inseridos no banco
        val dados = ContentValues()
        dados.put("nome", usuario!!.nome)
        dados.put("profissao", usuario.profissao)
        dados.put("email", usuario.email)
        dados.put("senha", usuario.senha)
        dados.put("altura", usuario.altura)
        dados.put("data_nascimento", usuario.dataNascimento.toString())
        dados.put("sexo", usuario.sexo.toString())

        // *** Executar o comando de gravação
        db.insert("tb_usuario", null, dados)

        db.close()
    }

    fun autenticar(email: String, senha: String): Boolean {
        // *** Obter uma instância de leitura do banco
        val db = dbHelper.readableDatabase


        // *** Derterminar quais são as colunas da tabela
        // *** Que nós queremos no resultado
        // *** Vamos criar uma projeção

        val campos = arrayOf("email",
                             "senha",
                             "nome",
                             "profissao",
                             "data_nascimento")

        // *** Vamos definir o filtro da consulta
        // *** O que estamos fazendo é
        val filtro = "email = ? AND senha = ?"

        // *** Vamos criar agora o argumentos do filtro
        // *** Vamos dizer ao kotlin quais serão os valores
        // *** que deverão ser substituidos pelas "?" no filtro

        val argumentos = arrayOf(email, senha)

        // *** Executar a consulta e obter  o resultado
        // *** em um "cursor"
        val cursor = db.query(
            "tb_usuario",
            campos,
            filtro,
            argumentos,
            null,
            null,
            null
        )

        Log.i("XPTO", "Linhas ${cursor.count.toString()}")

        val linhas = cursor.count

        var  autenticado = false

        if (linhas > 0){
            cursor.moveToFirst()

            val emailIndex = cursor.getColumnIndex("email")
            val nomeIndex = cursor.getColumnIndex("nome")
            val profissaoIndex = cursor.getColumnIndex("profissao")
            val dataNascimentoIndex = cursor.getColumnIndex("data_nascimento")

            val dataNacimento = cursor.getString(dataNascimentoIndex)
           // Criação/ Atualização do SharedPreferences
            // Utilização no restante da aplicação
            val dados =  context.getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
            val  editor = dados.edit()
            editor.putString("nome", cursor.getString(nomeIndex))
            editor.putString("email", cursor.getString(emailIndex))
            editor.putString("profissao", cursor.getString(profissaoIndex))
            editor.putString("idade", obterDiferencaEntreDatasEmAnos(dataNacimento))
            editor.putInt("peso", 0)

            Log.i("XPTO", cursor.getString(emailIndex))
            autenticado = true

        }

        db.close()
        return autenticado
    }


}