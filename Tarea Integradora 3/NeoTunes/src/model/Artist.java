package model;


import java.util.Date;

public class Artist extends UserProducer{

    public Artist(String nickname, String id, Date date, String urlPhoto, String name) {
        super(nickname, id, date, urlPhoto, name);
    }
    
}
