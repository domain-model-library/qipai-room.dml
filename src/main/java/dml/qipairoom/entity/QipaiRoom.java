package dml.qipairoom.entity;

public interface QipaiRoom {
    void setNo(String no);

    String getNo();

    void setMaxPlayersCount(int maxPlayersCount);

    void joinPlayer(String playerId);

    boolean containsPlayer(String playerId);

    boolean isFull();

    void playerReady(String playerId);

    boolean isAllPlayerReady();
}
