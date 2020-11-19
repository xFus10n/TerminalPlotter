package com.plotter.implementations;

import com.plotter.domain.RenderData;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ComputeHistogramV2 {
    private final List<Integer> data;
    private String pixel = "X";
    private String noPixel = " ";
    private int width = 1;
    private int interval = 0;

    public ComputeHistogramV2(List<Integer> data) {
        this.data = data;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public void setNoPixel(String noPixel) {
        this.noPixel = noPixel;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getMaxValue() {
        int max = 0;
        for (Integer val : data) {
            if (val > max) max = val;
        }
        return max;
    }

    public List<RenderData> render() {
        List<RenderData> out = new ArrayList<>();
        StringBuilder row = new StringBuilder();
        int count = 0;
        for (int i = 0; i < getMaxValue(); i++) { //rows
            int tmpMaxValue = getMaxValue() - count;
            row.setLength(0);
            for (int j = 0; j < this.data.size(); j++) { //cols
                Integer element = this.data.get(j);
                if (element >= tmpMaxValue) { /* fill in the matrix */
                    row.append(applyWidth(pixel));
                } else {
                    row.append((applyWidth(noPixel)));
                }
                row.append(applyInterval());
            }
            out.add(new RenderData(tmpMaxValue, row.toString()));
            count++;
        }
        return out;
    }

    public int getWidth() {
        return width;
    }

    public int getInterval() {
        return interval;
    }

    private String applyWidth(String element){
        return StringUtils.repeat(element, getWidth());
    }

    private String applyInterval(){
        return StringUtils.repeat(noPixel, getInterval());
    }
}