package model;

public class Monster {
    private String name;
    private EnemyType type;
    private int negativePoints;
    private int remPoints;
    private int xPos;
    private int yPos;

    public Monster(String name, EnemyType type, int negativePoints, int remPoints){
        this.name = name;
        this.type = type;
        this.negativePoints = negativePoints;
        this.remPoints = remPoints;
    }

    public Monster(Monster monsterToCopy){
        this.name = monsterToCopy.name;
        this.type = monsterToCopy.type;
        this.negativePoints = monsterToCopy.negativePoints;
        this.remPoints = monsterToCopy.remPoints;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnemyType getType() {
        return type;
    }

    public void setType(EnemyType type) {
        this.type = type;
    }

    public int getNegativePoints() {
        return negativePoints;
    }

    public void setNegativePoints(int negativePoints) {
        this.negativePoints = negativePoints;
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
        return " | Nombre del enemigo = " + name 
                + " | tipo = " + type 
                + " | puntos que resta = " + negativePoints 
                + " | puntos de remuneración = " + remPoints;
    }
    
    /* 
     * Description: This method verifies if the attributes of a given object are the same as the monster object attributes
     * @param obj Object: It is the object to compare
     * @return boolean: If the attributes are the same, it returns true. If they are not, it returns false
     */
    @Override
    public boolean equals(Object obj) {
        Monster monsterToCompare = (Monster) obj;
        return (this.name.equals(monsterToCompare.getName())&& this.type.equals(monsterToCompare.getType())&&this.negativePoints==monsterToCompare.getNegativePoints()&&this.remPoints==monsterToCompare.getRemPoints());
    }
    

    

}
