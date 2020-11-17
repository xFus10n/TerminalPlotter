package com.plotter.implementations;

import java.util.List;

public class ComputeHistogram {
    private final List<Integer> data;
    private final int[][] output;
    public static final int isPixel = -1;
    public static final int noPixel = 0;

    public ComputeHistogram(List<Integer> data, boolean displayCounts) {
        this.data = data;
        int size = this.data.size();
        int max = getMaxValue();
        // int[rows][cols]
        if (displayCounts) size++; /* add element for scale / counts */
        this.output = new int[max][size];
    }

    private int getMaxValue(){
        int max = 0;
        for (Integer val : data) {
            if (val > max) max = val;
        }
        return max;
    }

    protected int[][] render(boolean displayCounts){
        int count = 0;
        for (int i = 0; i < getMaxValue(); i++) { //rows
            int tmpMaxValue = getMaxValue() - count;
            for (int j = 0; j < this.data.size(); j++) { //cols
                Integer element = this.data.get(j);
                int k = j; /* for shifting */
                if (k==0 && displayCounts) output[i][k]=tmpMaxValue; /* put counts into first element if displayCounts == true */
                if (displayCounts) k++; /* increment if displayCounts == true */
                if (element >= tmpMaxValue) { /* fill in the matrix */
                    output[i][k]=isPixel;
                }else{
                    output[i][k]=noPixel;
                }
            }
            count++;
        }
        return this.output;
    }
}