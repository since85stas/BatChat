package ru.batura.stat.batchat.repository

import androidx.lifecycle.LiveData
import ru.batura.stat.batchat.repository.Firebase.FirebaseAuthSource
import javax.inject.Inject


class Repository @Inject constructor() : IRepository {

    @Inject lateinit var firebaseAuthSource: FirebaseAuthSource

    override fun isLogged(): LiveData<Boolean?> {
        return firebaseAuthSource.isLogged()
    }

    override fun addListner() {
        return firebaseAuthSource.addListner()
    }

    override fun removeListner() {
        return firebaseAuthSource.removeListner()
    }

}