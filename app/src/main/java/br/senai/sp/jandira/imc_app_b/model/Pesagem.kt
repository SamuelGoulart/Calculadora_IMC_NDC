package br.senai.sp.jandira.imc_app_b.model

data class Pesagem(
    var  id: Int = 0,
    var  peso: Double = 0.0,
    var  nivelAtividade: Int = 0,
    var  dataPesagem: String?= null,
    var  Usuario: Usuario
)