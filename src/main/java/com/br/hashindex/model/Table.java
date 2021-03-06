package com.br.hashindex.model;

import com.br.hashindex.util.Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public class Table {
    private ArrayList<Tupla> tuplas = new ArrayList<>();
    private Page firstPage;
    private Map<Integer, Bucket> buckets;

    public Table(Map<Integer, Bucket> buckets) {
        this.buckets = buckets;
        int maxSizePage = Configuration.getPageSize();
        this.firstPage = new Page(maxSizePage);
    }

    public ArrayList<Tupla> getTuplas() {
        return tuplas;
    }

    public int getName(String name) {
        int hash = Util.FHash6(name);

        Bucket bucket = buckets.get(hash);

        return bucket.getReg(name);
    }

    public void fillPages(
            String path,
            Map<Integer, Bucket> buckets
    ) throws FileNotFoundException, UnsupportedEncodingException {
        Util.readFile(path, buckets, firstPage, tuplas);
    }

    public void fillPages(
            BufferedReader br,
            Map<Integer, Bucket> buckets
    ) {
        Util.readFile(br, buckets, firstPage, tuplas);
    }
}
