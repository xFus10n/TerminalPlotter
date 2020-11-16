import com.plotter.*;
import com.plotter.domain.PlotterTypes;
import com.plotter.implementations.ComputeHistogram;
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
                .setProperty("series.end", "5")
                .setProperty("series.start", "1")
                .setData(integers);
        plotter.draw();
    }

//    @Test
//    public void compute() {
//        List<Integer> integers = Arrays.asList(1, 2, 4, 8, 15, 12, 10, 7, 5, 2);
//        ComputeHistogram histo = new ComputeHistogram(integers);
//        int maxValue = histo.getMaxValue();
//        assertEquals(15, maxValue);
//
//        int[][] render = histo.render();
//        for (int[] counts : render) {
//            for (int count : counts) {
//                String pix = "*";
//                String none = " ";
//                if (count==0){
//                    System.out.print(none);
//                }else System.out.print(pix);
//            }
//            System.out.println();
//        }
//    }
}