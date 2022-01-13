package ModelClasses;

public class FreeRoom extends Room{
        public FreeRoom(String roomNumber, Double price, RoomType enumeration){
        super(roomNumber,0.00,enumeration);
        }

        @Override
        public String toString(){
            return "The Details of the Free Room: \n" + super.toString();
        }
}
