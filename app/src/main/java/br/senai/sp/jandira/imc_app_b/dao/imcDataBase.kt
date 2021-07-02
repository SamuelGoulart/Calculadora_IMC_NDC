package br.senai.sp.jandira.imc_app_b.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DATABASE_NAME = "imc.bd"
const val DATABASE_VERSION = 1

class imcDataBase (val context: Context) :
    SQLiteOpenHelper(context, "imc.db", null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        val criarTabelaUsuario = "CREATE TABLE tb_usuario (" +
                "id INTEGER PRIMARY KEY," +
                "email TEXT NOT NULL," +
                "senha TEXT NOT NULL," +
                "nome TEXT NOT NULL," +
                "profissao TEXT NULL," +
                "data_nascimento TEXT NOT NULL," +
                "altura REAL NOT NULL," +
                "sexo TEXT NOT NULL,"+
                "foto BLOB NULL)"
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}
