package com.br;

import com.br.model.Configuration;
import com.br.model.Database;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) {

        String path = "words.txt";
        int pageSize = 4;
        int bucketSize = 3;

        new Configuration(pageSize, bucketSize, 0, 0);

        try {
            Database database = new Database();
            database.readTable(path);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
