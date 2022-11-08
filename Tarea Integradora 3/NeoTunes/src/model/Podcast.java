package model;

public class Podcast extends Audio {
    private String description;
    private Category category;
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Podcast(String name, String url, double duration, UserProducer author, String description, Category category) {
        super(name, url, duration, author);
        this.description = description;
        this.category = category;
    }
    @Override
    public String bePlayed() {
        this.setAmountReproductions(this.getAmountReproductions()+1);
        return "Reproduciendo... " + this.getName();
    }
}
