package com.example.tipcalculatortp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.text.NumberFormat

class MainActivity : AppCompatActivity(), View.OnFocusChangeListener, SeekBar.OnSeekBarChangeListener {

    private lateinit var txtTotalconta : EditText
    private lateinit var qtdPessoas : EditText
    private lateinit var skGorjeta : SeekBar

    private val formatador = NumberFormat.getCurrencyInstance()

    override fun onFocusChange(p0: View?, p1: Boolean){
        this.atualizaDadosConta()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

        var lblPercentualGorjeta = this.findViewById<TextView>(R.id.lblPercentualGorjeta)
        lblPercentualGorjeta.setText(skGorjeta.progress.toString() + "%")

        this.atualizaDadosConta()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTotalconta = this.findViewById<EditText>(R.id.txtTotalConta_id)
        txtTotalconta.setOnFocusChangeListener(this)

        qtdPessoas = this.findViewById<EditText>(R.id.qtdPessoas_id)
        qtdPessoas.setOnFocusChangeListener(this)

        skGorjeta = this.findViewById<SeekBar>(R.id.skGorjeta_id)
        skGorjeta.setOnFocusChangeListener(this)
    }


    private fun atualizaDadosConta(){

        if (txtTotalconta.text.toString().isNotEmpty()
            && qtdPessoas.text.toString().isNotEmpty()) {

            var valorConta = txtTotalconta.text.toString().toDouble()
            var qtdPessoas = qtdPessoas.text.toString().toInt()


            var lblValorRealGorjeta = this.findViewById<TextView>(R.id.lblTotalGorjeta_id)
            var valorRealGorjeta = valorConta * skGorjeta.progress / 100
            lblValorRealGorjeta.setText(formatador.format(valorRealGorjeta))

            var lblValorRealconta = this.findViewById<TextView>(R.id.lblValorRealConta_id)
            lblValorRealconta.setText(formatador.format(valorConta + valorRealGorjeta))

            var lblValorRealPorPessoa = this.findViewById<TextView>(R.id.lblTotalPorPessoas_id)
            lblValorRealPorPessoa.setText(formatador.format((valorConta + valorRealGorjeta) / qtdPessoas))
        }
    }
}