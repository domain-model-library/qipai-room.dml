import dml.qipairoom.entity.QipaiRoom;
import dml.qipairoom.repository.QipaiRoomRepository;
import dml.qipairoom.repository.RoomNoGeneratorRepository;
import dml.qipairoom.service.RoomService;
import dml.qipairoom.service.repositoryset.RoomServiceRepositorySet;
import dml.qipairoom.service.result.JoinRoomResult;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class QipaiRoomTest {

    @Test
    public void test() {
        // 创建2人房间
        String createRoomPlayerId1 = "1";
        int playersCount = 2;
        QipaiRoom room1 = RoomService.createRoom(roomServiceRepositorySet,
                createRoomPlayerId1, playersCount, new TestQipaiRoom());

        //玩家加入房间
        String joinPlayerId1 = "2";
        JoinRoomResult joinRoomResult1 = RoomService.joinRoom(roomServiceRepositorySet,
                room1.getNo(), joinPlayerId1);
        assertTrue(joinRoomResult1.isSuccess());

        //TODO: 又有人创建房间，但是玩家不能加入，同一时间只能加入一个房间
        //TODO: 已有房间也不能创建房间

        //玩家准备好了，开始游戏，删除房间
        room1 = RoomService.playerReady(roomServiceRepositorySet,
                room1.getNo(), createRoomPlayerId1);
        room1 = RoomService.playerReady(roomServiceRepositorySet,
                room1.getNo(), joinPlayerId1);
        room1 = RoomService.findRoom(roomServiceRepositorySet, room1.getNo());
        assertNull(room1);

    }

    RoomServiceRepositorySet roomServiceRepositorySet = new RoomServiceRepositorySet() {
        @Override
        public QipaiRoomRepository getQipaiRoomRepository() {
            return null;
        }

        @Override
        public RoomNoGeneratorRepository getRoomNoGeneratorRepository() {
            return null;
        }
    };

}
