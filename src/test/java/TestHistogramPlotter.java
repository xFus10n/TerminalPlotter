import com.plotter.*;
import com.plotter.domain.PlotterTypes;
import com.plotter.domain.RenderData;
import com.plotter.implementations.ComputeHistogram;
import com.plotter.implementations.HistogramPlotter;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class TestHistogramPlotter {
    @Test
    public void basic() {
        PlotterBuilder plotBuilder = PlotterBuilder.getInstance();
        Plotter plotter = plotBuilder.getPlotter(PlotterTypes.HISTOGRAM);
        List<Integer> integers = Arrays.asList(1, 2, 4, 8, 15, 12, 10, 7, 5, 2, 2, 4, 8, 15, 12, 10, 7, 5, 2);
        plotter
//                .setProperty("series.end", "5")
//                .setProperty("series.start", "1")
//                .setProperty("series.restrict", ".")
                .setProperty(HistogramPlotter.SERIES_PIXEL, "*")
                .setProperty(HistogramPlotter.SERIES_NONE, ".")
                .setProperty(HistogramPlotter.SERIES_WIDTH, "3")
                .setProperty(HistogramPlotter.SERIES_INTERVAL, "1")
                .setProperty(HistogramPlotter.NORMALIZE_ENABLE, "true")
                .setProperty(HistogramPlotter.NORMALIZE_SCALE, "10")
                .setProperty(HistogramPlotter.SERIES_COUNTS, "true")
                .setProperty(HistogramPlotter.SERIES_LENGTH, "50")
                .setData(integers);
        plotter.draw();
    }

    @Test
    public void testRenderSplit(){
        RenderData renderData = new RenderData(20, "abcdefghijklmnopqrst");
        List<RenderData> renderList = RenderData.splitRenderData(renderData, 3);
        renderList.forEach(render -> System.out.println(render.getRow()));
    }
}