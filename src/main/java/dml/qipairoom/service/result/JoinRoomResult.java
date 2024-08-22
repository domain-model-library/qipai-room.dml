package dml.qipairoom.service.result;

public class JoinRoomResult {
    private boolean success;
    private boolean full;
    private boolean alreadyIn;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

    public void setAlreadyIn(boolean alreadyIn) {
        this.alreadyIn = alreadyIn;
    }
}
