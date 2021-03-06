package br.senai.sp.jandira.imcapp20_a.dao

import android.content.ContentValues
import android.content.Context
import android.util.Log
import br.senai.sp.jandira.imcapp20_a.model.Biometria
import br.senai.sp.jandira.imcapp20_a.model.Usuario
import br.senai.sp.jandira.imcapp20_a.utils.*
import java.time.Duration
import java.time.LocalDate
import java.time.Period
import kotlin.math.log

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
        dados.put("foto", converterBitmapParaByteArray(usuario.foto))

        // *** Executar o comando de gravação
        db.insert("tb_usuario", null, dados)

        db.close()
    }

    fun autenticar(email: String, senha: String) : Boolean {
        // *** Obter uma instância de leitura do banco
        val db = dbHelper.readableDatabase

        // *** Determinar quais são as colunas da tabela
        // *** que nós queremos no resultado
        // *** Vamos criar uma projeção
        val campos = arrayOf(
            "id",
            "email",
            "senha",
            "nome",
            "profissao",
            "data_nascimento",
        "foto")

        // *** Vamos definir o filtro da consulta
        // *** O que estamos fazendo é construir o filtro
        // *** "WHERE email = ? AND senha = ?"
        val filtro = "email = ? AND senha = ?"

        // *** Vamos criar agora o argumentos do filtro
        // *** vamos dizer ao Kotlin quais serão os valores
        // *** que deverão ser substituídos pelas "?" no filtro
        val argumentos = arrayOf(email, senha)

        // *** Executar a consulta e obter o resultado
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

       // Log.i("XPTO", "Linhas ${cursor.count.toString()}")

        // *** Guardar a quantidade de linhas obtidas na consulta
        val linhas = cursor.count

        var autenticado = false;

        if (linhas > 0) {
            autenticado = true
            cursor.moveToFirst()

            val idIndex = cursor.getColumnIndex("id")
            val emailIndex = cursor.getColumnIndex("email")
            val nomeIndex = cursor.getColumnIndex("nome")
            val profissaoIndex = cursor.getColumnIndex("profissao")
            val dataNascimentoIndex = cursor.getColumnIndex("data_nascimento")
            val fotoIndex = cursor.getColumnIndex("foto")

            val dataNascimento = cursor.getString(dataNascimentoIndex)

            // Criação/atualização do sharedPreferences que será
            // utilizado no restante da aplicação
            val dados = context.getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
            val editor = dados.edit()

            editor.putInt("id", cursor.getString(idIndex).toInt())
            editor.putString("nome", cursor.getString(nomeIndex))
            editor.putString("email", cursor.getString(emailIndex))
            editor.putString("profissao", cursor.getString(profissaoIndex))
            editor.putString("idade", obterDiferencaEntredateEmAnos(dataNascimento))
            editor.putInt("peso", 0)

            //Converter o byteArray do banco em bitmap
            var bitmap = converterByteArrayParaBitmap(cursor.getBlob(fotoIndex))

            Log.i("XTPO", converterBitmapParaBase64(bitmap))

            editor.putString("foto", converterBitmapParaBase64(bitmap))
            editor.apply()
        }

        db.close()
        return autenticado

    }

}