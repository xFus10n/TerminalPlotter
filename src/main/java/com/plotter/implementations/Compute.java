package com.plotter.implementations;

import com.plotter.domain.RenderData;

import java.util.ArrayList;
import java.util.List;

public interface Compute {
    List<RenderData> render();
    void normalize();
    ArrayList<RenderData> renderXScale(Object scale);
    Object getXScale();
    void setData(List<Object> data);
    int getMaxValue();
    void setPixel(String pixel);
    void setNoPixel(String noPixel);
    void setNormalization(int scale);
    void setWidth(int width);
    void setInterval(int interval);
}
