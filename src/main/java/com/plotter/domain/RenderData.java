package com.plotter.domain;

import java.util.Comparator;

public class RenderData implements Comparator<RenderData> {
    private int counts;
    private String row;

    public RenderData(int counts, String row) {
        this.counts = counts;
        this.row = row;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    @Override
    public int compare(RenderData o1, RenderData o2) {
        return o1.getCounts() - o2.getCounts();
    }
}
