import com.plotter.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestHistogramPlotter {
    @Test
    public void basic() {
        Plotter plotter = PlotterBuilder.getInstance(PlotterTypes.HISTOGRAM);
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        plotter.setProperty("series.count", "9").setData(integers);
        plotter.draw();
    }
}