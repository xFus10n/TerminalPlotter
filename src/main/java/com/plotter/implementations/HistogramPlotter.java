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
    public static String SERIES_RESTRICT = "series.restrict";
    public static String SERIES_COUNTS = "series.counts";

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
        if (getSeriesCounts()) seriesStart++;seriesEnd++; /* shift right since element[0] is counts lvl */

        int[][] render = compute.render(getSeriesCounts());
        for (int[] counts : render) {
            for (int i = 0; i < counts.length; i++) {
                int count = counts[i];
                /* identify - start & stop */
                if (getSeriesCounts() && i == 0) { /* jump over if we need to show scale */
                } else {
                    if (i < seriesStart || i > seriesEnd) { /* if upper / lower limit set */
                        System.out.print(getSeriesRestrict()); /* show restricted symbol*/
                        continue;
                    }
                }
                String pix = getSeriesPixel();
                String none = getSeriesNone();
                if (count == ComputeHistogram.noPixel) {
                    System.out.print(none);
                } else if (count == ComputeHistogram.isPixel) {
                    System.out.print(pix);
                } else {
                    displayCountsScale(count);
                }
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
        this.compute = new ComputeHistogram(this.data, getSeriesCounts());
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

    private String getSeriesRestrict() {
        if (properties.containsKey(SERIES_RESTRICT)) {
            return properties.get(SERIES_RESTRICT);
        }
        return "";
    }

    private boolean getSeriesCounts() {
        if (properties.containsKey(SERIES_COUNTS)) {
            return Boolean.parseBoolean(properties.get(SERIES_COUNTS));
        }
        return false;
    }

    private void displayCountsScale(int counts) {
        System.out.print(counts + "|");
    }
}
