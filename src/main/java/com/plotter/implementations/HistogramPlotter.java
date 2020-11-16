package com.plotter.implementations;

import com.plotter.Plotter;
import com.plotter.PlotterInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistogramPlotter extends Plotter {
    /* Constants */
    public static String SERIES_END = "series.end";
    public static String SERIES_START = "series.start";
    public static String SERIES_PIXEL = "series.pixel";
    public static String SERIES_NONE = "series.none";

    private List<Integer> data;
    private final HashMap<String, String> properties;
    private ComputeHistogram compute;

    public HistogramPlotter() {
        this.data = new ArrayList<>();
        this.properties = new HashMap<>();
    }

    @Override
    public void draw() {
        int seriesEnd = getSeriesEnd();
        int seriesStart = getSeriesStart();
        if (seriesEnd == 0) {
            seriesEnd = data.size();
        }
//        for (int i = getSeriesStart(); i <= seriesEnd; i++) {
//            System.out.println(data.get(i));
//        }

        int[][] render = compute.render();
        for (int[] counts : render) {
            for (int i = 0; i < counts.length; i++) {
                int count = counts[i];
                /* identify - start & stop */
                if (i < seriesStart || i > seriesEnd){
                    System.out.print("_");
                    continue;
                }
                String pix = getSeriesPixel();
                String none = getSeriesNone();
                if (count == 0) {
                    System.out.print(none);
                } else System.out.print(pix);
            }
            System.out.println();
        }
    }

    @Override
    public PlotterInterface setProperty(String property, String value) {
        properties.put(property, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PlotterInterface setData(Object object) {
        try {
            this.data = (List<Integer>) object;
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        this.compute = new ComputeHistogram(this.data);
        return this;
    }

    /* Properties */
    private int getSeriesEnd() {
        if (properties.containsKey(SERIES_END)) {
            return Integer.parseInt(properties.get(SERIES_END));
        }
        return 0;
    }

    private int getSeriesStart() {
        if (properties.containsKey(SERIES_START)) {
            return Integer.parseInt(properties.get(SERIES_START));
        }
        return 0;
    }

    private String getSeriesPixel() {
        if (properties.containsKey(SERIES_PIXEL)) {
            return properties.get(SERIES_PIXEL);
        }
        return "x";
    }

    private String getSeriesNone() {
        if (properties.containsKey(SERIES_NONE)) {
            return properties.get(SERIES_NONE);
        }
        return " ";
    }
}
