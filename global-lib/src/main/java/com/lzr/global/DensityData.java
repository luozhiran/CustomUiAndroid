package com.lzr.global;

public class DensityData {
    private int width;
    private int height;
    private float density;
    private int densityDpi;

    public DensityData() {
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getDensity() {
        return this.density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public int getDensityDpi() {
        return this.densityDpi;
    }

    public void setDensityDpi(int densityDpi) {
        this.densityDpi = densityDpi;
    }

    public String toString() {
        return "DensityData{width=" + this.width + ", height=" + this.height + ", density=" + this.density + ", densityDpi=" + this.densityDpi + '}';
    }
}