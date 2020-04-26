package ir.ac.kntu.gamelogic;

public class Rifle {
    private int accuracy;
    private int damage;
    private Calibre calibre;
    private RifleType rifleType;

    public Rifle(int accuracy, int damage,Calibre calibre, RifleType rifleType){
        this.accuracy = accuracy;
        this.damage = damage;
        this.calibre = calibre;
        this.rifleType = rifleType;
    }

    public int getAccuracy(){
        return accuracy;
    }

    public int getDamage(){
        return damage;
    }

    public Calibre getCalibre(){
        return calibre;
    }

    public RifleType getRifleType(){
        return rifleType;
    }
}
