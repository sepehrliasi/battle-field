package ir.ac.kntu.gamelogic;

public class Controller {
    private int numberOfSoldiers;

    public Controller(int numberOfSoldiers){
        this.numberOfSoldiers = numberOfSoldiers;
    }

    public String game(){
        Soldier[] soldiers = new Soldier[2*numberOfSoldiers];
        for(int i = 0;i < 2*numberOfSoldiers;i++){
            int random1 = RandomHelper.nextInt(2);
            Calibre calibre1 = Calibre.MM7;
            if(random1 % 2 == 0){
                calibre1 = Calibre.MM5;
            }
            int random = RandomHelper.nextInt(3);
            Rifle rifle;
            if(random == 1){
                rifle = new Sniper(calibre1);
            }else{
                rifle = new Assault(calibre1);
            }
            if(i < numberOfSoldiers) {
                soldiers[i] = new Soldier(i, Side.RED,rifle);
            }else{
                soldiers[i] = new Soldier(i, Side.BLUE,rifle);
            }
        }
        int round = 0;
        show(soldiers,round);
        round++;
        while (!isEnd(soldiers)){
            int r1 = RandomHelper.nextInt(numberOfSoldiers);
            while (!soldiers[r1].isAlive()){
                r1 = RandomHelper.nextInt(numberOfSoldiers);
            }
            int r2 = RandomHelper.nextInt(numberOfSoldiers) + numberOfSoldiers;
            while (!soldiers[r2].isAlive()){
                r2 = RandomHelper.nextInt(numberOfSoldiers) + numberOfSoldiers;
            }
            battle(r1,r2,soldiers);
            show(soldiers,round);
            round++;
        }
        if(redWon(soldiers)) {
            return "reds won";
        }
        return "blues won";
    }

    public boolean redWon(Soldier[] soldiers){
        for(int i = 0;i < numberOfSoldiers;i++){
            if(soldiers[i].isAlive()){
                return true;
            }
        }
        return false;
    }

    public void battle(int p1, int p2, Soldier[] soldiers){
        Soldier red = soldiers[p1];
        Soldier blue = soldiers[p2];
        int random = RandomHelper.nextInt(2);
        if(random % 2 == 0){
            int accuracy = red.getRifle().getAccuracy();
            int damage = red.getRifle().getDamage();
            Calibre calibre = red.getRifle().getCalibre();
            int healthBlue = blue.getHealth();
            if(calibre == Calibre.MM7){
                accuracy -= 10;
                damage += 10;
            }else{
                accuracy += 15;
            }
            int r = 0;
            if(red.getRifle().getRifleType() == RifleType.SNIPER){
                int zoom = RandomHelper.nextInt(2);
                if(zoom == 0) {
                    r = 5 + RandomHelper.nextInt(11);
                }
            }
            accuracy += r;
            int shot = RandomHelper.nextInt(100);
            if(shot < accuracy){
                healthBlue -= damage;
                blue.setHealth(healthBlue);
                if(healthBlue <= 0){
                    blue.setHealth(0);
                    blue.setAlive(false);
                }
            }//now it's opponent's turn
            if(blue.isAlive()){
                int accuracy1 = blue.getRifle().getAccuracy();
                int damage1 = blue.getRifle().getDamage();
                Calibre calibre1 = blue.getRifle().getCalibre();
                int healthRed = red.getHealth();
                if(calibre1 == Calibre.MM7){
                    accuracy1 -= 10;
                    damage1 += 10;
                }else{
                    accuracy1 += 15;
                }
                r = 0;
                if(blue.getRifle().getRifleType() == RifleType.SNIPER){
                    int zoom = RandomHelper.nextInt(2);
                    if(zoom == 0) {
                        r = 5 + RandomHelper.nextInt(11);
                    }
                }
                accuracy1 += r;
                shot = RandomHelper.nextInt(100);
                if(shot < accuracy1){
                    healthRed -= damage1;
                    red.setHealth(healthRed);
                    if(healthRed <= 0){
                        red.setHealth(0);
                        red.setAlive(false);
                    }
                }
            }
        }else{
            int accuracy = blue.getRifle().getAccuracy();
            int damage = blue.getRifle().getDamage();
            Calibre calibre = blue.getRifle().getCalibre();
            int healthRed = red.getHealth();
            if(calibre == Calibre.MM7){
                accuracy -= 10;
                damage += 10;
            }else{
                accuracy += 15;
            }
            int r = 0;
            if(blue.getRifle().getRifleType() == RifleType.SNIPER){
                int zoom = RandomHelper.nextInt(2);
                if(zoom == 0) {
                    r = 5 + RandomHelper.nextInt(11);
                }
            }
            accuracy += r;
            int shot = RandomHelper.nextInt(100);
            if(shot < accuracy){
                healthRed -= damage;
                red.setHealth(healthRed);
                if(healthRed <= 0){
                    red.setHealth(0);
                    red.setAlive(false);
                }
            }//now it's opponent's turn
            if(red.isAlive()){
                int accuracy1 = red.getRifle().getAccuracy();
                int damage1 = red.getRifle().getDamage();
                Calibre calibre1 = red.getRifle().getCalibre();
                int healthBlue = blue.getHealth();
                if(calibre1 == Calibre.MM7){
                    accuracy1 -= 10;
                    damage1 += 10;
                }else{
                    accuracy1 += 15;
                }
                r = 0;
                if(red.getRifle().getRifleType() == RifleType.SNIPER){
                    int zoom = RandomHelper.nextInt(2);
                    if(zoom == 0) {
                        r = 5 + RandomHelper.nextInt(11);
                    }
                }
                accuracy1 += r;
                shot = RandomHelper.nextInt(100);
                if(shot < accuracy1){
                    healthBlue -= damage1;
                    blue.setHealth(healthBlue);
                    if(healthBlue <= 0){
                        blue.setHealth(0);
                        blue.setAlive(false);
                    }
                }
            }
        }
    }

    public boolean isEnd(Soldier[] soldiers){
        int t = 0;
        for(int i = 0;i < numberOfSoldiers;i++){
            if (soldiers[i].isAlive()) {
                t = 1;
                break;
            }
        }
        if(t == 0){
            return true;
        }
        for(int i = numberOfSoldiers;i < 2*numberOfSoldiers;i++){
            if (soldiers[i].isAlive()) {
                t = 2;
                break;
            }
        }
        return t == 1;
    }

    public void show(Soldier[] soldiers,int round){
        System.out.println("\n*********" + " ROUND" + round + " *********");
        System.out.println("RED team");
        int aliveRed = 0;
        for(int i = 0;i < numberOfSoldiers;i++){
            System.out.print(soldiers[i].toString());
            if(soldiers[i].isAlive()){
                aliveRed++;
            }
        }
        System.out.println("\nBLUE team");
        int aliveBlue = 0;
        for(int i = numberOfSoldiers;i < 2*numberOfSoldiers;i++){
            System.out.print(soldiers[i].toString());
            if(soldiers[i].isAlive()){
                aliveBlue++;
            }
        }
        System.out.println("\nred alives: " + aliveRed);
        System.out.println("blue alives: " + aliveBlue);
    }
}
