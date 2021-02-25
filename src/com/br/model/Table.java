package com.br.model;

import com.br.util.Util;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Table {
    ArrayList<Tupla> tuplas = new ArrayList<>();
    Page firstPage;

    public Table(int maxSize) {
        this.firstPage = new Page(maxSize);
    }

    public void fillPages(
            String path,
            ArrayList<Integer> buckets
    ) throws FileNotFoundException, UnsupportedEncodingException {
        Util.readFile(path, buckets, firstPage, tuplas);
    }
}
