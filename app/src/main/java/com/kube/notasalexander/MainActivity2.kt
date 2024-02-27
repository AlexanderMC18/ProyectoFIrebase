package com.kube.notasalexander

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        firebaseAuth = Firebase.auth

        val btnCerrarSesion = findViewById<Button>(R.id.btnsalir)
        val btnInsertar = findViewById<Button>(R.id.btnIngresar)
        val btnmostrar = findViewById<Button>(R.id.btnVer)
        val btnmodificar = findViewById<Button>(R.id.btnMod)

        btnCerrarSesion.setOnClickListener {
            signOut()
        }
        btnInsertar.setOnClickListener{
            val i = Intent(this, InsertarCarro::class.java)
            startActivity(i)
        }
        btnmostrar.setOnClickListener{
            val i = Intent(this, MostrarCarros::class.java)
            startActivity(i)
        }
        btnmodificar.setOnClickListener{
            val i = Intent(this, Actualizar_Eliminar::class.java)
            startActivity(i)
        }
    }
    private fun signOut() {
        firebaseAuth.signOut()
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}