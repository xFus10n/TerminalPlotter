package com.plotter.implementations;

import com.plotter.domain.RenderData;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ComputeHistogram {
    private List<Integer> data;
    private String pixel = "";
    private String noPixel = "";
    private int width = 1;
    private int interval = 0;
    private int normalizationScale = 0;
    private int deNormalizedMaxValue;

    public ComputeHistogram(List<Integer> data) {
        this.data = data;
    }

    public void setPixel(String pixel) {
        if (!pixel.equals("")) {
            this.pixel = pixel;
        }
    }

    public void setNoPixel(String noPixel) {
        if (!noPixel.equals("")) {
            this.noPixel = noPixel;
        }
    }

    public void setNormalization(int scale) {
        this.normalizationScale = scale;
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

    private void normalize() {
        System.out.println("Normalization scale : " + normalizationScale);
        List<Integer> normalized = new ArrayList<>();
        int max = getMaxValue();
        setDeNormalizedMaxValue(max); /* remember for denorm */
        for (Integer integer : data) {
            double normCount = ((double) integer / max) * normalizationScale;
            int rounded = (int) Math.round(normCount);
            normalized.add(rounded);
        }
        this.data = normalized;
    }

    public List<RenderData> render() {
        if (normalizationScale != 0) normalize();
        List<RenderData> out = new ArrayList<>();
        StringBuilder row = new StringBuilder();
        int count = 0;
        for (int i = 0; i <= getMaxValue(); i++) { //rows
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
            if (normalizationScale != 0)
                tmpMaxValue = getDeNormalizedValue(tmpMaxValue); /* recalculate if normalized */
            out.add(new RenderData(tmpMaxValue, row.toString()));
            count++;
        }
        out.addAll(renderXScale(getXScale()));
        return out;
    }

    private int[][] getXScale() {
        int maxDecimalsPlaces = String.valueOf(this.data.size()).length();
        int[][] output = new int[maxDecimalsPlaces][this.data.size()];
        for (int j = 0; j < this.data.size(); j++) { //cols
            char[] charArray = String.valueOf(j + 1).toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                output[i][j] = Integer.parseInt(String.valueOf(c));
            }
        }
        return output;
    }

    private ArrayList<RenderData> renderXScale(int[][] scale){
        int step = getWidth() - 1;
        ArrayList<RenderData> renderData = new ArrayList<>();
        for (int[] ints : scale) {
            int count = 0;
            StringBuilder builder = new StringBuilder();
            for (int anInt : ints) {
                String appender;
                if (count++ < 9 && anInt == 0) {
                    appender = " ";
                } else appender = String.valueOf(anInt);
                builder.append(appender)
                        .append(StringUtils.repeat(" ", step))
                        .append(" ");
            }
            renderData.add(new RenderData(0, builder.toString()));
        }
        return renderData;
    }

    private int getDeNormalizedValue(int value) {
        return ((value * getDeNormalizedMaxValue()) / normalizationScale);
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

    public int getDeNormalizedMaxValue() {
        return deNormalizedMaxValue;
    }

    public void setDeNormalizedMaxValue(int deNormalizedMaxValue) {
        this.deNormalizedMaxValue = deNormalizedMaxValue;
    }
}