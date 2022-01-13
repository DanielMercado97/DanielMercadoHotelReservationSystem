package ServiceClasses;

import ModelClasses.Customer;
import ModelClasses.IRoom;
import ModelClasses.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class ReservationService {
    private static ReservationService reservationService;
    Collection<IRoom> rooms = new HashSet<>();
    //Collection<Reservation> roomList = new HashSet<>();
    Collection<Reservation> reservations = new HashSet<>();


    private ReservationService(){}

    public static ReservationService getInstance(){
        if(null==reservationService){
            //System.out.println("Reservation Service: null! Instantiating an object of the class.");
            reservationService = new ReservationService();
        }
        //otherwise? we will just return the reference of the class itself (at least that's what it should do).
        return reservationService;
    }

    public void addRoom(IRoom room){
        rooms.add(room);
    }

    public IRoom getARoom(String roomID){
        for (IRoom room: rooms) {
            //if the calls to the getEmail getter within the Customer class equals the email set within the setOfCustomers set, return it
            if(roomID.equals(room.getRoomNumber())){
                return room;
            }
        }
        return null;
    }


    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate,Date checkOutDate){
        Collection<IRoom> avRooms = findRooms(checkInDate,checkOutDate);
        if(avRooms.contains(room)){
            Reservation makeReservation = new Reservation(customer,room,checkInDate,checkOutDate);
            if(reservations.add(makeReservation)){
                System.out.println("Congratulations on making your reservation!");
                return makeReservation;
            }
            else{
                System.out.println("This reservation couldn't be made because it's not available");
                return null;

            }
        }

        return null;
    }
    public Collection<IRoom>findRooms(Date checkInDate, Date checkOutDate){
        //creating a new for rooms that haven't been reserved already.
        Collection<IRoom> roomsAvailable = new HashSet<>();
        //just to see if the room search set even works
        System.out.println(rooms);

        if(reservations.size() == 0){
            roomsAvailable = rooms;
            return roomsAvailable;
        }
        else {
            for(Reservation rRoom : reservations) {
                for (IRoom rm : rooms) {
                    if ((rm.getRoomNumber().equals(rRoom.getRoom().getRoomNumber()))
                            && ((checkInDate.before(rRoom.getCheckInDate())
                            && checkOutDate.before(rRoom.getCheckInDate()))
                            || (checkInDate.after(rRoom.getCheckOutDate())
                            && checkOutDate.after(rRoom.getCheckOutDate())))
                            || (!rRoom.getRoom().getRoomNumber().contains(rm.getRoomNumber()))) {
                        roomsAvailable.add(rm);
                    } else if (rm.getRoomNumber().equals(rRoom.getRoom().getRoomNumber())) {
                        roomsAvailable.remove(rm);
                    }
                }
            }
        }
        return roomsAvailable;


    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
        Collection<Reservation> customerReservations = new HashSet<>();
        for(Reservation getReservation: reservations){
            if(getReservation.getCustomer().equals(customer)){
                customerReservations.add(getReservation);
            }
        }
        return customerReservations;
    }

    public Collection<IRoom> getAllRooms(){
        return rooms;
    }

    public void printAllReservations(){
        for(Reservation allReservations: reservations){
            System.out.println(allReservations);
        }

    }




}
