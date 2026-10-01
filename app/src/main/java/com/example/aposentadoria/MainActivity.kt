package com.example.aposentadoria

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.aposentadoria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.generos,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spinnerGenero.adapter = adapter

        binding.btnCalcular.setOnClickListener {
            calcularAposentadoria()
        }

        binding.btnLimpar.setOnClickListener {
            binding.edtIdade.text?.clear()
            binding.spinnerGenero.setSelection(0)
            binding.txtResultado.setText(R.string.resultado_inicial)
            binding.layoutIdade.error = null
        }
    }

    private fun calcularAposentadoria() {

        val idadeTexto = binding.edtIdade.text.toString()

        if (idadeTexto.isEmpty()) {
            binding.layoutIdade.error = "Informe sua idade."
            return
        }

        if (binding.spinnerGenero.selectedItemPosition == 0) {
            binding.txtResultado.text = "Selecione o gênero."
            return
        }

        binding.layoutIdade.error = null

        val idade = idadeTexto.toInt()

        val idadeMinima = if (
            binding.spinnerGenero.selectedItemPosition == 1
        ) {
            65
        } else {
            62
        }

        val resultado = idadeMinima - idade

        if (resultado < 0) {
            binding.txtResultado.text =
                "Você já poderia estar aposentado."
        } else {
            binding.txtResultado.text =
                "Faltam $resultado anos para você se aposentar."
        }
    }
}