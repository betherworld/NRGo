package go.energy.crypto.cryptoenergy;

public class Quest {
    public int icon;
    public String title;
    public String content;
    public int pointsOfQuest;
    public int typeId;

    public Quest(){
        super();
    }

    public Quest(int icon, String title, String content, int pointsOfQuest, int typeId) {
        super();
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.pointsOfQuest = pointsOfQuest;
        this.typeId = typeId;
    }
}
