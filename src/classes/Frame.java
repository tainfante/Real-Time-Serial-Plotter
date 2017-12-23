package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Frame {

    private int numberOfChannels;
    ArrayList<Integer> channelData=new ArrayList<>();
    private Date date;

    //Constructors

    public Frame() {

    }

    public Frame(ArrayList<Integer> channelData) {
        this.channelData = channelData;
        date=new GregorianCalendar().getTime();
        int numberOfChannels=channelData.size();
        setNumberOfChannels(numberOfChannels);
    }
    
    //Setters

    public void setNumberOfChannels(int numberOfChannels) {
        this.numberOfChannels = numberOfChannels;
    }

    public void setChannelData(ArrayList<Integer> channelData) {
        this.channelData = channelData;
    }

    public void setTime(Date date) {
        this.date = date;
    }

    //Getters

    public int getNumberOfChannels() {
        return numberOfChannels;
    }

    public ArrayList<Integer> getChannelData() {
        return channelData;
    }

    public Date getTime() {
        return date;
    }
}
