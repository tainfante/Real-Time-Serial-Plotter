package mainwindow;

import application.Main;
import classes.Frame;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import port.PortReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CaptureController implements Initializable
{
    public TextField filePathTextField;

    public Button chooseFileButton;
    public Button startOverwriteButton;
    public Button startAppendButton;
    public Button stopButton;

    public RadioButton asHexRadioButton;
    public RadioButton asDecimalRadioButton;

    public CheckBox channel1CheckBox;
    public CheckBox channel2CheckBox;
    public CheckBox channel3CheckBox;
    public CheckBox channel4CheckBox;
    public CheckBox channel5CheckBox;
    public CheckBox channel6CheckBox;
    public CheckBox channel7CheckBox;
    public CheckBox channel8CheckBox;

    private ArrayList<CheckBox> channelCheckBoxes;

    private volatile boolean isActiveExport;

    private File file;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        channelCheckBoxes = new ArrayList<>();

        channelCheckBoxes.add(channel1CheckBox);
        channelCheckBoxes.add(channel2CheckBox);
        channelCheckBoxes.add(channel3CheckBox);
        channelCheckBoxes.add(channel4CheckBox);
        channelCheckBoxes.add(channel5CheckBox);
        channelCheckBoxes.add(channel6CheckBox);
        channelCheckBoxes.add(channel7CheckBox);
        channelCheckBoxes.add(channel8CheckBox);
    }

    public void chooseFileClick()
    {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save as");
        file = fileChooser.showSaveDialog(Main.getPrimaryStage());

        if(null != file)
        {
            filePathTextField.setText(file.getPath());
        }
    }

    public void startOverwriteClick()
    {
        file = new File(filePathTextField.getText());

        if(file.exists())
        {
            if(PortReader.getInstance().isOpen())
            {
                PortReader.getInstance().setExportBufferEnabled(true);
                isActiveExport = true;

                stopButton.setDisable(false);
                //startAppendButton.setDisable(true);
                startOverwriteButton.setDisable(true);

                new Thread(() ->
                {
                    int i;

                    boolean checks[] = new boolean[8];

                    try
                    {
                        PrintWriter printWriter = new PrintWriter(file);

                        while (isActiveExport)
                        {
                            if (!PortReader.getInstance().getExportFrameBuffer().isEmpty())
                            {
                                Frame frame = PortReader.getInstance().getExportFrameBuffer().take();

                                for(i = 0; i < frame.getNumberOfChannels(); i++)
                                {
                                    int finalI = i;
                                    Platform.runLater(() -> channelCheckBoxes.get(finalI).setDisable(false));
                                }

                                for(i = frame.getNumberOfChannels(); i < 8; i++)
                                {
                                    int finalI = i;
                                    Platform.runLater(() -> channelCheckBoxes.get(finalI).setDisable(true));
                                }

                                for(i = 0; i < 8; i++)
                                {
                                    checks[i] = channelCheckBoxes.get(i).isSelected();
                                }

                                printWriter.println(frame.toString(checks));
                            }
                        }

                        printWriter.close();
                    }
                    catch (FileNotFoundException | InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                }).start();

            }
            else
            {
                showInformationAlert("Port must be open to capture!");
            }
        }
        else
        {
            showErrorAlert("Podana ścieżka jest niepoprawna!");
        }
    }

    public void startAppendClick()
    {
        if(PortReader.getInstance().isOpen())
        {
            PortReader.getInstance().setExportBufferEnabled(true);
            isActiveExport = true;

            stopButton.setDisable(false);
            startAppendButton.setDisable(true);
            startOverwriteButton.setDisable(true);

        }
        else
        {
            showInformationAlert("Port must be open to capture!");
        }
    }

    public void stopClick()
    {
        PortReader.getInstance().setExportBufferEnabled(false);
        isActiveExport = false;

        stopButton.setDisable(true);
        //startAppendButton.setDisable(false);
        startOverwriteButton.setDisable(false);

        for(int i = 0; i < 8; i++)
        {
            int finalI = i;
            Platform.runLater(() -> channelCheckBoxes.get(finalI).setDisable(false));
        }

    }

    private void showInformationAlert(String information)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Real Time Serial Plotter");
        alert.setHeaderText(null);
        alert.setContentText(information);
        alert.showAndWait();
    }

    private void showErrorAlert(String error)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Real Time Serial Plotter");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }
}
