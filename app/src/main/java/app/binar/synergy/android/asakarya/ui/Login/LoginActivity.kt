package app.binar.synergy.android.asakarya.ui.Login

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.databinding.ActivityLoginBinding
import app.binar.synergy.android.asakarya.ui.Register.RegisterActivity
import app.binar.synergy.android.asakarya.ui.homeNavigation.HomeNavigationActivity
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.CampaignFragment
import app.binar.synergy.android.asakarya.ui.landing.LandingPageActivity
import app.binar.synergy.android.asakarya.ui.loading.LoadingDialog

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private val loading by lazy { LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(Const.PREF_NAME, MODE_PRIVATE)

        viewModel = LoginViewModel(sharedPreferences)

        binding.etEmail.doAfterTextChanged {
            viewModel.onChangeEmail(it.toString())
        }

        binding.etPassword.doAfterTextChanged {
            viewModel.onChangePassword(it.toString())
        }

        viewModel.isButtonEnable.observe(this, Observer {
            binding.btLogin.isEnabled = it
        })

        viewModel.gotoHomePage.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()
                startActivity(
                    Intent(this, HomeNavigationActivity::class.java)
                        .apply {
                            //apply this.addFlags untuk membuat halaman tidak kembali kesign in ketika sudah login
                            this.addFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                                        Intent.FLAG_ACTIVITY_NEW_TASK
                            )
                        }
                )
            }
        })

        viewModel.showErrorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        binding.btLogin.setOnClickListener {
            viewModel.doLogin()
        }

        binding.imagePassVisibility.setOnClickListener {
            if (binding.etPassword.inputType.equals(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                binding.etPassword.setRawInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
            } else if (binding.etPassword.inputType.equals(InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                binding.etPassword.setRawInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
            }
        }

        binding.daftarAkun.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
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
        //            if (binding.etPassword.inputType.equals(InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
//                binding.etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
//            } else {
//                binding.etPassword.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
//            }
        }


        viewModel.showLoading.observe(this, {
            loading.showLoading(it)
        })

    }
}


