package com.br.util;

import com.br.model.Page;
import com.br.model.Tupla;

import java.io.*;
import java.util.ArrayList;

public class Util {

    public static int FHash(String text) {
        char[] words = text.toCharArray();
        int hash = 0;

        for (char word : words) hash += word;

        return hash;
    }

    public static void addTupla(ArrayList<Tupla> tuplas, String name) {
        Tupla newTupla = new Tupla(name);

        tuplas.add(newTupla);
    }

    public static void readFile(
            String path,
            ArrayList<Integer> buckets,
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
                    int hash = FHash(register);

                    Util.addTupla(tuplas, register);

                    if (!buckets.contains(hash)) buckets.add(hash);

                    if (!page.setRegister(register)) page = page.getNextPage();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
