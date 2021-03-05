package com.br;

import com.br.model.Configuration;
import com.br.model.Database;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) {

        String path = "test.txt";
        int pageSize = 4;
        int bucketSize = 2;

        new Configuration.Builder()
            .setPageSize(pageSize)
            .setBucketSize(bucketSize)
            .setColisionCount(0)
            .setOverflowCount(0)
            .build();

        try {
            Database database = new Database();
            database.readTable(path);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
