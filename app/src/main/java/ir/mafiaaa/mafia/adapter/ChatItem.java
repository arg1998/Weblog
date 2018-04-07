package ir.mafiaaa.mafia.adapter;


public class ChatItem {

    private Player senderPlayer;
    private String Message;

    public ChatItem(Player senderPlayer, String message) {
        this.senderPlayer = senderPlayer;
        Message = message;
    }

    public Player getSenderPlayer() {
        return senderPlayer;
    }

    public void setSenderPlayer(Player senderPlayer) {
        this.senderPlayer = senderPlayer;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

}
