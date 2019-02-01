package com.lzr.com.utils_lib.common;

public class DensityData {
    private int width;
    private int height;
    private float density;
    private int densityDpi;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public int getDensityDpi() {
        return densityDpi;
    }

    public void setDensityDpi(int densityDpi) {
        this.densityDpi = densityDpi;
    }

    @Override
    public String toString() {
        return "DensityData{" +
                "width=" + width +
                ", height=" + height +
                ", density=" + density +
                ", densityDpi=" + densityDpi +
                '}';
    }
}
