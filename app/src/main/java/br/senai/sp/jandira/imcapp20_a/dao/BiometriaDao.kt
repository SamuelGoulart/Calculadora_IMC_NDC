package br.senai.sp.jandira.imcapp20_a.dao

import android.content.ContentValues
import android.content.Context
import br.senai.sp.jandira.imcapp20_a.model.Biometria

class BiometriaDao(val context: Context, val biometria: Biometria?) {

    val dbHelper = ImcDataBase.getDatabase(context)

    public fun gravarBiometria() {


        val db = dbHelper.writableDatabase

        val dados = ContentValues()
        dados.put("peso", biometria!!.peso)
        dados.put("nivelAtiviade", biometria.nivelAtiviade)
    }

}