package br.senai.sp.jandira.imc_app_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class activity_resultado_ncd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_ncd)

        title = "Resultado do NDC"

        val textViewNcd: TextView = findViewById(R.id.text_view_resultado_ncd)
        val textViewDica: TextView = findViewById(R.id.text_view_dica)

        val pesoNcd = intent.getDoubleExtra("pesoNcd", 3.9)
        val faixaEtariaIdade = intent.getIntExtra("faixaEtariaIdade", 0)
        val grauAtividadeFisica = intent.getIntExtra("grauAtividadeFisica", 0)
        val sexo = intent.getCharExtra("sexo", '?')

        val  tmb = taxaMetabolismoBasal(pesoNcd, faixaEtariaIdade, sexo)

        val resultadoNcd = ncd(tmb, grauAtividadeFisica, sexo)

        val dicaNcd = getDicaNcd()

        textViewNcd.text = String.format("%.1f", resultadoNcd)
        textViewDica.text = dicaNcd[0]

    }
}