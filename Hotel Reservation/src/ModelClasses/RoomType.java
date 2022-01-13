package ModelClasses;

/*
https://stackoverflow.com/questions/58916426/how-do-i-use-user-input-to-select-data-from-a-2d-array-in-java/58916815#58916815
 */

public enum RoomType {
    SINGLE(1),DOUBLE(2);

    private final int ival;


    RoomType(int i) {
        this.ival = i;
    }

    public int getival(){
        return ival;
    }

    public static RoomType getByValue(int val) {
        for(RoomType e : values()) {
            if(e.ival == val)
                return e;
        }
        return null;
    }

}
