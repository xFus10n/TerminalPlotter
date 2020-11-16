package com.plotter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistogramPlotter extends Plotter implements PlotterInterface{
    private List<Integer> data;
    private HashMap<String,String> properties;

    public HistogramPlotter() {
        this.data = new ArrayList<>();
        this.properties = new HashMap<>();
    }

    @Override
    public void draw() {
        for (int i = 0; i < getSeriesCount(); i++) {
            System.out.println(data.get(i));
        }
    }

    @Override
    public PlotterInterface setProperty(String property, String value) {
        properties.put(property,value);
        return this;
    }

    @Override
    public PlotterInterface setData(Object object) {
        try {
            this.data = (List<Integer>) object;
        }catch (ClassCastException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return this;
    }

    private int getSeriesCount(){
        return Integer.parseInt(properties.get("series.count"));
    }
}
