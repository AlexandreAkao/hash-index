package com.br.model;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private Table table;
    private Map<Integer, Bucket> buckets;

    public Database() {
        this.table = null;
        this.buckets = new HashMap<>();
    }

    public Table getTables() {
        return table;
    }

    public void setTables(Table table) {
        this.table = table;
    }

    public void readTable(String path) throws FileNotFoundException, UnsupportedEncodingException {
        Configuration.resetValues();
        buckets.clear();

        Table table = new Table(this.buckets);
        table.fillPages(path, buckets);
        this.table = table;
    }
}
