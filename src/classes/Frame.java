package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Frame {

    private int numberOfChannels;
    private ArrayList<Integer> channelData = new ArrayList<>();
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

    public String toString(boolean checks[], String format)
    {
        StringBuilder s = new StringBuilder();

        for(short i = 0; i < numberOfChannels;i++)
        {
            if (checks[i])
            {
                if(format.equals("hex"))
                {
                    s.append(Integer.toHexString(channelData.get(i))).append(" ");
                }
                else if(format.equals("decimal"))
                {
                    s.append(channelData.get(i).toString()).append(" ");
                }
            }
            else
            {
                s.append("  ");
            }
        }

        s.deleteCharAt(s.length() - 1);

        return s.toString();
    }
}
