package com.example.fintrack.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Definindo as entidades e a versão do banco de dados
@Database(entities = [Category::class, Expenses::class], version = 4)
abstract class AppDataBase : RoomDatabase() {

    // Método abstrato para obter a DAO
    abstract fun appDao(): AppDao

    companion object {
        // Instância volátil do banco de dados
        @Volatile
        private var INSTANCE: AppDataBase? = null

        // Função para obter a instância do banco de dados
        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                // Cria a instância do banco de dados se ainda não existir
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // Usado para migração destrutiva
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
