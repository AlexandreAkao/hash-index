package com.br.model;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private ArrayList<Table> tables;
    private ArrayList<Integer> buckets;
    private int maxSizePage;

    public Database(int maxSizePage) {
        this.tables = new ArrayList<>();
        this.buckets = new ArrayList<>();
        this.maxSizePage = maxSizePage;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    public void readTable(String path) throws FileNotFoundException, UnsupportedEncodingException {
        Table table = new Table(maxSizePage);
        table.fillPages(path, buckets);
        this.tables.add(table);
    }
}
