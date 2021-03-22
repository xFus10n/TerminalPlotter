package com.plotter.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RenderData implements Comparator<RenderData> {
    private final int counts;
    private String row;

    public RenderData(int counts, String row) {
        this.counts = counts;
        this.row = row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getCounts() {
        return counts;
    }

    public String getRow() {
        return row;
    }

    @Override
    public int compare(RenderData o1, RenderData o2) {
        return o1.getCounts() - o2.getCounts();
    }

    public static List<RenderData> splitRenderData(RenderData renderData, int size){
        int counts = renderData.counts;
        String row = renderData.row;
        List<RenderData> output = new ArrayList<>();
        while (row.length() > size) {
            String currentRow = row.substring(0,size);
            row = row.substring(size);
            output.add(new RenderData(counts, currentRow));
        }
        output.add(new RenderData(counts, row)); /* last row */
        return output;
    }
}
