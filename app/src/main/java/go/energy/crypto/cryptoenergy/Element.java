package go.energy.crypto.cryptoenergy;

import org.web3j.tuples.generated.Tuple2;

public class Element {
    private int dnaWater;
    private int dnaElectricity;
    private int dnaFire;
    private int dnaLeaf;
    private int dnaAir;
    private int dnaSpecial;

    public Element(int dnaWater, int dnaElectricity, int dnaFire, int dnaLeaf, int dnaAir, int dnaSpecial) {
        this.dnaWater = dnaWater;
        this.dnaElectricity = dnaElectricity;
        this.dnaFire = dnaFire;
        this.dnaLeaf = dnaLeaf;
        this.dnaAir = dnaAir;
        this.dnaSpecial = dnaSpecial;
    }

    public int getDnaWater() {
        return dnaWater;
    }

    public int getDnaElectricity() {
        return dnaElectricity;
    }

    public int getDnaFire() {
        return dnaFire;
    }

    public int getDnaLeaf() {
        return dnaLeaf;
    }

    public int getDnaAir() {
        return dnaAir;
    }

    public int getDnaSpecial() {
        return dnaSpecial;
    }

    //    public String getName(){
//        return name;
//    }
//
//    public void setName(String name){
//        this.name = name;
//    }
//
//    public int getCoins(){
//        return curCoins;
//    }
//
//    public void addPoints(int incrementBy){
//        this.curCoins += incrementBy;
//    }
    private int dnaSum(){
        return (dnaWater+dnaElectricity+dnaFire+dnaLeaf+dnaAir+dnaSpecial);
    }

    @Override
    public String toString() {
        return "Element{" +
                "dnaWater=" + dnaWater +
                ", dnaElectricity=" + dnaElectricity +
                '}';
    }
    public  int getLevel(){
        return  dnaSum()/10 + 1;
    }
    public int percentageToNextLevel(){
        return (dnaSum()%10)*10;
    }
    public int getWaterProgress(){
        return (dnaWater % 10)*10;
    }
    public int getElectricityProgress(){
        return (dnaElectricity % 10)*10;
    }
    public int getFireProgress(){
        return (dnaFire % 10)*10;
    }
    public int getLeafProgress(){
        return (dnaLeaf % 10)*10;
    }
    public int getAirProgress(){
        return (dnaAir % 10)*10;
    }
    public int getSpecialProgress(){
        return (dnaSpecial % 10)*10;
    }
    public void update(ElementEnum elementEnum, int dnaUpdate){
        switch (elementEnum){
            case WATER:
                dnaWater+=dnaUpdate;
                break;
            case ELECTRICITY:
                dnaElectricity+=dnaUpdate;
                break;
            case FIRE:
                dnaFire+=dnaUpdate;
                break;
            case LEAF:
                dnaLeaf+=dnaUpdate;
                break;
            case AIR:
                dnaAir+=dnaUpdate;
                break;
            case SPECIAL:
                dnaSpecial+=dnaUpdate;
                break;
        }
    }
}
