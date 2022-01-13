package api;

import ModelClasses.Customer;
import ModelClasses.IRoom;
import ModelClasses.Reservation;
import ServiceClasses.CustomerService;
import ServiceClasses.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResource;

    private final CustomerService customerService = CustomerService.getInstance();
    private final ReservationService reservationService = ReservationService.getInstance();

    private AdminResource(){}

    public static AdminResource getInstance() {
        if(null==adminResource){
            //System.out.println("Hotel Service: null! Instantiating an object of the class.");
            adminResource = new AdminResource();
        }
        //otherwise? we will just return the reference of the class itself (at least that's what it should do).
        return adminResource;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms){
        for( IRoom room : rooms){
            reservationService.addRoom(room);
        }
    }
    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomer();
    }

    public Collection<Reservation> displayAllReservations(){
        reservationService.printAllReservations();
        return null;
    }
}
