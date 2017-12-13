package classes;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Frame {

    private byte number_of_channels;
    private Integer[] channel_data=new Integer[10];
    private String formattedTime;
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME ;

    //Constructors

    public Frame(Integer[] channel_data) {
        this.channel_data = channel_data;
        LocalTime time=LocalTime.now();
        formattedTime = formatter.format(time);
    }


    //Setters

    public void setNumber_of_channels(byte number_of_channels) {
        this.number_of_channels = number_of_channels;
    }

    public void setChannel_data(Integer[] channel_data) {
        this.channel_data = channel_data;
    }

    public void setTime(String time) {
        this.formattedTime = time;
    }

    //Getters

    public byte getNumber_of_channels() {
        return number_of_channels;
    }

    public Integer[] getChannel_data() {
        return channel_data;
    }

    public String getTime() {
        return formattedTime;
    }
}
