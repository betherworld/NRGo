package go.energy.crypto.cryptoenergy;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private int fireCount, dropCount, leafCount, airCount, electricityCount, specialCount;


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public int getFire(){
        return fireCount;
    }

    public void setFire(int newFireCount){
        this.fireCount = newFireCount;
    }

    public int getDrop(){
        return dropCount;
    }

    public void setDrop(int newDropCount){
        this.dropCount = newDropCount;
    }

    public int getLeaf(){
        return leafCount;
    }

    public void setLeaf(int newLeafCount){
        this.leafCount = newLeafCount;
    }

    public int getAir(){
        return airCount;
    }

    public void setAir(int newAirCount){
        this.airCount = newAirCount;
    }

    public int getElectricity(){
        return electricityCount;
    }

    public void setElectricity(int newElectricityCount){
        this.electricityCount = newElectricityCount;
    }

    public int getSpecial(){
        return specialCount;
    }

    public void setSpecial(int newSpecialCount){
        this.specialCount = newSpecialCount;
    }
}
