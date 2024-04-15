package com.example.opsc_part2

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

class Register : AppCompatActivity() {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userreg: EditText = findViewById(R.id.edtUserReg)
        val passreg: EditText = findViewById(R.id.edtPassReg)

        var btnReg: Button = findViewById(R.id.btnReg)
        btnReg.setOnClickListener(){
            auth.createUserWithEmailAndPassword(userreg.text.toString(), passreg.text.toString())
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Log.w("Registration", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this, "Registration not successful", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}