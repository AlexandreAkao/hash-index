package com.br.model;

import java.util.ArrayList;

public class Page {
    private ArrayList<String> register;
    private int maxSize;
    private Page nextPage;

    public Page(int maxSize) {
        this.register = new ArrayList<>();
        this.maxSize = maxSize;
    }

    public Page getNextPage() {
        return nextPage;
    }

    public void setNextPage(Page nextPage) {
        this.nextPage = nextPage;
    }

    public ArrayList<String> getRegister() {
        return register;
    }

    public boolean setRegister(String text) {
        int size = this.register.size();

        if (size < this.maxSize) {
            register.add(text);
            return true;
        } else {
            Page newPage = new Page(maxSize);
            newPage.setRegister(text);

            this.nextPage = newPage;
            return false;
        }
    }
}
