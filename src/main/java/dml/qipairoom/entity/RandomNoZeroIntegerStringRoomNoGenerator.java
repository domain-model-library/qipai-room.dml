package dml.qipairoom.entity;

import java.util.Random;

public class RandomNoZeroIntegerStringRoomNoGenerator implements RoomNoGenerator {
    private int stringLength;
    private Random random;

    public RandomNoZeroIntegerStringRoomNoGenerator(int stringLength) {
        this.stringLength = stringLength;
        this.random = new Random();
    }

    public RandomNoZeroIntegerStringRoomNoGenerator(int stringLength, long seed) {
        this.stringLength = stringLength;
        this.random = new Random(seed);
    }

    @Override
    public String generate() {
        StringBuilder noBuilder = new StringBuilder();
        for (int i = 0; i < stringLength; i++) {
            noBuilder.append(random.nextInt(9) + 1);
        }
        return noBuilder.toString();
    }
}
