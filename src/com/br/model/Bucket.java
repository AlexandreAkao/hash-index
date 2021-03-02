package com.br.model;

public class Bucket {
    private Page[] pages;
    private int size;
    private Bucket nextBucket;

    public Bucket(int bucketSize) {
        this.pages = new Page[bucketSize];
        this.size = 0;
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
