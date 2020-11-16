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

    private List<Integer> data;
    private final HashMap<String, String> properties;

    public HistogramPlotter() {
        this.data = new ArrayList<>();
        this.properties = new HashMap<>();
    }

    @Override
    public void draw() {
        int seriesEnd = getSeriesEnd();
        if (seriesEnd == 0) {
            seriesEnd = data.size();
        }
        for (int i = getSeriesStart(); i < seriesEnd; i++) {
            System.out.println(data.get(i));
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
}
