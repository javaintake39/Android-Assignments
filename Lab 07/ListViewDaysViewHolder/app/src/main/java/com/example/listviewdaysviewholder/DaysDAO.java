package com.example.listviewdaysviewholder;

public class DaysDAO {
    private String name;
    private String desc;
    private int imgRes;

    public DaysDAO(String name, String desc, int imgRes)
    {
        this.name = name;
        this.desc = desc;
        this.imgRes = imgRes;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getImgRes() {
        return imgRes;
    }

    @Override
    public String toString() {
        return "Day Name "+name;
    }
}
