package plot;

import classes.DateAxis;
import classes.Frame;
import javafx.application.Platform;
import javafx.concurrent.Task;
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

    private volatile boolean stop = false;

    private static volatile Plot PlotINSTANCE;

    public void stopPlotting() {
        stop = true;

    }

    public void setStopPlotting(boolean stop){
        this.stop=stop;
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

    public void startPlotting() {

        Thread plottingThread=new Thread(new Runnable() {
            Frame receivedFrame;
            @Override
            public void run() {
                while (!stop) {
                    try {
                        receivedFrame = PortReader.getInstance().getFrameBuffer().take();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    receivedFrame.setNumberOfChannels(receivedFrame.getChannelData().size());
                    for (int i = 0; i < receivedFrame.getNumberOfChannels(); i++) {
                        final int data = receivedFrame.getChannelData().get(i);
                        final int j = i;
                        final Frame frame = receivedFrame;

                        Platform.runLater(() -> {

                            switch (j) {
                                case 0:
                                    if (null != series1) series1.getData().add(new XYChart.Data<>(frame.getTime(), data));
                                    break;
                                case 1:
                                    if (null != series2) series2.getData().add(new XYChart.Data<>(frame.getTime(), data));
                                    break;
                                case 2:
                                    if (null != series3) series3.getData().add(new XYChart.Data<>(frame.getTime(), data));
                                    break;
                                case 3:
                                    if (null != series4) series4.getData().add(new XYChart.Data<>(frame.getTime(), data));
                                    break;
                                case 4:
                                    if (null != series5) series5.getData().add(new XYChart.Data<>(frame.getTime(), data));
                                    break;
                                case 5:
                                    if (null != series6) series6.getData().add(new XYChart.Data<>(frame.getTime(), data));
                                    break;
                                case 6:
                                    if (null != series7) series7.getData().add(new XYChart.Data<>(frame.getTime(), data));
                                    break;
                                case 7:
                                    if (null != series8) series8.getData().add(new XYChart.Data<>(frame.getTime(), data));
                                    break;
                            }
                        });
                    }
                    final boolean update = Chart.getInstance().update();
                    final int samples = Chart.getInstance().numberOfSamples();
                    if (update) {
                        Platform.runLater(() -> {
                            try {
                                if (null != series1) series1.getData().remove(0, (series1.getData().size() - samples));
                                if (null != series2) series2.getData().remove(0, (series2.getData().size() - samples));
                                if (null != series3) series3.getData().remove(0, (series3.getData().size() - samples));
                                if (null != series4) series4.getData().remove(0, (series4.getData().size() - samples));
                                if (null != series5) series5.getData().remove(0, (series5.getData().size() - samples));
                                if (null != series6) series6.getData().remove(0, (series6.getData().size() - samples));
                                if (null != series7) series7.getData().remove(0, (series7.getData().size() - samples));
                                if (null != series8) series8.getData().remove(0, (series8.getData().size() - samples));
                            } catch (NullPointerException nul) {
                                System.out.println("No such data in the Series");
                            }
                        });
                    }
                }
                System.out.println("Stopped plotting thread");
            }
        });
        plottingThread.setDaemon(true);
        plottingThread.setName("Plotting thread");
        plottingThread.start();
    }
}

