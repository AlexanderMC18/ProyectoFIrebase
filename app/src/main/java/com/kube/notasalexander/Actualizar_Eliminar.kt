package com.kube.notasalexander

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Actualizar_Eliminar : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_eliminar)

        var db = Firebase.firestore
        firebaseAuth = Firebase.auth

        var txtIdQ = findViewById<TextInputEditText>(R.id.txtIdQ)
        var txtTrackQ = findViewById<TextInputEditText>(R.id.txtTrackQ)
        var txtArtistQ = findViewById<TextInputEditText>(R.id.txtArtistQ)
        var txtAlbumQ = findViewById<TextInputEditText>(R.id.txtAlbumQ)
        var txtGenderQ = findViewById<TextInputEditText>(R.id.txtGenderQ)
        var txtYearQ = findViewById<TextInputEditText>(R.id.txtYearQ)

        val btnQuery = findViewById<Button>(R.id.btnQuery)
        val btnReturn = findViewById<Button>(R.id.btnReturn)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnClean = findViewById<Button>(R.id.btnClean)

        val trackId = intent.getStringExtra("id")
        txtIdQ.setText(trackId)

        btnQuery.setOnClickListener(){

            db = FirebaseFirestore.getInstance()

            db.collection("coche").document(txtIdQ.text.toString()).get().addOnSuccessListener { documentSnapshot ->
                // Obtén el ID del documento
                val documentId = documentSnapshot.id

                // Utiliza el ID como necesites (puedes imprimirlo, almacenarlo, etc.)
                Toast.makeText(this, "ID del documento: $documentId", Toast.LENGTH_SHORT).show()

                // Resto del código para llenar los campos con los valores del documento
                txtTrackQ.setText(documentSnapshot.get("nombre") as String?)
                txtArtistQ.setText(documentSnapshot.get("marca") as String?)
                txtAlbumQ.setText(documentSnapshot.get("color") as String?)
                val cilindros = documentSnapshot.get("cilindros") as? Long
                val puerta = documentSnapshot.get("puertas") as? Long

                txtGenderQ.setText(cilindros?.toString() ?: "")
                txtYearQ.setText(puerta?.toString() ?: "")
            }
        }

        btnUpdate.setOnClickListener(){
            db.collection("coche").document(txtIdQ.text.toString()).set(
                hashMapOf(
                    "nombre" to txtTrackQ.text.toString(),
                    "marca" to txtArtistQ.text.toString(),
                    "color" to txtAlbumQ.text.toString(),
                    "cilindros" to txtGenderQ.text.toString().toInt(),
                    "puertas" to txtYearQ.text.toString().toInt()
                )
            )
        }

        btnDelete.setOnClickListener(){
            db.collection("coche").document(txtIdQ.text.toString()).delete()
        }

        btnReturn.setOnClickListener()
        {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        btnClean.setOnClickListener(){
            txtIdQ.setText(" ")
            txtTrackQ.setText(" ")
            txtArtistQ.setText(" ")
            txtAlbumQ.setText(" ")
            txtGenderQ.setText(" ")
            txtYearQ.setText(" ")
        }

    }
}