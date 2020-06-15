package ru.batura.stat.batchat.repository.Firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

interface IFirebaseAuth {
    fun isLogged(): LiveData<Boolean?>
    fun addListner()
    fun removeListner()
    fun getCurrentUser(): FirebaseUser?
}

class FirebaseAuthSource @Inject constructor() : IFirebaseAuth {

    private var currUser: FirebaseUser? = null

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private var mAuthStateListener: AuthStateListener? = AuthStateListener { firebaseAuth ->
        val user = firebaseAuth.currentUser
        if (user == null) { // User is signed in
            isLogging.value = false
        } else { // User is signed out
            currUser = user
            isLogging.value = true
        }
    }

    init {
        addListner()
    }

    private var isLogging: MutableLiveData<Boolean?> = MutableLiveData(null)

    override fun isLogged(): LiveData<Boolean?> {
        return isLogging
    }

    override fun addListner() {
        firebaseAuth.addAuthStateListener(mAuthStateListener!!)
    }

    override fun removeListner() {
        firebaseAuth.removeAuthStateListener(mAuthStateListener!!)
    }

    override fun getCurrentUser(): FirebaseUser? {
        return currUser
    }

    //    fun isLogged(): LiveData<Boolean> {
//
//        mAuthStateListener = AuthStateListener { firebaseAuth ->
//            val user = firebaseAuth.currentUser
//            if (user != null) { // User is signed in
//                isLogging.value = false
//            } else { // User is signed out
//                isLogging.value = true
//            }
//        }
//
//
//    }
}