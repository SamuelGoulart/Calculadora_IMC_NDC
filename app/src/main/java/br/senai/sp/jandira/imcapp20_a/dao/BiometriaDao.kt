package br.senai.sp.jandira.imcapp20_a.dao

import android.content.ContentValues
import android.content.Context
import br.senai.sp.jandira.imcapp20_a.model.Biometria

class BiometriaDao(val context: Context, val biometria: Biometria?) {

    val dbHelper = ImcDataBase.getDatabase(context)

    fun gravarBiometria() {

        val db = dbHelper.writableDatabase

        val dados = ContentValues()
        dados.put("peso", biometria!!.peso)
        dados.put("nivel_atividade", biometria.nivelAtiviade)
        dados.put("data_pesagem", biometria.dataPesagem.toString())
        dados.put("id_usario", biometria.usuario.id.toString())

        // *** Executar o comando de gravação
        db.insert("tb_biometria", null, dados)

        db.close()
    }


    fun gravarDadosNoSharedPreferences() : Boolean {
        // *** Obter uma instância de leitura do banco
        val db = dbHelper.readableDatabase

        // *** Determinar quais são as colunas da tabela
        // *** que nós queremos no resultado
        // *** Vamos criar uma projeção
        val campos = arrayOf(
            "id",
            "peso",
            "nivel_atividade",
            "data_pesagem",
            "id_usuario"
        )

        val cursor = db.query(
            "tb_biometria",
            campos,
            null,
            null,
            null,
            null,
            null
        )

        val linhas = cursor.count

        var confirmado = false

        if (linhas > 0) {
            confirmado = true
            cursor.moveToFirst()

            val idIndex = cursor.getColumnIndex("id")
            val pesoIndex = cursor.getColumnIndex("peso")
            val nivelAtividadeIndex = cursor.getColumnIndex("nivel_atividade")
            val dataPesagemIndex = cursor.getColumnIndex("data_pesagem")
            val idUsuarioIndex = cursor.getColumnIndex("id_usuario")

            val dados = context.getSharedPreferences("dados_biometria", Context.MODE_PRIVATE)
            val editor = dados.edit()

            editor.putInt("id", cursor.getInt(idIndex))
            editor.putFloat("peso", cursor.getFloat(pesoIndex))
            editor.putInt("nivel_atividade", cursor.getInt(nivelAtividadeIndex))
            editor.putString("data_pesagem", cursor.getString(dataPesagemIndex))
            editor.putInt("id_usuario", cursor.getInt(idUsuarioIndex))

            editor.apply()
        }

        db.close()
        return confirmado

    }







}