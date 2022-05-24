package com.example.calculadora

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Surface
import androidx.appcompat.content.res.AppCompatResources
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private var num1 = 0.0
    private var num2: Double = 0.0
    private var operacion: Int = 0
    private var meBoHe: Boolean = false
    private var meBoBi: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            b1.setOnClickListener { pulsarnumero("1") }
            b2.setOnClickListener { pulsarnumero("2") }
            b3.setOnClickListener { pulsarnumero("3") }
            b4.setOnClickListener { pulsarnumero("4") }
            b5.setOnClickListener { pulsarnumero("5") }
            b6.setOnClickListener { pulsarnumero("6") }
            b7.setOnClickListener { pulsarnumero("7") }
            b8.setOnClickListener { pulsarnumero("8") }
            b9.setOnClickListener { pulsarnumero("9") }
            bcero.setOnClickListener { pulsarnumero("0") }
            bPunto.setOnClickListener { pulsarnumero(".") }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            bHexA.setOnClickListener { pulsarnumero("A") }
            bHexB.setOnClickListener { pulsarnumero("B") }
            bHexC.setOnClickListener { pulsarnumero("C") }
            bHexD.setOnClickListener { pulsarnumero("D") }
            bHexE.setOnClickListener { pulsarnumero("E") }
            bHexF.setOnClickListener { pulsarnumero("F") }

            hexadecimal.setOnClickListener {

                if (!meBoHe){
                    bPunto.isEnabled = false
                    meBoHe = true
                    bHexA.isEnabled = true
                    bHexB.isEnabled = true
                    bHexC.isEnabled = true
                    bHexD.isEnabled = true
                    bHexE.isEnabled = true
                    bHexF.isEnabled = true
                    hexadecimal.setTextColor(AppCompatResources.getColorStateList(this, R.color.white))
                } else {
                    meBoHe = false
                    bHexA.isEnabled = false
                    bHexB.isEnabled = false
                    bHexC.isEnabled = false
                    bHexD.isEnabled = false
                    bHexE.isEnabled = false
                    bHexF.isEnabled = false
                    hexadecimal.setTextColor(AppCompatResources.getColorStateList(this, R.color.black))

                }


            }

            binario.setOnClickListener {
                if (!meBoBi){
                    meBoBi = true
                    b2.isEnabled = false
                    b3.isEnabled = false
                    b4.isEnabled = false
                    b5.isEnabled = false
                    b6.isEnabled = false
                    b7.isEnabled = false
                    b8.isEnabled = false
                    b9.isEnabled = false
                    bPunto.isEnabled = false
                    binario.setTextColor(AppCompatResources.getColorStateList(this, R.color.white))

                }else {
                    meBoBi = false
                    b2.isEnabled = true
                    b3.isEnabled = true
                    b4.isEnabled = true
                    b5.isEnabled = true
                    b6.isEnabled = true
                    b7.isEnabled = true
                    b8.isEnabled = true
                    b9.isEnabled = true
                    bPunto.isEnabled = true
                    binario.setTextColor(AppCompatResources.getColorStateList(this, R.color.black))

                }
            }
        }
            sumar.setOnClickListener { operacion(com.example.calculadora.MainActivity.Companion.SUMA) }
            restar.setOnClickListener { operacion(com.example.calculadora.MainActivity.Companion.RESTA) }
            multiplicar.setOnClickListener { operacion(com.example.calculadora.MainActivity.Companion.MULTI) }
            dividir.setOnClickListener { operacion(com.example.calculadora.MainActivity.Companion.DIVI) }

            b_clean.setOnClickListener {
                if (texto.text.isNotEmpty()){
                    texto.text = ""
                    num1 = 0.0
                    num2 = 0.0
                    operacion = NADA
                }
            }

            bo_ca.setOnClickListener{
                if (texto.text.isNotEmpty()){
                    texto.text = texto.text.substring(0, texto.text.length -1)
                }
            }

            igual.setOnClickListener {
                var valor = 0.0
                valor = when (operacion) {
                    SUMA -> num1 + num2
                    RESTA -> num1 - num2
                    MULTI -> num1 * num2
                    DIVI -> num1 / num2
                    else -> 0.0
                }

                if(!meBoHe && !meBoBi){
                    texto.text = valor.toString()
                } else {
                    texto.text = DeToX(valor)
                }
                operacion = NADA
            }



    }


    private fun pulsarnumero(valor: String){
        if (operacion == NADA){
            texto.text = "${texto.text}$valor"

            if (!meBoBi && !meBoHe){
                num1 = texto.text.toString().toDouble()
            }

            if (meBoHe) {
                num1 = HeToDe(texto.text.toString()).toDouble()
            }

            if (meBoBi){
                num1 = BiToDe(texto.text.toString())
            }
        } else {
            texto.text = "${texto.text}$valor"

            if (!meBoBi && !meBoHe){
                num2 = texto.text.toString().toDouble()
            }

            if (meBoHe) {
                num2 = HeToDe(texto.text.toString()).toDouble()
            }

            if (meBoBi) {
                num2 = BiToDe(texto.text.toString())
            }
        }
    }

    fun operacion(op: Int){
        if (op != 0){
            operacion = op
        }
        texto.text = ""
    }

    fun HeToDe(num: String): Int {
        return Integer.parseInt(num, 16)
    }

    fun BiToDe(num: String): Double {
        var sum = 0.00
        num.reversed().forEachIndexed { k, v ->
            sum += v.toString().toDouble() * 2.0.pow(k.toDouble())
        }
        return sum
    }

    fun DeToX(num: Double): String {
        if (meBoHe) {
            return Integer.toHexString(num.toInt())
        } else if (meBoBi){
            return Integer.toBinaryString(num.toInt()).toString()
        }
        return ""
    }

    companion object {
        const val SUMA = 1
        const val RESTA = 2
        const val MULTI = 3
        const val DIVI = 4
        const val NADA = 0
    }
}




