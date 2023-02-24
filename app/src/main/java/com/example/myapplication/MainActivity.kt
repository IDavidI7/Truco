package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var contador = 1

    var contar1 = 0
    var contar2 = 0

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let { view ->
            setContentView(view.root)
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance()).commitNow()
            }
        }


        contagem_mais()

        contagem_menos()



    }




    fun ganhador() {
        fun setVisibilityAndClickable(
            contador: Int,
            vencedor: View,
            botao: Button,
            contadorVencedor: Int,
        ) {
            if (contador == 12) {
                vencedor.visibility = View.VISIBLE
                botao.isClickable = false
            } else {
                vencedor.visibility = View.INVISIBLE
                botao.isClickable = true
            }
        }

        setVisibilityAndClickable(contar1, binding!!.vencedores1, binding!!.botaomais2, 12)
        setVisibilityAndClickable(contar2, binding!!.vencedores2, binding!!.botaomais1, 12)
    }


    fun contagem_mais() {
        maisdeum()
        binding?.botaomais1?.setOnClickListener {
            if (contar1 < 13) {
                contar1 = contar1 + contador
                if (contar1 > 12) {
                    contar1 = 12
                    binding?.contador1?.text = contar1.toString()
                    ganhador()
                }
            }
            binding?.contador1?.text = contar1.toString()
            ganhador()
        }
        binding?.botaomais2?.setOnClickListener {
            if (contar2 < 13) {
                contar2 = contar2 + contador
                if (contar2 > 12) {
                    contar2 = 12
                    binding?.contador2?.text = contar2.toString()
                    ganhador()
                }
            }
            binding?.contador2?.text = contar2.toString()
            ganhador()
        }
    }

    fun contagem_menos() {
        maisdeum()
        binding?.botaomenos1?.setOnClickListener {
            if (contar1 > 0) {
                contar1 = contar1 - contador
                if (contar1 < 0) {
                    contar1 = 0
                    binding?.contador1?.text = contar1.toString()
                    ganhador()
                }
                binding?.contador1?.text = contar1.toString()
                ganhador()
            } else {
                Toast.makeText(this, "Já está em zero!!!", Toast.LENGTH_SHORT).show()
            }
        }
        binding?.botaomenos2?.setOnClickListener {
            if (contar2 > 0) {
                contar2 = contar2 - contador
                if (contar2 < 0) {
                    contar2 = 0
                    binding?.contador2?.text = contar2.toString()
                    ganhador()
                }
                binding?.contador2?.text = contar2.toString()
                ganhador()
            } else {
                Toast.makeText(this, "Já está em zero!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val textViewIdToCounterValue = mapOf(
        R.id.um to 1,
        R.id.truco to 3,
        R.id.seis to 6,
        R.id.nove to 9,
        R.id.doze to 12
    )

    fun maisdeum() {
        buttonInvisible()
        textViewIdToCounterValue.forEach { (textViewId, counterValue) ->
            val textView = findViewById<TextView>(textViewId)
            textView.setOnClickListener {
                this@MainActivity.contador = counterValue
                buttonInvisible()
            }
        }
    }

    fun buttonInvisible() {
        val visibilidades =
            listOf(binding!!.um, binding!!.truco, binding!!.seis, binding!!.nove, binding!!.doze)

        visibilidades.forEach {
            it.visibility = View.GONE
        }
        when (contador) {
            1 -> binding!!.truco.visibility = View.VISIBLE
            3 -> binding!!.seis.visibility = View.VISIBLE
            6 -> binding!!.nove.visibility = View.VISIBLE
            9 -> binding!!.doze.visibility = View.VISIBLE
            12 -> binding!!.um.visibility = View.VISIBLE
        }

    }
}