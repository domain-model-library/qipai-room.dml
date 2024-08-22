package dml.qipairoom.entity;

public class PlayerRoomJoin {
    private String playerId;
    private String roomNoIn;

    public PlayerRoomJoin() {
    }

    public PlayerRoomJoin(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getRoomNoIn() {
        return roomNoIn;
    }

    public void setRoomNoIn(String roomNoIn) {
        this.roomNoIn = roomNoIn;
    }
}
