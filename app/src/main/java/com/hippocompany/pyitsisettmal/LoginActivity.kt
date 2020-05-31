package com.hippocompany.pyitsisettmal

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    //Firebase references
    private var databaseReference: DatabaseReference? = null
    private var fireBaseDatabase: FirebaseDatabase? = null
    private var firebaseAuth: FirebaseAuth? = null

    lateinit var signInButton: MaterialButton
    var passwordEdt: TextInputEditText? = null
    var usernameEdt: TextInputEditText? = null
    private var progressBar: ProgressBar? = null

    private val TAG = "CreateAccountActivity"
    private var userName: String? = null
    private var password: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        InitView()
        IntentToMainActivity()

    }

    fun InitView() {
        signInButton = findViewById(R.id.sign_in_btn)
        usernameEdt = findViewById(R.id.username_text_input_edt)
        passwordEdt = findViewById(R.id.password_text_input_edt)

        fireBaseDatabase = FirebaseDatabase.getInstance()
//        databaseReference = fireBaseDatabase!!.reference!!.child("Users")
        firebaseAuth = FirebaseAuth.getInstance()
    }


    fun IntentToMainActivity() {
        signInButton.setOnClickListener { v ->
            userName = usernameEdt!!.text.toString()
            password = passwordEdt!!.text.toString()
            if (!TextUtils.isEmpty(userName)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT)
                    .show();

            } else if (!TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT)
                    .show();
            }
            firebaseAuth!!.signInWithEmailAndPassword(userName!!, password!!)
                .addOnCompleteListener(
                    this@LoginActivity,
                    OnCompleteListener<AuthResult?> { task ->
                        if (!task.isSuccessful) {
                        } else {
                            val intent =
                                Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    })
        }
    }

}
