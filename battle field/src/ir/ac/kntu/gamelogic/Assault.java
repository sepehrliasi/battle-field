package ir.ac.kntu.gamelogic;

public class Assault extends Rifle{

    public Assault(Calibre calibre){
        super(50, 10,calibre,RifleType.ASSAULT);
    }

    @Override
    public String toString(){
        return "AssaultRifle " + getCalibre();
    }
}
