package com.plotter.implementations;

import com.plotter.domain.RenderData;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ComputeHistogram implements Compute {
    private final List<Integer> data;
    private String pixel = "X";
    private String noPixel = " ";
    private int width = 1;
    private int interval = 0;
    private boolean showValues = false;

    public ComputeHistogram(List<Integer> data) {
        this.data = data;
    }

    public void setPixel(String pixel) {
        if (!pixel.equals("")) {
            this.pixel = pixel;
        }
    }

    public void setShowValues() {
        this.showValues = true;
    }

    public void setNoPixel(String noPixel) {
        if (!noPixel.equals("")) {
            this.noPixel = noPixel;
        }
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
            for (Integer element : this.data) { //cols
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
        /* show values*/
        if (showValues) out.add(new RenderData(0,getValues(data.size())));
        return out;
    }

    public int getWidth() {
        return width;
    }

    public int getInterval() {
        return interval;
    }

    private String applyWidth(String element) {
        return StringUtils.repeat(element, getWidth());
    }

    private String applyInterval() {
        return StringUtils.repeat(noPixel, getInterval());
    }

    private String getValues(int elementSize) {
        //TODO: draw vertical numbering, use interval & width
        return null;
    }

    private int findNumOfDigits(int i){
        int length = String.valueOf(i).length() - 1;
        return length;
    }
}