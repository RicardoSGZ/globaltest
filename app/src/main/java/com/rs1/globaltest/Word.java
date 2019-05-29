package com.rs1.globaltest;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(String word) {
        this.mWord = word;
    }

    public String getWord(){
        return this.mWord;
    }

    /*
    @PrimaryKey(autoGenerate = true)
    private int id;
    */
}
