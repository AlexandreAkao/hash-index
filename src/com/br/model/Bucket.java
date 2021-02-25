package com.br.model;

public class Bucket {
    private Page[] page;

    public Bucket(int refSize) {
        this.page = new Page[refSize];
    }
}
