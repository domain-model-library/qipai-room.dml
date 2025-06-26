import dml.common.repository.TestCommonRepository;
import dml.common.repository.TestCommonSingletonRepository;
import dml.qipairoom.entity.QipaiRoom;
import dml.qipairoom.entity.RandomNoZeroIntegerStringRoomNoGenerator;
import dml.qipairoom.repository.PlayerRoomJoinRepository;
import dml.qipairoom.repository.QipaiRoomRepository;
import dml.qipairoom.repository.RoomNoGeneratorRepository;
import dml.qipairoom.service.RoomService;
import dml.qipairoom.service.repositoryset.RoomServiceRepositorySet;
import dml.qipairoom.service.result.CreateRoomResult;
import dml.qipairoom.service.result.JoinRoomResult;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QipaiRoomTest {

    @Test
    public void test() {
        // 创建2人房间
        String createRoomPlayerId1 = "1";
        int playersCount = 2;
        CreateRoomResult createRoomResult1 = RoomService.createRoom(roomServiceRepositorySet,
                createRoomPlayerId1, playersCount, new TestQipaiRoom());

        //玩家加入房间
        String joinPlayerId1 = "2";
        JoinRoomResult joinRoomResult1 = RoomService.joinRoom(roomServiceRepositorySet,
                createRoomResult1.getRoomNo(), joinPlayerId1);
        assertTrue(joinRoomResult1.isSuccess());

        //又有人创建房间，但是玩家不能加入，同一时间只能加入一个房间
        String createRoomPlayerId2 = "3";
        CreateRoomResult createRoomResult2 = RoomService.createRoom(roomServiceRepositorySet,
                createRoomPlayerId2, playersCount, new TestQipaiRoom());
        JoinRoomResult joinRoomResult2 = RoomService.joinRoom(roomServiceRepositorySet,
                createRoomResult2.getRoomNo(), joinPlayerId1);
        assertFalse(joinRoomResult2.isSuccess());
        assertTrue(joinRoomResult2.isInAnotherRoom());

        //已有房间也不能创建房间
        CreateRoomResult createRoomResult3 = RoomService.createRoom(roomServiceRepositorySet,
                createRoomPlayerId1, playersCount, new TestQipaiRoom());
        assertFalse(createRoomResult3.isSuccess());
        assertTrue(createRoomResult3.isInAnotherRoom());

        //玩家准备好了，开始游戏
        QipaiRoom room1 = RoomService.playerReady(roomServiceRepositorySet,
                createRoomResult1.getRoomNo(), createRoomPlayerId1);
        room1 = RoomService.playerReady(roomServiceRepositorySet,
                room1.getNo(), joinPlayerId1);

        //游戏结束，解散房间
        RoomService.dismissRoom(roomServiceRepositorySet, room1.getNo());

        //游戏结束后，玩家可以重新创建房间
        CreateRoomResult createRoomResult4 = RoomService.createRoom(roomServiceRepositorySet,
                createRoomPlayerId1, playersCount, new TestQipaiRoom());
        assertTrue(createRoomResult4.isSuccess());

        //玩家2可以加入新房间
        JoinRoomResult joinRoomResult3 = RoomService.joinRoom(roomServiceRepositorySet,
                createRoomResult4.getRoomNo(), joinPlayerId1);
        assertTrue(joinRoomResult3.isSuccess());

        //房主直接解散房间
        RoomService.dismissRoomByOwner(roomServiceRepositorySet, createRoomPlayerId1);

        //玩家2可以创建新房间
        CreateRoomResult createRoomResult5 = RoomService.createRoom(roomServiceRepositorySet,
                joinPlayerId1, playersCount, new TestQipaiRoom());
        assertTrue(createRoomResult5.isSuccess());

    }

    QipaiRoomRepository qipaiRoomRepository = TestCommonRepository.instance(QipaiRoomRepository.class);
    RoomNoGeneratorRepository roomNoGeneratorRepository = TestCommonSingletonRepository.instance(RoomNoGeneratorRepository.class,
            new RandomNoZeroIntegerStringRoomNoGenerator(6));
    PlayerRoomJoinRepository playerRoomJoinRepository = TestCommonRepository.instance(PlayerRoomJoinRepository.class);

    RoomServiceRepositorySet roomServiceRepositorySet = new RoomServiceRepositorySet() {
        @Override
        public QipaiRoomRepository getQipaiRoomRepository() {
            return qipaiRoomRepository;
        }

        @Override
        public RoomNoGeneratorRepository getRoomNoGeneratorRepository() {
            return roomNoGeneratorRepository;
        }

        @Override
        public PlayerRoomJoinRepository getPlayerRoomJoinRepository() {
            return playerRoomJoinRepository;
        }
    };

}
