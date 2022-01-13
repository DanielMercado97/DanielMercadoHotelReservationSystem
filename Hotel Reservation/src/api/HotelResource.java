package api;

import ModelClasses.Customer;
import ModelClasses.IRoom;
import ModelClasses.Reservation;
import ServiceClasses.CustomerService;
import ServiceClasses.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResource;

    private final CustomerService customerService = CustomerService.getInstance();
    private final ReservationService reservationService = ReservationService.getInstance();

    private HotelResource(){}

    public static HotelResource getInstance() {
        if(null==hotelResource){
            //System.out.println("Hotel Service: null! Instantiating an object of the class.");
            hotelResource = new HotelResource();
        }
        //otherwise? we will just return the reference of the class itself (at least that's what it should do).
        return hotelResource;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email,String firstName,String lastName){
        customerService.addCustomer(email,firstName,lastName);
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        return reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation>getCustomerReservation(String customerEmail){
        Customer customer = CustomerService.getInstance().getCustomer(customerEmail);
        return reservationService.getCustomerReservation(customer);
    }

    public Collection<IRoom>findARoom(Date checkInDate,Date checkOutDate){

        return reservationService.findRooms(checkInDate,checkOutDate);
    }

}
