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

    private int numberOfChannels=8;

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
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (null != receivedFrame) {
                        receivedFrame.setNumberOfChannels(receivedFrame.getChannelData().size());
                        if (receivedFrame.getNumberOfChannels() != numberOfChannels) {
                            numberOfChannels = receivedFrame.getNumberOfChannels();
                            for (int i = 8; i > numberOfChannels; i--) {
                                int finalI = i;
                                Platform.runLater(() -> {
                                    switch (finalI) {
                                        case 1:
                                            Chart.getInstance().checkOne.setDisable(true);
                                            break;
                                        case 2:
                                            Chart.getInstance().checkTwo.setDisable(true);
                                            break;
                                        case 3:
                                            Chart.getInstance().checkThree.setDisable(true);
                                            break;
                                        case 4:
                                            Chart.getInstance().checkFour.setDisable(true);
                                            break;
                                        case 5:
                                            Chart.getInstance().checkFive.setDisable(true);
                                            break;
                                        case 6:
                                            Chart.getInstance().checkSix.setDisable(true);
                                            break;
                                        case 7:
                                            Chart.getInstance().checkSeven.setDisable(true);
                                            break;
                                        case 8:
                                            Chart.getInstance().checkEight.setDisable(true);
                                            break;
                                    }
                                });
                            }
                        }

                        for (int i = 0; i < receivedFrame.getNumberOfChannels(); i++) {
                            final int data = receivedFrame.getChannelData().get(i);
                            final int j = i;
                            final Frame frame = receivedFrame;
                            final XYChart.Data<Date, Number> chartdata=new XYChart.Data<>(frame.getTime(),data);
                                switch (j) {
                                    case 0:
                                        if (null != series1.getData())
                                            Platform.runLater(() -> series1.getData().add(chartdata));
                                        break;
                                    case 1:
                                        if (null != series2.getData())
                                            Platform.runLater(() -> series2.getData().add(chartdata));
                                        break;
                                    case 2:
                                        if (null != series3.getData())
                                            Platform.runLater(() -> series3.getData().add(chartdata));
                                        break;
                                    case 3:
                                        if (null != series4.getData())
                                            Platform.runLater(() -> series4.getData().add(chartdata));
                                        break;
                                    case 4:
                                        if (null != series5.getData())
                                            Platform.runLater(() -> series5.getData().add(chartdata));
                                        break;
                                    case 5:
                                        if (null != series6.getData())
                                            Platform.runLater(() -> series6.getData().add(chartdata));
                                        break;
                                    case 6:
                                        if (null != series7.getData())
                                            Platform.runLater(() -> series7.getData().add(chartdata));
                                        break;
                                    case 7:
                                        if (null != series8.getData())
                                            Platform.runLater(() -> series8.getData().add(chartdata));
                                        break;
                                }
                        }
                        boolean update = Chart.getInstance().update();
                        int samples = Chart.getInstance().numberOfSamples();
                        if (update) {
                            for(int i=0; i<numberOfChannels; i++) {
                                final int removeData = series1.getData().size() - samples;
                                final int j=i;
                                Platform.runLater(() -> {
                                  switch (j){
                                      case 0:
                                          if (null != series1.getData())
                                              if (series1.getData().get(0) != null) {
                                                  series1.getData().remove(0, removeData);
                                              }
                                          break;
                                      case 1:
                                          if (null != series2.getData())
                                              if (series2.getData().get(0) != null) {
                                                  series2.getData().remove(0, removeData);
                                              }
                                          break;
                                      case 2:
                                          if (null != series3.getData())
                                              if (series3.getData().get(0) != null) {
                                                  series3.getData().remove(0, removeData);
                                              }
                                          break;
                                      case 3:
                                          if (null != series4.getData())
                                              if (series4.getData().get(0) != null) {
                                                  series4.getData().remove(0, removeData);
                                              }
                                          break;
                                      case 4:
                                          if (null != series5.getData())
                                              if (series5.getData().get(0) != null ) {
                                                  series5.getData().remove(0, removeData);
                                              }
                                          break;
                                      case 5:
                                          if (null != series6.getData())
                                              if (series6.getData().get(0) != null) {
                                                  series6.getData().remove(0, removeData);
                                              }
                                          break;
                                      case 6:
                                          if (null != series7.getData())
                                              if (series7.getData().get(0) != null) {
                                                  series7.getData().remove(0, removeData);
                                              }
                                          break;
                                      case 7:
                                          if (null != series8.getData())
                                              if (series8.getData().get(0) != null) {
                                                  series8.getData().remove(0, removeData);
                                              }
                                          break;
                                  }
                                });
                            }
                        }
                    }
                }
            }
        });
        plottingThread.setDaemon(true);
        plottingThread.setName("Plotting thread");
        plottingThread.start();
    }
}

