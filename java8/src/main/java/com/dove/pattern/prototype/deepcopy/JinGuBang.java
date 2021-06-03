package com.dove.pattern.prototype.deepcopy;

import java.io.Serializable;

public class JinGuBang implements Serializable {
    private float h = 100;

    private float w = 20000;

    public float getH() {
        return this.h;
    }

    public void setH(final float h) {
        this.h = h;
    }

    public float getW() {
        return this.w;
    }

    public void setW(final float w) {
        this.w = w;
    }
}
