package go.energy.crypto.cryptoenergy;

public class Element {
    private String name;
    private int curCoins;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getCoins(){
        return curCoins;
    }

    public void addPoints(int incrementBy){
        this.curCoins += incrementBy;
    }

}
