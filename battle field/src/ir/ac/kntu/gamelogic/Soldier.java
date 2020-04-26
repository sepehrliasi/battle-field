package ir.ac.kntu.gamelogic;

public class Soldier{
    private int name;
    private int health;
    private Side side;
    private Rifle rifle;
    private boolean alive;

    public Soldier(int name, Side side, Rifle rifle){
        this.name = name;
        this.health = 100;
        this.side = side;
        this.rifle = rifle;
        this.alive = true;
    }

    @Override
    public String toString(){
        return "[" +
                "S" + name +
                " | " + health +
                " | " + side +
                " | " + rifle +
                ']';
    }

    public int getHealth(){
        return health;
    }

    public Rifle getRifle(){
        return rifle;
    }

    public boolean isAlive(){
        return alive;
    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }

    public void setHealth(int health){
        this.health = health;
    }
}
