package br.senai.sp.jandira.imc_app_b

fun taxaMetabolismoBasal(pesoNcd: Double, faixaEtariaIdade: Int, sexo: Char):Double {

    if (sexo == 'm'){
       if (faixaEtariaIdade == 1) {
           return 15.3* pesoNcd + 679
       }else if (faixaEtariaIdade == 2){
           return 11.6 * pesoNcd + 879
       }else if (faixaEtariaIdade == 3){
           return 13.5 * pesoNcd + 487
       }
    }else {
        if (faixaEtariaIdade == 1) {
            return 14.7* pesoNcd + 496
        }else if (faixaEtariaIdade == 2){
            return 8.7 * pesoNcd + 829
        }else if (faixaEtariaIdade == 3){
            return 10.5 * pesoNcd + 596
        }
    }

    return 0.0
}

fun ncd(taxaMetabolismoBasal: Double, grauAtividadeFisica: Int, sexo: Char):Double{
      if (sexo == 'm'){
          if (grauAtividadeFisica == 1){
              return taxaMetabolismoBasal * 1.5
          }else if(grauAtividadeFisica == 2){
              return  taxaMetabolismoBasal * 1.8
          }else if (grauAtividadeFisica == 3){
              return taxaMetabolismoBasal * 2.1
          }
      }else{
          if (grauAtividadeFisica == 1){
              return taxaMetabolismoBasal * 1.6
          }else if(grauAtividadeFisica == 2){
              return  taxaMetabolismoBasal * 1.6
          }else if (grauAtividadeFisica == 3){
              return taxaMetabolismoBasal * 1.8
          }
      }
    return 0.0
}