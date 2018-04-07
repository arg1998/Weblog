package ir.mafiaaa.mafia.adapter;


public class Player {

    private String ID;
    private String nickName;
    private String emailAddress;
    private int level;
    private int xp;

    public Player(String ID, String nickName, int level) {
        this.ID = ID;
        this.nickName = nickName;
        this.level = level;
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }

}
