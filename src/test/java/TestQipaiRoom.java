import dml.qipairoom.entity.QipaiRoom;

public class TestQipaiRoom implements QipaiRoom {

    private String no;

    @Override
    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public String getNo() {
        return "";
    }

    @Override
    public void setMaxPlayersCount(int maxPlayersCount) {

    }

    @Override
    public void joinPlayer(String playerId) {

    }

    @Override
    public boolean containsPlayer(String playerId) {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
