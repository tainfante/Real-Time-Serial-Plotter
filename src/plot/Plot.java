package plot;

import classes.DateAxis;
import classes.Frame;
import javafx.scene.chart.XYChart;
import mainwindow.Chart;
import mainwindow.Log;
import port.Port;
import port.PortReader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static mainwindow.Chart.*;


public class Plot {

    private boolean stop=false;

    private static volatile Plot PlotINSTANCE;

    public boolean stopPlotting(){
        return stop=true;

    }

    public static Plot getInstance() {
        if (null == PlotINSTANCE) {
            synchronized (Plot.class) {
                if (null == PlotINSTANCE) {
                    PlotINSTANCE = new Plot();
                }
            }
        }
        return PlotINSTANCE;
    }

    private void plotData() throws InterruptedException {
        Frame frame;
        int data;
        frame=PortReader.getInstance().getFrameBuffer().take();
        frame.setNumberOfChannels(frame.getChannelData().size());
        for(int i=0; i<frame.getNumberOfChannels();i++){
            data=frame.getChannelData().get(i);

            switch (i){
                case 0:
                    series1.getData().add(new XYChart.Data(frame.getTime(), data));
                    break;
                case 1:
                    series2.getData().add(new XYChart.Data(frame.getTime(), data));
                    break;
                case 2:
                    series3.getData().add(new XYChart.Data(frame.getTime(), data));
                    break;
                case 3:
                    series4.getData().add(new XYChart.Data(frame.getTime(), data));
                    break;
                case 4:
                    series5.getData().add(new XYChart.Data(frame.getTime(), data));
                    break;
                case 5:
                    series6.getData().add(new XYChart.Data(frame.getTime(), data));
                    break;
                case 6:
                    series7.getData().add(new XYChart.Data(frame.getTime(), data));
                    break;
                case 7:
                    series8.getData().add(new XYChart.Data(frame.getTime(), data));
                    break;

            }
        }
    }
    public void startPlotting(){
        Thread plotThread=new Thread(()->{

            while(!stop) {
                try {
                    plotData();
                    Chart.getInstance().updateXAxis();
                }
                catch (Exception e) {
                    break;
                }
            }
        });
        plotThread.start();

    }

}
