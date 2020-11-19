import com.plotter.*;
import com.plotter.domain.PlotterTypes;
import com.plotter.domain.RenderData;
import com.plotter.implementations.ComputeHistogram;
import com.plotter.implementations.ComputeHistogramV2;
import com.plotter.implementations.HistogramPlotter;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class TestHistogramPlotter {
    @Test
    public void basic() {
        Plotter plotter = PlotterBuilder.getInstance(PlotterTypes.HISTOGRAM);
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        plotter
//                .setProperty("series.end", "5")
//                .setProperty("series.start", "1")
//                .setProperty("series.restrict", ".")
                .setProperty(HistogramPlotter.SERIES_PIXEL, "*")
                .setProperty(HistogramPlotter.SERIES_NONE, ".")
                .setProperty(HistogramPlotter.SERIES_WIDTH, "3")
                .setProperty(HistogramPlotter.SERIES_INTERVAL, "1")
//                .setProperty("series.counts", "true")
                .setData(integers);
        plotter.draw();
    }

    @Test
    public void compute() {
        List<Integer> integers = Arrays.asList(1, 2, 4, 8, 15, 12, 10, 7, 5, 2);
        ComputeHistogramV2 histo = new ComputeHistogramV2(integers);
        int maxValue = histo.getMaxValue();
        assertEquals(15, maxValue);
        histo.setInterval(1);
        histo.setWidth(2);
        List<RenderData> render = histo.render();
        for (RenderData renderData : render) {
            System.out.print(renderData.getCounts());
            System.out.println(renderData.getRow());
        }
    }
}