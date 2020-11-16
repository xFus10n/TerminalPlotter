import com.plotter.HistogramPlotter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestHistogramPlotter {
    @Test
    public void name() {
        //test
        HistogramPlotter plotter = new HistogramPlotter();
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        plotter.setProperty("series.count", "9").setData(integers);
        plotter.draw();
    }
}
