package com.example.profileinfo


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.profileinfo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {


    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var inputs: List<EditText>
    private lateinit var textViews: List<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        inputs = listOf(
            binding.etEmail,
            binding.etUsername,
            binding.etFirstName,
            binding.etLastName,
            binding.etAge
        )

        textViews = listOf(
            binding.profileInfoLayout.etEmailInfo,
            binding.profileInfoLayout.etUsernameInfo,
            binding.profileInfoLayout.etFirstNameInfo,
            binding.profileInfoLayout.etLastNameInfo,
            binding.profileInfoLayout.etAgeInfo
        )

        binding.buttonSave.setOnClickListener {
            var inputsEmpty = false
            val allInputsFilled = true

            for (i in inputs) {
                val input = i.text.toString()

                if (input.isEmpty()) {
                    inputsEmpty = true
                }
            }

            if (inputsEmpty) {
                Snackbar.make(
                    binding.root, "რომელიმე ველი ცარიელია!! შეავსეთ!!", Snackbar.LENGTH_SHORT
                ).show()
            } else {
                if (allInputsFilled) {
                    if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString())
                            .matches()
                    ) {
                        Snackbar.make(
                            binding.root, "Email შეიყვანეთ სწორად!!!!", Snackbar.LENGTH_SHORT
                        ).show()

                    } else if (binding.etUsername.text.length < 10) {

                        Snackbar.make(
                            binding.root,
                            "Username არ უნდა იყოს 10 სიმბოლოზე ნაკლები!!!",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } else if (binding.etAge.text.toString() < 0.toString()) {

                        Snackbar.make(
                            binding.root,
                            "ასაკი მთელი დადებითი რიცხვი უნდა იყოს!!!",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } else {
                        for (i in inputs.indices) {
                            val inputsI = inputs[i]
                            val textViewsT = textViews[i]
                            val value = inputsI.text.toString()
                            textViewsT.text = value
                            binding.profileInfoLayout.profileInfo.visibility = View.VISIBLE
                        }
                    }


                }


            }
        }

        binding.profileInfoLayout.buttonAgainInfo.setOnClickListener {
            binding.etEmail.text.clear()
            binding.etUsername.text.clear()
            binding.etFirstName.text.clear()
            binding.etLastName.text.clear()
            binding.etAge.text.clear()
            binding.profileInfoLayout.profileInfo.visibility = View.GONE
            binding.profileInfoStart.visibility = View.VISIBLE
        }

        buttonClearFunction()


    }


    private fun buttonClearFunction() {
        binding.buttonClear.setOnLongClickListener {
            binding.etEmail.text.clear()
            binding.etUsername.text.clear()
            binding.etFirstName.text.clear()
            binding.etLastName.text.clear()
            binding.etAge.text.clear()
            true
        }
    }
}


