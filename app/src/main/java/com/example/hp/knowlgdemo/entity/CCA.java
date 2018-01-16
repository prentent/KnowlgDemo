package com.example.hp.knowlgdemo.entity;

import java.io.Serializable;

/**
 * Created by HP on 2018/1/13.
 */

public class CCA implements Serializable{
//    private static final long serialVersionUID=333
    private final String ss;
    private CCA(Builder builder) {
        ss = builder.ss;
    }

    public static final class Builder {
        private String ss;

        public Builder() {
        }

        public Builder ss(String val) {
            ss = val;
            return this;
        }

        public CCA build() {
            return new CCA(this);
        }
    }

}
