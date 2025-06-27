package dml.qipairoom.entity;

import java.util.List;

public interface QipaiRoom {
    void setNo(String no);

    String getNo();

    void setMaxPlayersCount(int maxPlayersCount);

    void joinPlayer(Object playerId);

    boolean containsPlayer(Object playerId);

    boolean isFull();

    void playerReady(Object playerId);

    boolean isAllPlayerReady();

    List<Object> getPlayerIds();

    void setOwnerId(Object ownerId);

    Object getOwnerId();
}
