package ru.batura.stat.batchat.repository.Firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import javax.inject.Singleton

interface IFirebaseAuth {
    fun isLogged(): LiveData<Boolean?>
    fun addListner()
    fun removeListner()
    fun getCurrentUser(): LiveData<FirebaseUser?>
}

@Singleton
class FirebaseAuthSource @Inject constructor() : IFirebaseAuth {

    // подключенный пользователь
    private var currUser: MutableLiveData<FirebaseUser?> = MutableLiveData(null)

    // состояние входа
    private var isLogging: MutableLiveData<Boolean?> = MutableLiveData(null)

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    /**
     * слушатель на состояние входа
     */
    private var mAuthStateListener: AuthStateListener? = AuthStateListener { firebaseAuth ->
        val user = firebaseAuth.currentUser
        if (user == null) { // User is signed in
            currUser.postValue(user)
            isLogging.value = false

        } else { // User is signed out
            currUser.postValue(user)
            isLogging.value = true
        }
    }

    init {
        addListner()
    }


    /**
     * возвращаем состояние входа
     */
    override fun isLogged(): LiveData<Boolean?> {
        return isLogging
    }

    /**
     * добавляем слушатель
     */
    override fun addListner() {
        firebaseAuth.addAuthStateListener(mAuthStateListener!!)
    }

    /**
     * убераем слушатель
     */
    override fun removeListner() {
        firebaseAuth.removeAuthStateListener(mAuthStateListener!!)
    }

    /**
     * возврвщаем текущего пользователя
     */
    override fun getCurrentUser(): LiveData<FirebaseUser?> {
        return currUser
    }


}