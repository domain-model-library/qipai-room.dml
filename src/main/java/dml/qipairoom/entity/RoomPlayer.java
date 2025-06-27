package dml.qipairoom.entity;

public class RoomPlayer {
    private String id;
    private boolean ready;

    public RoomPlayer() {
    }

    public RoomPlayer(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
