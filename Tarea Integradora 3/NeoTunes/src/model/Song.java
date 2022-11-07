package model;

public class Song extends Audio implements Sellable{
    private double price;
    private int numSales;
    private String album;
    private Genre genre;

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getNumSales() {
        return numSales;
    }
    public void setNumSales(int numSales) {
        this.numSales = numSales;
    }
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public Song(String name, String url, double duration, UserProducer author, double price, String album, Genre genre) {
        super(name, url, duration, author);
        this.price = price;
        this.numSales = 0;
        this.album = album;
        this.genre = genre;
    }

    
}