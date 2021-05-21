package br.senai.sp.jandira.imc_app_b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.*
import java.security.acl.Group

class NcdActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ncd)

        val buttonCalcular: Button = findViewById(R.id.button_calcular_ncd)
        val editTextPeso: EditText = findViewById(R.id.edit_Text_Peso_Ncd)
        val spinnerIdade: Spinner = findViewById(R.id.spinner_faixa_idade)
        val spinnerAtividadeFisica: Spinner = findViewById(R.id.spinner_grau_atividade_fisica)
        val radioButtonMasculino: RadioButton = findViewById(R.id.radio_Button_Masculino)

        var grauAtividadeFisica: Int = 0
        var faixaEtaria: Int = 0

        title = "Calcular NCD"

        buttonCalcular.setOnClickListener {

            faixaEtaria = spinnerIdade.selectedItemPosition
            grauAtividadeFisica = spinnerAtividadeFisica.selectedItemPosition

            if (editTextPeso.text.isEmpty()) {
                editTextPeso.error = "Você não me disse o seu peso!"

            } else if (faixaEtaria == 0) {
                Toast.makeText(this, "Faixa etária de idade incorreto", Toast.LENGTH_SHORT).show()
            } else if (grauAtividadeFisica == 0) {
                Toast.makeText(this, "Grau de atividade diárias inválida", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(this, activity_resultado_ncd::class.java)

                intent.putExtra("pesoNcd", editTextPeso.text.toString().toDouble())
                intent.putExtra("faixaEtariaIdade", faixaEtaria)
                intent.putExtra("grauAtividadeFisica", grauAtividadeFisica)

                if (radioButtonMasculino.isChecked()) {
                    val sexo = 'm'
                    intent.putExtra("sexo", sexo)
                } else {
                    val sexo = 'f'
                    intent.putExtra("sexo", sexo)
                }

                startActivity(intent)
            }

        }

    }
}

