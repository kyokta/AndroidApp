package com.example.app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootview = inflater.inflate(R.layout.fragment_login, container, false)

        val inp_username = rootview.findViewById<TextInputEditText>(R.id.username)
        val inp_password = rootview.findViewById<TextInputEditText>(R.id.password)

        val btnLogin = rootview.findViewById<Button>(R.id.btn_login)

        btnLogin.setOnClickListener{
            val username = "kyokta"
            val password = "492596"

            val uname = inp_username.text.toString()
            val pass = inp_password.text.toString()

            if (uname == username && password == pass) {
                val intent = Intent(activity, HomePage::class.java)
                intent.putExtra("username", uname)
                startActivity(intent)
            } else if (uname == username) {
                Toast.makeText(activity,
                    "Password yang Anda masukkan salah!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity,
                    "Akun Anda belum terdaftar!", Toast.LENGTH_SHORT).show()
            }
        }

        return rootview
    }
}