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

}