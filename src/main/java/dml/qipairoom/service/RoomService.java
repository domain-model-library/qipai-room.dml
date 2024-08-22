package dml.qipairoom.service;

import dml.qipairoom.entity.PlayerRoomJoin;
import dml.qipairoom.entity.QipaiRoom;
import dml.qipairoom.entity.RoomNoGenerator;
import dml.qipairoom.repository.PlayerRoomJoinRepository;
import dml.qipairoom.repository.QipaiRoomRepository;
import dml.qipairoom.repository.RoomNoGeneratorRepository;
import dml.qipairoom.service.repositoryset.RoomServiceRepositorySet;
import dml.qipairoom.service.result.CreateRoomResult;
import dml.qipairoom.service.result.JoinRoomResult;

public class RoomService {
    public static CreateRoomResult createRoom(RoomServiceRepositorySet roomServiceRepositorySet,
                                              String createRoomPlayerId, int playersCount, QipaiRoom newQipaiRoom) {
        QipaiRoomRepository<QipaiRoom> qipaiRoomRepository = roomServiceRepositorySet.getQipaiRoomRepository();
        RoomNoGeneratorRepository<RoomNoGenerator> roomNoGeneratorRepository = roomServiceRepositorySet.getRoomNoGeneratorRepository();
        PlayerRoomJoinRepository playerRoomJoinRepository = roomServiceRepositorySet.getPlayerRoomJoinRepository();

        CreateRoomResult result = new CreateRoomResult();
        PlayerRoomJoin playerRoomJoin = playerRoomJoinRepository.takeOrPutIfAbsent(createRoomPlayerId,
                new PlayerRoomJoin(createRoomPlayerId));
        if (playerRoomJoin.getRoomNoIn() != null) {
            result.setInAnotherRoom(true);
            result.setSuccess(false);
            return result;
        }
        RoomNoGenerator roomNoGenerator = roomNoGeneratorRepository.get();
        String roomNo = roomNoGenerator.generate();
        newQipaiRoom.setNo(roomNo);
        newQipaiRoom.setMaxPlayersCount(playersCount);
        newQipaiRoom.joinPlayer(createRoomPlayerId);
        qipaiRoomRepository.put(newQipaiRoom);
        playerRoomJoin.setRoomNoIn(newQipaiRoom.getNo());
        result.setRoomNo(roomNo);
        result.setSuccess(true);
        return result;
    }

    public static JoinRoomResult joinRoom(RoomServiceRepositorySet roomServiceRepositorySet,
                                          String roomNo, String joinPlayerId) {
        QipaiRoomRepository<QipaiRoom> qipaiRoomRepository = roomServiceRepositorySet.getQipaiRoomRepository();
        PlayerRoomJoinRepository playerRoomJoinRepository = roomServiceRepositorySet.getPlayerRoomJoinRepository();

        JoinRoomResult result = new JoinRoomResult();
        PlayerRoomJoin playerRoomJoin = playerRoomJoinRepository.takeOrPutIfAbsent(joinPlayerId,
                new PlayerRoomJoin(joinPlayerId));
        if (playerRoomJoin.getRoomNoIn() != null) {
            result.setInAnotherRoom(true);
            result.setSuccess(false);
            return result;
        }
        QipaiRoom qipaiRoom = qipaiRoomRepository.take(roomNo);
        if (qipaiRoom.containsPlayer(joinPlayerId)) {
            result.setSuccess(false);
            result.setAlreadyIn(true);
            return result;
        }
        if (qipaiRoom.isFull()) {
            result.setSuccess(false);
            result.setFull(true);
            return result;
        }
        qipaiRoom.joinPlayer(joinPlayerId);
        playerRoomJoin.setRoomNoIn(qipaiRoom.getNo());
        result.setSuccess(true);
        return result;
    }

    public static QipaiRoom playerReady(RoomServiceRepositorySet roomServiceRepositorySet,
                                        String roomNo, String playerId) {
        QipaiRoomRepository<QipaiRoom> qipaiRoomRepository = roomServiceRepositorySet.getQipaiRoomRepository();

        QipaiRoom qipaiRoom = qipaiRoomRepository.take(roomNo);
        qipaiRoom.playerReady(playerId);
        if (qipaiRoom.isAllPlayerReady()) {
            qipaiRoomRepository.remove(roomNo);
            return qipaiRoom;
        }
        return qipaiRoom;
    }

    public static QipaiRoom findRoom(RoomServiceRepositorySet roomServiceRepositorySet,
                                     String roomNo) {
        QipaiRoomRepository<QipaiRoom> qipaiRoomRepository = roomServiceRepositorySet.getQipaiRoomRepository();

        return qipaiRoomRepository.find(roomNo);
    }
}
