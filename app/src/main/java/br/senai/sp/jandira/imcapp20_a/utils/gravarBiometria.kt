//package br.senai.sp.jandira.imcapp20_a.utils
//
//import android.widget.Toast
//import br.senai.sp.jandira.imcapp20_a.dao.UsuarioDao
//import br.senai.sp.jandira.imcapp20_a.model.Biometria
//import br.senai.sp.jandira.imcapp20_a.model.Usuario
//import kotlinx.android.synthetic.main.activity_novo_usuario.*
//
//fun gravarBiometria(peso: Double, nivelDeAtividade: Int){
//    val biometria = Biometria(
//        0,
//        peso.toString(),
//        nivelDeAtividade.toString()
//
//    val dao = BiometriaDao(this, biometria)
//    dao.gravar()
//
//    Toast.makeText(this, "Dados gravados com sucesso!!", Toast.LENGTH_SHORT).show()
//
//    finish()
//
//}
//}