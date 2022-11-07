package model;

import java.util.Date;

public abstract class UserProducer extends User{
    private int numreproduction;
    private double timeReproduced;
    private String urlPhoto;
    private String name;
    
    public int getNumreproduction() {
        return numreproduction;
    }
    public void setNumreproduction(int numreproduction) {
        this.numreproduction = numreproduction;
    }
    public double getTimeReproduced() {
        return timeReproduced;
    }
    public void setTimeReproduced(double timeReproduced) {
        this.timeReproduced = timeReproduced;
    }
    public String getUrlPhoto() {
        return urlPhoto;
    }
    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public UserProducer(String nickname, String id, Date date, String urlPhoto, String name) {
        super(nickname, id, date);
        this.numreproduction = 0;
        this.timeReproduced = 0;
        this.urlPhoto = urlPhoto;
        this.name = name;
    }
    @Override
    public String toString() {
        return "\nNombre: " + name + "\nnickname: " + this.getNickname() + "\n id: " + this.getId() + "\nFecha de vinculación: " + this.getDate();
    }

    
}
