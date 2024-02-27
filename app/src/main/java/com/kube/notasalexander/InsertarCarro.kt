package com.kube.notasalexander

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class InsertarCarro : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_carro)

        db = FirebaseFirestore.getInstance()

        val txtId = findViewById<EditText>(R.id.etId)
        val txtnomb = findViewById<EditText>(R.id.etNombre)
        val txtmar = findViewById<EditText>(R.id.etMarca)
        val txtcolor = findViewById<EditText>(R.id.etColor)
        val txtcili = findViewById<EditText>(R.id.etCili)
        val txtpuert = findViewById<EditText>(R.id.etPuert)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val btnClean = findViewById<Button>(R.id.btnClea)
        btnAgregar.setOnClickListener {
            db.collection("coche").document(txtId.text.toString()).set(
                hashMapOf(
                    "id" to txtId.text.toString(),
                    "nombre" to txtnomb.text.toString(),
                    "marca" to txtmar.text.toString(),
                    "color" to txtcolor.text.toString(),
                    "cilindros" to txtcili.text.toString().toInt(),
                    "puertas" to txtpuert.text.toString().toInt()
                )
            )
            limpiar()

        }
        btnClean.setOnClickListener(){
            txtnomb.setText(" ")
            txtmar.setText(" ")
            txtcolor.setText(" ")
            txtcili.setText(" ")
            txtpuert.setText(" ")
        }
    }

    private fun showToast(message: String) {
        // Muestra un Toast con el mensaje proporcionado
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun limpiar(){
        val txtId = findViewById<EditText>(R.id.etId)
        val txtnomb = findViewById<EditText>(R.id.etNombre)
        val txtmar = findViewById<EditText>(R.id.etMarca)
        val txtcolor = findViewById<EditText>(R.id.etColor)
        val txtcili = findViewById<EditText>(R.id.etCili)
        val txtpuert = findViewById<EditText>(R.id.etPuert)
        txtId.setText(" ")
        txtnomb.setText(" ")
        txtmar.setText(" ")
        txtcolor.setText(" ")
        txtcili.setText(" ")
        txtpuert.setText(" ")
    }
}
