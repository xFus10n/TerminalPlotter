package com.plotter;

import com.plotter.domain.PlotterTypes;
import com.plotter.implementations.HistogramPlotter;

import java.util.EnumMap;
import java.util.HashMap;

public class PlotterBuilder {
    private static PlotterBuilder instance;
    private static EnumMap<PlotterTypes, Plotter> container;

    private PlotterBuilder(){}

    public static synchronized PlotterBuilder getInstance(){
        if (instance==null){
            instance = new PlotterBuilder();
            container = new EnumMap<>(PlotterTypes.class);
        }
        return instance;
    }

    public Plotter getPlotter(PlotterTypes plotterTypes){
        if (container.containsKey(plotterTypes)) return container.get(plotterTypes);
        /* create instance */
        Plotter plotter;
        switch (plotterTypes) {
            case HISTOGRAM: plotter = new HistogramPlotter();
                break;
            case SCATTERPLOT: plotter = null; //TODO: implement
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + plotterTypes);
        }
        container.put(plotterTypes, plotter);
        return container.get(plotterTypes);
    }
}
