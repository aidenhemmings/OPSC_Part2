package com.example.opsc_part2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btnLogin: Button = findViewById(R.id.btnLogin)
        val userlogin: EditText = findViewById(R.id.edtUser)
        val passlogin: EditText = findViewById(R.id.edtPass)
        btnLogin.setOnClickListener(){
            auth.signInWithEmailAndPassword(userlogin.text.toString(), passlogin.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(this, "Signed in", Toast.LENGTH_LONG).show()
                    }
                    else {
                        Log.w("Login", "signInWithEmail:failure", task.exception)
                        Toast.makeText(this, "Not signed in", Toast.LENGTH_LONG).show()
                    }
                }
        }

        var btnRegister: Button = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener(){
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }


    }
}