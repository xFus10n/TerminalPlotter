package com.plotter.implementations;

import java.util.List;

public class ComputeHistogram {
    private final List<Integer> data;
    private final int[][] output;

    public ComputeHistogram(List<Integer> data) {
        this.data = data;
        // int[rows][cols]
        this.output = new int[getMaxValue()][this.data.size()];
    }

    private int getMaxValue(){
        int max = 0;
        for (Integer val : data) {
            if (val > max) max = val;
        }
        return max;
    }

    protected int[][] render(){
        int count = 0;
        for (int i = 0; i < getMaxValue(); i++) { //rows
            int tmpMaxValue = getMaxValue() - count;
            for (int j = 0; j < this.data.size(); j++) { //cols
                Integer element = this.data.get(j);
                if (element == tmpMaxValue) {
                    output[i][j]=1;
                }else{
                    output[i][j]=0;
                }
            }
            count++;
        }
        return this.output;
    }
}
