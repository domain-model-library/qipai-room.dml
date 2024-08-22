package dml.qipairoom.service;

import dml.qipairoom.entity.QipaiRoom;
import dml.qipairoom.entity.RoomNoGenerator;
import dml.qipairoom.repository.QipaiRoomRepository;
import dml.qipairoom.repository.RoomNoGeneratorRepository;
import dml.qipairoom.service.repositoryset.RoomServiceRepositorySet;
import dml.qipairoom.service.result.JoinRoomResult;

public class RoomService {
    public static QipaiRoom createRoom(RoomServiceRepositorySet roomServiceRepositorySet,
                                       String createRoomPlayerId, int playersCount, QipaiRoom newQipaiRoom) {
        QipaiRoomRepository<QipaiRoom> qipaiRoomRepository = roomServiceRepositorySet.getQipaiRoomRepository();
        RoomNoGeneratorRepository<RoomNoGenerator> roomNoGeneratorRepository = roomServiceRepositorySet.getRoomNoGeneratorRepository();

        RoomNoGenerator roomNoGenerator = roomNoGeneratorRepository.get();
        String roomNo = roomNoGenerator.generate();
        newQipaiRoom.setNo(roomNo);
        newQipaiRoom.setMaxPlayersCount(playersCount);
        newQipaiRoom.joinPlayer(createRoomPlayerId);
        qipaiRoomRepository.put(newQipaiRoom);
        return newQipaiRoom;
    }

    public static JoinRoomResult joinRoom(RoomServiceRepositorySet roomServiceRepositorySet,
                                          String roomNo, String joinPlayerId) {
        QipaiRoomRepository<QipaiRoom> qipaiRoomRepository = roomServiceRepositorySet.getQipaiRoomRepository();

        JoinRoomResult result = new JoinRoomResult();
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
        result.setSuccess(true);
        return result;
    }

    public static QipaiRoom playerReady(RoomServiceRepositorySet roomServiceRepositorySet,
                                        String roomNo, String playerId) {
        QipaiRoomRepository<QipaiRoom> qipaiRoomRepository = roomServiceRepositorySet.getQipaiRoomRepository();

        QipaiRoom qipaiRoom = qipaiRoomRepository.take(roomNo);
        qipaiRoom.playerReady(playerId);
        return qipaiRoom;
    }

    public static QipaiRoom findRoom(RoomServiceRepositorySet roomServiceRepositorySet,
                                     String roomNo) {
        QipaiRoomRepository<QipaiRoom> qipaiRoomRepository = roomServiceRepositorySet.getQipaiRoomRepository();

        return qipaiRoomRepository.find(roomNo);
    }
}
