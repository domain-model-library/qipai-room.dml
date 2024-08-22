package dml.qipairoom.service.repositoryset;

import dml.qipairoom.repository.PlayerRoomJoinRepository;
import dml.qipairoom.repository.QipaiRoomRepository;
import dml.qipairoom.repository.RoomNoGeneratorRepository;

public interface RoomServiceRepositorySet {
    QipaiRoomRepository getQipaiRoomRepository();

    RoomNoGeneratorRepository getRoomNoGeneratorRepository();

    PlayerRoomJoinRepository getPlayerRoomJoinRepository();
}
