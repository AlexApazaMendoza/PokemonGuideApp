package com.alpamedev.pokemonguideapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.alpamedev.pokemonguideapp.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var navController: NavController

    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    private var mFirebaseAuth: FirebaseAuth? = null

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        navController = Navigation.findNavController(this,R.id.navGraphMainHostFragment)
        setupWithNavController(mBinding.bottomNav,navController)

        setUpAuth()
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuth?.addAuthStateListener( mAuthListener )
    }

    override fun onPause() {
        super.onPause()
        mFirebaseAuth?.removeAuthStateListener( mAuthListener )
    }

    private fun setUpAuth() {
        mFirebaseAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            if( user == null ){
                val intent = AuthUI
                    .getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(
                        mutableListOf(
                            AuthUI.IdpConfig.EmailBuilder().build(),
                            AuthUI.IdpConfig.GoogleBuilder().build()
                        )
                    ).build()
                signInLauncher.launch(intent)
            }
        }
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        //val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            //val user = FirebaseAuth.getInstance().currentUser
            Toast.makeText(this, getString(R.string.sigin_text_message), Toast.LENGTH_SHORT).show()
            // ...
        } else {
            finish()
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }
}