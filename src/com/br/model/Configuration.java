package com.br.model;

public class Configuration {
    static private int pageSize;
    static private int bucketSize;

    public Configuration(int pageSize, int bucketSize) {
        Configuration.pageSize = pageSize;
        Configuration.bucketSize = bucketSize;
    }

    public static int getPageSize() {
        return pageSize;
    }

    public static void setPageSize(int pageSize) {
        Configuration.pageSize = pageSize;
    }

    public static int getBucketSize() {
        return bucketSize;
    }

    public static void setBucketSize(int bucketSize) {
        Configuration.bucketSize = bucketSize;
    }
}
