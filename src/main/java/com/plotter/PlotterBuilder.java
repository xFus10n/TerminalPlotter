package com.plotter;

import com.plotter.domain.PlotterTypes;
import com.plotter.implementations.HistogramPlotter;

public class PlotterBuilder {
    public static Plotter getInstance(PlotterTypes plotterTypes){
        Plotter plotter;
        switch (plotterTypes) {
            case HISTOGRAM: plotter = new HistogramPlotter();
                break;
            case SCATTERPLOT: plotter = null; //TODO: implement
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + plotterTypes);
        }
        return plotter;
    }
}
