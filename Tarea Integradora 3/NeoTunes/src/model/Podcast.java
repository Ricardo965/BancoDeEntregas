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

    public Podcast(Podcast podcastToCopy) {
        super(podcastToCopy);
        this.description = podcastToCopy.description;
        this.category = podcastToCopy.category;
    }
    @Override
    public String bePlayed() {
        this.getAuthor().setNumReproduction(getAuthor().getNumReproduction() + 1);
        this.getAuthor().setTimeReproduced(getAuthor().getTimeReproduced()+ this.getDuration());
        this.setAmountReproductions(this.getAmountReproductions()+1);
        return "Reproduciendo... " + this.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getName().equals(((Podcast)obj).getName()) && this.getUrl().equals(((Podcast)obj).getUrl()) && this.getDuration() == ((Podcast)obj).getDuration() && this.getAuthor().equals(((Podcast)obj).getAuthor()) && this.getDescription().equals(((Podcast)obj).getDescription()) && this.getCategory().equals(((Podcast)obj).getCategory())) {
            return true;
        }
        return false;
    }
}
