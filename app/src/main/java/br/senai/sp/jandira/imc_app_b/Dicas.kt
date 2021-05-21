package br.senai.sp.jandira.imc_app_b

import java.util.*


fun getDica() : List<String> {

    val dicas = listOf(
        listOf("Alimente-se melhor", "Reduza alimentos com gordura, açúcar e sal. Tenha sempre frutas, verduras e legumes na alimentação diária."),
        listOf("Movimente-se", "Inicie uma atividade física. Evite escadas rolantes e elevadores e suba a pé."),
        listOf("Beba água", "Não espere ter sede para beber água. Quando você tem sede, significa que o corpo já está desidratado."),
        listOf("Durma bem", "Crie um ritual, deixe o ambiente escuro e silencioso. Quem dorme pouco e tem uma qualidade de sono ruim, tem mais chances de aumentar o peso.")
    )

    val sorteio = Random()
    val dicaDoDia = sorteio.nextInt(dicas.size)

    return dicas[dicaDoDia]

}

fun getDicaNcd() : List<String> {

    val dicas = listOf(
        listOf("Temperar a salada com 1 colher de sobremesa de azeite de oliva extra virgem."),
        listOf("Para preparo das carnes, prefira grelhado, cozido, ensopado ou assado."),
        listOf(" Evite açúcar e farinha branca."),
        listOf("Beba 2 litros de água por dia entre as refeições."),
        listOf("Quando sair para comer fora: Escolher pratos que acompanhem legumes e usar bebida alcoólica com moderação (intercalada com 1 copo de água)."),
        listOf("Procure manter a \"dieta\" de segunda à sexta-feira, mas cuidado com os excessos de final de semana. Comer de “tudo”, mas cuidar com as quantidades.")
    )

    val sorteio = Random()
    val dicaDoDia = sorteio.nextInt(dicas.size)

    return dicas[dicaDoDia]

}