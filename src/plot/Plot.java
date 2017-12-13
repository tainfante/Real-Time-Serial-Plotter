package plot;

import classes.Frame;
import javafx.scene.chart.XYChart;
import mainwindow.Log;
import port.Port;
import port.PortReader;

import java.util.ArrayList;

import static mainwindow.Chart.series_1;
import static mainwindow.Chart.series_2;
import static mainwindow.Chart.series_3;
import static mainwindow.Chart.series_4;
import static mainwindow.Chart.series_5;
import static mainwindow.Chart.series_6;
import static mainwindow.Chart.series_7;
import static mainwindow.Chart.series_8;


public class Plot {

    private boolean stop=false;

    private static volatile Plot PlotINSTANCE;

    public boolean stopPlotting(){
        return stop=true;

    }

    public static Plot getInstance()
    {
        if (null == PlotINSTANCE)
        {
            synchronized (Plot.class)
            {
                if (null == PlotINSTANCE)
                {
                    PlotINSTANCE = new Plot();
                }
            }
        }
        return PlotINSTANCE;
    }

    public void plotData() throws InterruptedException {
        Frame frame=new Frame();
        int data;
        frame=PortReader.getInstance().getFrameBuffer().take();
        frame.setNumber_of_channels(PortReader.getInstance().getFrameBuffer().size());
        for(int i=0; i<frame.getNumber_of_channels();i++){
            data=frame.getChannel_data().get(i);

            switch (i){
                case 0:
                    series_1.getData().add(new XYChart.Data(frame.getTime(), data));
                case 1:
                    series_2.getData().add(new XYChart.Data(frame.getTime(), data));
                case 2:
                    series_3.getData().add(new XYChart.Data(frame.getTime(), data));
                case 3:
                    series_4.getData().add(new XYChart.Data(frame.getTime(), data));
                case 4:
                    series_5.getData().add(new XYChart.Data(frame.getTime(), data));
                case 5:
                    series_6.getData().add(new XYChart.Data(frame.getTime(), data));
                case 6:
                    series_7.getData().add(new XYChart.Data(frame.getTime(), data));
                case 7:
                    series_8.getData().add(new XYChart.Data(frame.getTime(), data));

            }
        }

    }
    public void startPlotting(){
        Thread plotThread=new Thread(()->{

            while(!stop)
            {
                try
                {
                    plotData();
                }
                catch (Exception e)
                {
                    break;
                }
            }

        });
        plotThread.start();

    }

}
