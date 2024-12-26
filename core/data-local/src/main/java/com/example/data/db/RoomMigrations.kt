package com.example.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.db.contract.RoomContract

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("DROP TABLE optimal_stats")
        db.execSQL(
            "CREATE TABLE ${RoomContract.tableStats} (" +
                    "idStat INTEGER NOT NULL PRIMARY KEY, statName TEXT NOT NULL)"
        )
        db.execSQL(
            "CREATE TABLE ${RoomContract.tableAdditionalStats} (" +
                    "idAdditionalStat INTEGER NOT NULL PRIMARY KEY," +
                    "idStat INTEGER NOT NULL, idHero INTEGER NOT NULL)"
        )
        db.execSQL(
            "CREATE TABLE ${RoomContract.tableOptimalStatsHero} (" +
                    "idOptimalStat INTEGER NOT NULL PRIMARY KEY," +
                    "idStat INTEGER NOT NULL, statValue TEXT NOT NULL," +
                    "remark TEXT, idHero INTEGER NOT NULL)"
        )
        db.execSQL("ALTER TABLE ${RoomContract.tableBuildWeapon} ADD COLUMN tier INTEGER NOT NULL DEFAULT 1")
    }
}