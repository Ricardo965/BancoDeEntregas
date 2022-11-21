package model;


import java.util.Date;


public abstract class  User {
    private String nickname;
    private String id;
    private Date date;
    


    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public User(String nickname, String id, Date date) {
        this.nickname = nickname;
        this.id = id;
        this.date = date;
    }

    @Override
    public String toString() {
        return "\nNickname: " + nickname + "\nid: " + id + "\nFecha: " + date.toString();
    }
    

    
}