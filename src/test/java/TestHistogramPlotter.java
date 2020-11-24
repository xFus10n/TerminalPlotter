import com.plotter.*;
import com.plotter.domain.PlotterTypes;
import com.plotter.domain.RenderData;
import com.plotter.implementations.ComputeHistogram;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.plotter.implementations.HistogramPlotter.*;

import java.util.Arrays;
import java.util.List;


public class TestHistogramPlotter {
    @Test
    public void basic() {
        Plotter plotter = PlotterBuilder.getInstance(PlotterTypes.HISTOGRAM);
        List<Integer> integers = Arrays.asList(1, 2, 4, 8, 15, 12, 10, 7, 5, 2, 1, 2, 4, 8, 15, 12, 10, 7, 5, 2,
                1, 2, 4, 8, 15, 12, 10, 7, 5, 2, 1, 2, 4, 8, 15, 12, 10, 7, 5, 2);
        plotter
//                .setProperty("series.end", "5") /* not implemented */
//                .setProperty("series.start", "1") /* not implemented */
//                .setProperty("series.restrict", ".") /* not implemented */
                .setProperty(SERIES_PIXEL, "*")
                .setProperty(SERIES_NONE, ".")
                .setProperty(SERIES_WIDTH, "2")
                .setProperty(SERIES_INTERVAL, "1")
                .setProperty(SERIES_COUNTS, "true")
//                .setProperty(SERIES_VALS, "true") /* working not properly */
                .setData(integers);
        plotter.draw();
    }

    @Test
    public void compute() {
        List<Integer> integers = Arrays.asList(1, 2, 4, 8, 15, 12, 10, 7, 5, 2);
        ComputeHistogram histo = new ComputeHistogram(integers);
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