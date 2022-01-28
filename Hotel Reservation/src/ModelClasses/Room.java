package ModelClasses;

import java.util.Objects;
/*
https://github.com/zeevolution/hotel-reservation/blob/974084324c562b94c8ddd9d6663be3bcac9074f3/src/model/room/Room.java
 */

public class Room implements IRoom {
    private final String roomNumber;
    private final Double price;
    private final RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString(){
        return "Room Number: " + roomNumber +"\nPrice: $"+ price + "\nRoomType: " + enumeration; }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Room)){
            return false;
        }
        final Room room = (Room) o;
        return Objects.equals(this.roomNumber, room.roomNumber);
    }
    
    @Override
    public int hashCode() {return Objects.hash(roomNumber);}
}



