package dml.qipairoom.entity;

import java.util.List;

public interface QipaiRoom {
    void setNo(String no);

    String getNo();

    void setMaxPlayersCount(int maxPlayersCount);

    void joinPlayer(String playerId);

    boolean containsPlayer(String playerId);

    boolean isFull();

    void playerReady(String playerId);

    boolean isAllPlayerReady();

    List<String> getPlayerIds();

    void setOwner(String ownerId);

    String getOwner();
}
