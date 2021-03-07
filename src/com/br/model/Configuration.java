package com.br.model;

public class Configuration {
    static private int pageSize;
    static private int bucketSize;
    static private int colisionCount;
    static private int overflowCount;

    public static class Builder {
        private int pageSize = 0;
        private int bucketSize = 0;
        private int colisionCount = 0;
        private int overflowCount = 0;

        public Builder setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder setBucketSize(int bucketSize) {
            this.bucketSize = bucketSize;
            return this;
        }

        public Builder setColisionCount(int colisionCount) {
            this.colisionCount = colisionCount;
            return this;
        }

        public Builder setOverflowCount(int overflowCount) {
            this.overflowCount = overflowCount;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }

    public Configuration() {}

    public Configuration(int pageSize, int bucketSize, int colisionCount, int overflowCount) {
        Configuration.pageSize = pageSize;
        Configuration.bucketSize = bucketSize;
        Configuration.colisionCount = colisionCount;
        Configuration.overflowCount = overflowCount;
    }

    public Configuration(Builder builder) {
        Configuration.pageSize = builder.pageSize;
        Configuration.bucketSize = builder.bucketSize;
    }

    public static void resetValues() {
        Configuration.colisionCount = 0;
        Configuration.overflowCount = 0;
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

    public static int getColisionCount() {
        return colisionCount;
    }

    public static void setColisionCount(int colisionCount) {
        Configuration.colisionCount = colisionCount;
    }

    public static void incrementColisionCount() {
        Configuration.colisionCount++;
    }

    public static int getOverflowCount() {
        return overflowCount;
    }

    public static void setOverflowCount(int overflowCount) {
        Configuration.overflowCount = overflowCount;
    }

    public static void incrementOverflowCount() {
        Configuration.overflowCount++;
    }

    @Override
    public String toString() {
        return "Configuration {" +
                "\npageSize = " + Configuration.pageSize +
                "\nbucketSize = " + Configuration.bucketSize +
                "\ncolisionCount = " + Configuration.colisionCount +
                "\noverflowCount = " + Configuration.overflowCount +
                "\n}";
    }
}
