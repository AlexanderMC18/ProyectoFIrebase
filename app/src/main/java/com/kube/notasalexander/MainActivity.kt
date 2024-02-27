package com.kube.notasalexander

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnaceptar = findViewById<Button>(R.id.btn_Entrar)
        var txtusuario = findViewById<TextInputEditText>(R.id.txt_acceso)
        var txtcontra = findViewById<TextInputEditText>(R.id.txt_contra)
        val btnGenerar = findViewById<TextView>(R.id.btcrear)
        val btnOlvide = findViewById<TextView>(R.id.btn_olvidar)
        firebaseAuth = Firebase.auth

        btnaceptar.setOnClickListener(){
            if (txtusuario.text.toString().trim().isEmpty()){
                txtusuario.setError("Ingrese un usuario")
            }else if(txtcontra.text.toString().trim().isEmpty()){
                txtcontra.setError("Ingrese una contraseña")
            }else{
                signIn(txtusuario.text.toString(),txtcontra.text.toString())
            }
        }
        btnGenerar.setOnClickListener() {
            val a = Intent(this, CrearCuenta::class.java)
            startActivity(a)
        }
        btnOlvide.setOnClickListener(){
            val i = Intent(this, OlvideCOntrasena::class.java)
            startActivity(i)
        }
    }
    private fun signIn(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    //current user usuario actual
                    val verifica = user?.isEmailVerified

                    if (verifica==true)
                    {
                        Toast.makeText(baseContext,"Exito", Toast.LENGTH_SHORT).show()
                        //Aqui vamos ir a la segunda activity
                        val i = Intent(this, MainActivity2::class.java)
                        startActivity(i)
                    }
                    else
                    {
                        Toast.makeText(baseContext,"No se ha verificado su correo", Toast.LENGTH_SHORT).show()
                    }

                }
                else
                {
                    Toast.makeText(baseContext,"Error de email y/o contraseña", Toast.LENGTH_SHORT).show()
                }
            }
    }
}