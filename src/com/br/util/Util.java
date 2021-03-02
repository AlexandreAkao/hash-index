package com.br.util;

import com.br.model.Bucket;
import com.br.model.Configuration;
import com.br.model.Page;
import com.br.model.Tupla;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class Util {

    public static int FHash1(String text) {
        char[] words = text.toCharArray();
        int hash = 0;

        for (char word : words) hash += word;

        return hash;
    }

    public static int FHash2(String text) {
        char[] words = text.toCharArray();
        int hash = 0;

        for (char word : words) hash += word;

        hash = (int) (Math.log(hash) * Math.log(hash) * 1000);

        return hash;
    }

    public static void addTupla(ArrayList<Tupla> tuplas, String name) {
        Tupla newTupla = new Tupla(name);

        tuplas.add(newTupla);
    }

    public static void readFile(
            String path,
            Map<Integer, Bucket> buckets,
            Page firstPage,
            ArrayList<Tupla> tuplas
    ) throws FileNotFoundException, UnsupportedEncodingException {
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "ISO_8859_1"));

        try {
            Page page = firstPage;

            while (br.ready()) {
                if (br.ready()) {
                    String register = br.readLine();
                    int hash = FHash2(register);

                    Util.addTupla(tuplas, register);

                    if (!page.setRegister(register)) page = page.getNextPage();

                    if (buckets.get(hash) == null) {
                        Bucket newBucket = new Bucket(Configuration.getBucketSize());

                        newBucket.addRef(page);
                        buckets.put(hash, newBucket);
                    } else {
                        Bucket bucket = buckets.get(hash);
                        Configuration.incrementColisionCount();

                        bucket.addRef(page);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
