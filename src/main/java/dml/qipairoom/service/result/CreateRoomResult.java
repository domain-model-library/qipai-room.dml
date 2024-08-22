package dml.qipairoom.service.result;

public class CreateRoomResult {
    private boolean success;
    private boolean inAnotherRoom;
    private String roomNo;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isInAnotherRoom() {
        return inAnotherRoom;
    }

    public void setInAnotherRoom(boolean inAnotherRoom) {
        this.inAnotherRoom = inAnotherRoom;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
}
