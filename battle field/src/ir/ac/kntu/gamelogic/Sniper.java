package ir.ac.kntu.gamelogic;

public class Sniper extends Rifle {

    public Sniper(Calibre calibre){
        super(60, 20,calibre,RifleType.SNIPER);
    }

    @Override
    public String toString(){
        return "SniperRifle " + getCalibre();
    }
}
