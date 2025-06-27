package dml.qipairoom.entity;

public class RoomPlayer {
    private Object id;
    private boolean ready;

    public RoomPlayer() {
    }

    public RoomPlayer(Object id) {
        this.id = id;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
