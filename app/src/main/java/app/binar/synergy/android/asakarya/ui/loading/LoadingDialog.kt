package app.binar.synergy.android.asakarya.ui.loading

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatDialog
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.databinding.LoadingDialogBinding

class LoadingDialog(context: Context) : AppCompatDialog(context) {
    private lateinit var binding: LoadingDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = LoadingDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.setCancelable(true)
    }

    fun showLoading(value: Boolean) {
        if (value) {
            if (!isShowing) {
                this.show()
            } else {
                if (isShowing) {
                    this.dismiss()
                }
            }
        }
    }
}