package com.br.model;

import com.br.util.Util;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public class Table {
    ArrayList<Tupla> tuplas = new ArrayList<>();
    Page firstPage;

    public Table() {
        int maxSizePage = Configuration.getPageSize();
        this.firstPage = new Page(maxSizePage);
    }

    public void fillPages(
            String path,
            Map<Integer, Bucket> buckets
    ) throws FileNotFoundException, UnsupportedEncodingException {
        Util.readFile(path, buckets, firstPage, tuplas);
    }
}
