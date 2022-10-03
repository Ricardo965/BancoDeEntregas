package model;

/**
 * Treasure
 */
public class Treasure {
    private String name;
    private String linkUrl;
    private int remPoints;
    private int xPos;
    private int yPos;

    public Treasure(String name, String linkUrl, int remPoints){
        this.name = name;
        this.linkUrl = linkUrl;
        this.remPoints = remPoints;
    }

    public Treasure(Treasure treasureToCopy){
        this.name = treasureToCopy.getName();
        this.linkUrl = treasureToCopy.getLinkUrl();
        this.remPoints = treasureToCopy.getRemPoints();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getRemPoints() {
        return remPoints;
    }

    public void setRemPoints(int remPoints) {
        this.remPoints = remPoints;
    }

    
    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    @Override
    public String toString() {
        return "Nombre del tesoro: " +  name + " Puntos que otorga: " + remPoints;
    }

    @Override
    public boolean equals(Object obj) {
        Treasure treasureToCompare = (Treasure) obj;
        return (this.name.equals(treasureToCompare.getName())&&this.linkUrl.equals(treasureToCompare.getLinkUrl())&&this.remPoints==treasureToCompare.getRemPoints());
    }
    
    
}