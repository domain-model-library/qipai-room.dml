package dml.qipairoom.entity;

import java.util.HashMap;
import java.util.Map;

public abstract class QipaiRoomBase implements QipaiRoom {
    private String no;
    private int maxPlayersCount;
    private Map<String, RoomPlayer> players = new HashMap<>();

    @Override
    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public String getNo() {
        return no;
    }

    @Override
    public void setMaxPlayersCount(int maxPlayersCount) {
        this.maxPlayersCount = maxPlayersCount;
    }

    @Override
    public void joinPlayer(String playerId) {
        RoomPlayer existingPlayer = players.get(playerId);
        if (existingPlayer != null) {
            return;
        }
        RoomPlayer newPlayer = new RoomPlayer(playerId);
        players.put(playerId, newPlayer);
    }

    @Override
    public boolean containsPlayer(String playerId) {
        return players.containsKey(playerId);
    }

    @Override
    public boolean isFull() {
        return players.size() >= maxPlayersCount;
    }

    @Override
    public void playerReady(String playerId) {
        RoomPlayer player = players.get(playerId);
        if (player != null) {
            player.setReady(true);
        }
    }

    @Override
    public boolean isAllPlayerReady() {
        for (RoomPlayer player : players.values()) {
            if (!player.isReady()) {
                return false;
            }
        }
        return true;
    }
}
