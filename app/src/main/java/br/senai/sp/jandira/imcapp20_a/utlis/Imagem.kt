package br.senai.sp.jandira.imcapp20_a.utlis

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

fun convertBitmapParaByteArray(imagem: Bitmap?): ByteArray? {

    val stream = ByteArrayOutputStream()

    if (imagem != null){
        val imageArray = imagem.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }

    return null

}