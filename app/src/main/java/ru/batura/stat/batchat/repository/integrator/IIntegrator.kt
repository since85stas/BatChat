package ru.batura.stat.batchat.repository.integrator

import ru.batura.stat.batchat.repository.Firebase.IFirebase
import ru.batura.stat.batchat.repository.Firebase.IFirebaseAuth
import ru.batura.stat.batchat.repository.room.ChatDao

interface IIntegrator: IFirebaseAuth, IFirebase, ChatDao {

}