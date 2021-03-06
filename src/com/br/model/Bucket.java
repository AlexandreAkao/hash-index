package com.br.model;

import java.util.ArrayList;

public class Bucket {
    private Page[] pages;
    private int size;
    private Bucket nextBucket;

    public Bucket(int bucketSize) {
        this.pages = new Page[bucketSize];
        this.size = 0;
    }

    public int getReg(String name) {
        Bucket currentBucket = this;
        int cost = 0;

        while (currentBucket != null) {
            for (int i = 0; i < size; i++) {
                ArrayList<String> registerList = currentBucket.pages[i].getRegister();

                for (String register : registerList) {
                    if (register.equals(name)) {
                        return cost;
                    } else {
                        cost++;
                    }
                }
            }
            currentBucket = currentBucket.nextBucket;
        }

        return cost;
    }

    public Bucket getNextBucket() {
        return nextBucket;
    }

    public boolean isMax() {
        return Configuration.getBucketSize() - 1 < this.size;
    }

    public Page[] getPages() {
        return pages;
    }

    public void addRef(Page page) {
        if (!isMax()) {
            this.pages[size] = page;
            size++;
        } else {
            if (this.nextBucket == null) {
                Bucket newBucket = new Bucket(Configuration.getBucketSize());
                Configuration.incrementOverflowCount();
                newBucket.addRef(page);
                this.nextBucket = newBucket;
            } else {
                Bucket bucket = this.nextBucket;
                bucket.addRef(page);
            }
        }
    }
}
