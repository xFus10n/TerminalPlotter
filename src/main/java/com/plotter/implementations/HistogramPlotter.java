package com.plotter.implementations;

import com.plotter.Plotter;
import com.plotter.PlotterInterface;
import com.plotter.domain.RenderData;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistogramPlotter extends Plotter {
    /* Constants */
    public static final String SERIES_END = "series.end";
    public static final String SERIES_START = "series.start";
    public static final String SERIES_PIXEL = "series.pixel";
    public static final String SERIES_NONE = "series.none";
    public static final String SERIES_RESTRICT = "series.restrict";
    public static final String SERIES_COUNTS = "series.counts";
    public static final String SERIES_WIDTH = "series.width";
    public static final String SERIES_INTERVAL = "series.interval";
    public static final String NORMALIZE_ENABLE = "normalize.enable";
    public static final String NORMALIZE_SCALE = "normalize.scale";

    private List<Object> data;
    private final HashMap<String, String> properties;
    private final Compute compute;

    public HistogramPlotter() {
        this.data = new ArrayList<>();
        this.properties = new HashMap<>();
        this.compute = new ComputeHistogram();
    }

    @Override
    public void draw() {
//        int seriesEnd = getSeriesEnd();
//        int seriesStart = getSeriesStart();
//        if (seriesEnd == 0) {
//            seriesEnd = data.size();
//        }
        //test
        int maxNumberOfDigits = getNumberOfDigits(compute.getMaxValue()); /* scale digits for correct output */
        compute.setWidth(getSeriesWidth());
        compute.setInterval(getSeriesInterval());
        compute.setPixel(getSeriesPixel());
        compute.setNoPixel(getSeriesNone());
        if (enableNormalize()) compute.setNormalization(getNormalizationScale());
        List<RenderData> render = compute.render();
        for (RenderData renderData : render) {
            if (showCounts()) System.out.print(correctScaleNumber(renderData.getCounts(), maxNumberOfDigits) + getSeriesNone());
            System.out.println(renderData.getRow());
        }
    }

    private String correctScaleNumber(int counts, int maxNumberOfDigits){
        int currentNumberOfDigits = getNumberOfDigits(counts);
        int lead = maxNumberOfDigits - currentNumberOfDigits;
        return addLeadingZeros(lead, counts);
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
            this.data = (List<Object>) object;
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        this.compute.setData(this.data);
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
        return "X";
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

    private boolean showCounts() {
        if (properties.containsKey(SERIES_COUNTS)) {
            return Boolean.parseBoolean(properties.get(SERIES_COUNTS));
        }
        return false;
    }

    private int getSeriesWidth() {
        if (properties.containsKey(SERIES_WIDTH)) {
            return Integer.parseInt(properties.get(SERIES_WIDTH));
        }
        return 1;
    }

    private int getSeriesInterval() {
        if (properties.containsKey(SERIES_INTERVAL)) {
            return Integer.parseInt(properties.get(SERIES_INTERVAL));
        }
        return 0;
    }

    private int getNumberOfDigits(int val){
        return String.valueOf(val).length();
    }
    private String addLeadingZeros(int numberOfZeros, int val){
        String leadingZeros = StringUtils.repeat("0", numberOfZeros);
        return leadingZeros + val;
    }

    private boolean enableNormalize() {
        return properties.containsKey(NORMALIZE_ENABLE);
    }

    private int getNormalizationScale() {
        if (properties.containsKey(NORMALIZE_SCALE)) {
            return Integer.parseInt(properties.get(NORMALIZE_SCALE));
        }
        return 20; /* optimized value */
    }
}