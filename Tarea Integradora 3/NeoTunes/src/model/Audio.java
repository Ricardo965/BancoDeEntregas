package model;

public abstract class Audio implements Reproducible{
    private String name;
    private String url;
    private double duration;
    private int amountReproductions;
    private UserProducer author;

    public UserProducer getAuthor() {
        return author;
    }
    public void setAuthor(UserProducer author) {
        this.author = author;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public double getDuration() {
        return duration;
    }
    public void setDuration(double duration) {
        this.duration = duration;
    }
    public int getAmountReproductions() {
        return amountReproductions;
    }
    public void setAmountReproductions(int amountReproductions) {
        this.amountReproductions = amountReproductions;
    }
    public Audio(String name, String url, double duration, UserProducer author) {
        this.name = name;
        this.url = url;
        this.duration = duration;
        this.amountReproductions = 0;
        this.author = author;
    }
    @Override
    public String toString() {
        return "\nNombre: " + name + "\nCantidad de reproducciones: " + amountReproductions + "\nAutor: " + author.toString();
    }

    

    
}
