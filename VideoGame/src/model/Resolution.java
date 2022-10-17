package model;

public enum Resolution {
    SD("Standar Definition", 640, 480), 
    QHD("Quarter of High Definition", 960, 540), 
    HD("High Definition", 1280, 720), 
    FHD("Full High Definition", 1920, 1080), 
    WQHD("Wide Quad High Definition", 2560, 1440), 
    UHD("Ultra High Definition", 3840, 2160),
    UHD8K("Ultra High Definition 8K", 7680, 4320);

    private String name;
    public String getName() {
        return name;
    }

    private int xPixel;
    public int getxPixel() {
        return xPixel;
    }

    private int yPixel;
    
    public int getyPixel() {
        return yPixel;
    }

    private Resolution(String name, int xPixel, int yPixel){

    }

    
}