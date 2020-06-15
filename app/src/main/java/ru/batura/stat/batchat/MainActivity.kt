package ru.batura.stat.batchat

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import dagger.hilt.android.AndroidEntryPoint
import ru.batura.stat.batchat.ui.chat.ChatFragment
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.simpleName

    val RC_SIGN_IN = 1

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChatFragment.newInstance())
                .commitNow()
        }
    }

    override fun onResume() {
        super.onResume()
        addObservers()
    }

    private fun addObservers() {
        mainViewModel.isLogging.observe(this, Observer {
            Log.d(TAG, "logg")
            if (it != null) {
                if (!it) {
                    openLoggingActivity()
                }
            }
        })
    }

    private fun openLoggingActivity() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false)
                .setAvailableProviders(
                    Arrays.asList(
                        AuthUI.IdpConfig.EmailBuilder().build(),
                        AuthUI.IdpConfig.GoogleBuilder().build()
                    )
                )
                .build(),
            RC_SIGN_IN
        )
    }

}


