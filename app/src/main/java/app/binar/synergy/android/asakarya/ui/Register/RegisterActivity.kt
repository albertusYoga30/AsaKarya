package app.binar.synergy.android.asakarya.ui.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.databinding.ActivityRegisterBinding
import app.binar.synergy.android.asakarya.ui.Login.LoginActivity
import app.binar.synergy.android.asakarya.ui.loading.LoadingDialog

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private val loading by lazy { LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = RegisterViewModel()

        binding.etEmail.doAfterTextChanged {
            val email = it.toString()
            viewModel.onChangeEmail(email)
        }

        binding.etPassword.doAfterTextChanged {
            val password = it.toString()
            viewModel.onChangePassword(password)
        }

        binding.etName.doAfterTextChanged {
            viewModel.onChangeName(it.toString())
        }

        binding.etPhone.doAfterTextChanged {
            viewModel.onChangePhone(it.toString())
        }

        binding.btSignup.setOnClickListener {
            viewModel.validateInputValue()
            viewModel.validate.observe(this, Observer {
                if (it) {
                    viewModel.doRegister()
                } else {
                    Toast.makeText(this, viewModel.showErrorInput.value, Toast.LENGTH_SHORT).show()
                }
            })
        }

        viewModel.isButtonEnable.observe(this, Observer {
            binding.btSignup.isEnabled = it
        })

        viewModel.goToLogin.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        })

        viewModel.showErrorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })


        binding.MasukAkun.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        var show = true
        binding.imagePassVisibility.setOnClickListener {
            if (show) {
                binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.imagePassVisibility.setImageResource(R.drawable.ic_eye_visible)
                show = false
            } else {
                binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.imagePassVisibility.setImageResource(R.drawable.ic_eye)
                show = true
            }
        }

        viewModel.showLoading.observe(this, {
            loading.showLoading(it)
        })
    }
}