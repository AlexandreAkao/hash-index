package com.br;

import com.br.model.Configuration;
import com.br.model.Database;
import com.br.model.Tupla;
import com.br.util.Util;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String caminho = "words.txt";
        int pageSize = 1;
        int bucketSize = 3;
        Configuration config = new Configuration(pageSize, bucketSize);

        Map<Integer, Tupla> m = new HashMap<>();
        try {
            Database database = new Database(pageSize);
            database.readTable(caminho);

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
