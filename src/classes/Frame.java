package classes;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Frame {

    private int number_of_channels;
    ArrayList<Integer> channel_data=new ArrayList<>();
    private String formattedTime;
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME ;

    //Constructors


    public Frame() {
    }

    public Frame(ArrayList<Integer> channel_data) {
        this.channel_data = channel_data;
        LocalTime time=LocalTime.now();
        formattedTime = formatter.format(time);
    }


    //Setters

    public void setNumber_of_channels(int number_of_channels) {
        this.number_of_channels = number_of_channels;
    }

    public void setChannel_data(ArrayList<Integer> channel_data) {
        this.channel_data = channel_data;
    }

    public void setTime(String time) {
        this.formattedTime = time;
    }

    //Getters

    public int getNumber_of_channels() {
        return number_of_channels;
    }

    public ArrayList<Integer> getChannel_data() {
        return channel_data;
    }

    public String getTime() {
        return formattedTime;
    }
}
