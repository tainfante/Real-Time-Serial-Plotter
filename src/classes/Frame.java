package classes;

public class Frame {

    private byte length;
    private Integer channel_1_data;
    private Integer channel_2_data;
    private Integer channel_3_data;
    private Integer channel_4_data;
    private Integer channel_5_data;
    private Integer channel_6_data;
    private Integer channel_7_data;
    private Integer channel_8_data;
    private String time;

    //Constructors

    public Frame(String time, byte length, Integer channel_1_data) {
        this.length = length;
        this.channel_1_data = channel_1_data;
        this.time = time;
    }
    public Frame(String time, byte length, Integer channel_1_data, Integer channel_2_data) {
        this.length=length;
        this.channel_1_data = channel_1_data;
        this.channel_2_data = channel_2_data;
        this.time = time;
    }
    public Frame(String time, byte length, Integer channel_1_data, Integer channel_2_data,
                 Integer channel_3_data) {
        this.length=length;
        this.channel_1_data = channel_1_data;
        this.channel_2_data = channel_2_data;
        this.channel_3_data = channel_3_data;
        this.time = time;
    }
    public Frame(String time, byte length, Integer channel_1_data, Integer channel_2_data,
                 Integer channel_3_data, Integer channel_4_data) {
        this.length=length;
        this.channel_1_data = channel_1_data;
        this.channel_2_data = channel_2_data;
        this.channel_3_data = channel_3_data;
        this.channel_4_data = channel_4_data;
        this.time = time;
    }
    public Frame(String time, byte length, Integer channel_1_data, Integer channel_2_data,
                 Integer channel_3_data, Integer channel_4_data, Integer channel_5_data) {
        this.length=length;
        this.channel_1_data = channel_1_data;
        this.channel_2_data = channel_2_data;
        this.channel_3_data = channel_3_data;
        this.channel_4_data = channel_4_data;
        this.channel_5_data = channel_5_data;
        this.time = time;
    }
    public Frame(String time, byte length, Integer channel_1_data, Integer channel_2_data,
                 Integer channel_3_data, Integer channel_4_data, Integer channel_5_data,
                 Integer channel_6_data) {
        this.length=length;
        this.channel_1_data = channel_1_data;
        this.channel_2_data = channel_2_data;
        this.channel_3_data = channel_3_data;
        this.channel_4_data = channel_4_data;
        this.channel_5_data = channel_5_data;
        this.channel_6_data = channel_6_data;
        this.time = time;
    }
    public Frame(String time, byte length, Integer channel_1_data, Integer channel_2_data,
                 Integer channel_3_data, Integer channel_4_data, Integer channel_5_data,
                 Integer channel_6_data, Integer channel_7_data) {
        this.length=length;
        this.channel_1_data = channel_1_data;
        this.channel_2_data = channel_2_data;
        this.channel_3_data = channel_3_data;
        this.channel_4_data = channel_4_data;
        this.channel_5_data = channel_5_data;
        this.channel_6_data = channel_6_data;
        this.channel_7_data = channel_7_data;
        this.time = time;
    }
    public Frame(String time, byte length, Integer channel_1_data, Integer channel_2_data,
                 Integer channel_3_data, Integer channel_4_data, Integer channel_5_data,
                 Integer channel_6_data, Integer channel_7_data, Integer channel_8_data) {
        this.length=length;
        this.channel_1_data = channel_1_data;
        this.channel_2_data = channel_2_data;
        this.channel_3_data = channel_3_data;
        this.channel_4_data = channel_4_data;
        this.channel_5_data = channel_5_data;
        this.channel_6_data = channel_6_data;
        this.channel_7_data = channel_7_data;
        this.channel_8_data = channel_8_data;
        this.time = time;
    }

    //Setters

    public void setLength(byte length) {
        this.length = length;
    }

    public void setChannel_1_data(Integer channel_1_data) {
        this.channel_1_data = channel_1_data;
    }

    public void setChannel_2_data(Integer channel_2_data) {
        this.channel_2_data = channel_2_data;
    }

    public void setChannel_3_data(Integer channel_3_data) {
        this.channel_3_data = channel_3_data;
    }

    public void setChannel_4_data(Integer channel_4_data) {
        this.channel_4_data = channel_4_data;
    }

    public void setChannel_5_data(Integer channel_5_data) {
        this.channel_5_data = channel_5_data;
    }

    public void setChannel_6_data(Integer channel_6_data) {
        this.channel_6_data = channel_6_data;
    }

    public void setChannel_7_data(Integer channel_7_data) {
        this.channel_7_data = channel_7_data;
    }

    public void setChannel_8_data(Integer channel_8_data) {
        this.channel_8_data = channel_8_data;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //Getters

    public byte getLength() {
        return length;
    }

    public Integer getChannel_1_data() {
        return channel_1_data;
    }

    public Integer getChannel_2_data() {
        return channel_2_data;
    }

    public Integer getChannel_3_data() {
        return channel_3_data;
    }

    public Integer getChannel_4_data() {
        return channel_4_data;
    }

    public Integer getChannel_5_data() {
        return channel_5_data;
    }

    public Integer getChannel_6_data() {
        return channel_6_data;
    }

    public Integer getChannel_7_data() {
        return channel_7_data;
    }

    public Integer getChannel_8_data() {
        return channel_8_data;
    }

    public String getTime() {
        return time;
    }
}
