package dml.qipairoom.entity;

public class PlayerRoomJoin {
    private Object playerId;
    private String roomNoIn;

    public PlayerRoomJoin() {
    }

    public PlayerRoomJoin(Object playerId) {
        this.playerId = playerId;
    }

    public Object getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Object playerId) {
        this.playerId = playerId;
    }

    public String getRoomNoIn() {
        return roomNoIn;
    }

    public void setRoomNoIn(String roomNoIn) {
        this.roomNoIn = roomNoIn;
    }
}
