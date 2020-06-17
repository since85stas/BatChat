package ru.batura.stat.batchat.repository

import ru.batura.stat.batchat.repository.Firebase.IFirebase
import ru.batura.stat.batchat.repository.Firebase.IFirebaseAuth
import ru.batura.stat.batchat.repository.room.ChatDao

interface IRepository: IFirebaseAuth, IFirebase, ChatDao {



}