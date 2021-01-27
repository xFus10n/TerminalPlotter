package com.plotter.implementations;

import com.plotter.domain.RenderData;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ComputeHistogram {
    private List<Integer> data;
    private String pixel = "X";
    private String noPixel = " ";
    private int width = 1;
    private int interval = 0;
    private int normalizationScale = 0;

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

    public void setNormalization(int scale){
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

    private void normalize(){
        System.out.println("Scale set to " + normalizationScale);
        List<Integer> normalized = new ArrayList<>();
        int max = getMaxValue();
        System.out.println("Max value " + normalizationScale + " = " + max + ", 1 = " + max/(double)normalizationScale);
        for (Integer integer : data) {
            double normCount = ( (double) integer / max) * normalizationScale;
            normalized.add((int) normCount);
        }
        this.data = normalized;
    }

//    public static List<Integer> normalize(Dataset<Row> dataset, int scale){
//        System.out.println("Scale 100 -> " + scale);
//        Row[] for100ks = (Row[]) dataset.select(col("for100k")).collect();
//        List<Integer> data = new ArrayList<>();
//        List<Integer> normalized = new ArrayList<>();
//        int max = 0;
//        for (Row row : for100ks) {
//            int i = Integer.parseInt(row.get(0).toString());
//            if (max < i) max = i;
//            data.add(i);
//        }
//        System.out.println("Max value " + scale + " = " + max);
//        for (Integer counts : data) {
//            double normCount = ( (double) counts / max) * scale;
//            normalized.add(((int) normCount));
//        }
//        return normalized;
//    }

    public List<RenderData> render() {
        if (normalizationScale != 0) normalize();
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

    private String applyWidth(String element) {
        return StringUtils.repeat(element, getWidth());
    }

    private String applyInterval() {
        return StringUtils.repeat(noPixel, getInterval());
    }
}