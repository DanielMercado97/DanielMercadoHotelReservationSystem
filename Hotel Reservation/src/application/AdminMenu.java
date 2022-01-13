package application;

import ModelClasses.Customer;
import ModelClasses.IRoom;
import ModelClasses.Room;
import ModelClasses.RoomType;
import ServiceClasses.CustomerService;
import ServiceClasses.ReservationService;
import api.AdminResource;
import api.HotelResource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static CustomerService customerService = CustomerService.getInstance();
    private static AdminResource adminResource = AdminResource.getInstance();

    public static ReservationService reservationService = ReservationService.getInstance();
    private static HotelResource hotelResource = HotelResource.getInstance();

    public static void adminMenu(){
        boolean running = true;
        while(running){
            adminMenuDisplay();
            Scanner scanner = new Scanner (System.in);
            int input = Integer.parseInt(scanner.next());
            switch(input) {
                case 1:
                    System.out.println("You chose to see all Customers of the Hotel!");
                    seeAllCustomers();
                    break;
                case 2:
                    System.out.println("You chose to see all Registered Rooms in the Hotel");
                    seeAllRooms();
                    break;
                case 3:
                    System.out.println("You chose to see all the Booked Reservations at the moment");
                    seeAllReservations();
                    break;
                case 4:
                    System.out.println("You chose to add a Room to the Hotel's Registry");
                    addARoom(scanner);
                    break;
                case 5:
                    System.out.println("You Wish to go Back to the Main Menu. HERE WE GO!");
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.menuSU();
                    running = false;
                    break;
            }
        }
    }

    public static void adminMenuDisplay(){
        System.out.println("------Welcome to the Hotel Mercado's Admin System! Please Select What You Would Like to do First!------");
        System.out.println("1. See all Customers of the Hotel");
        System.out.println("2. See all Registered Rooms in the Hotel");
        System.out.println("3. See all Booked Reservations");
        System.out.println("4. Add a Room");
        System.out.println(("5. Back to the Main Menu!"));
        System.out.println("-------------------------------------------------------------------------------------------------------");
    }


    private static void seeAllCustomers(){
        Collection<Customer> allCustomers= customerService.getAllCustomer();
        for(Customer customer: allCustomers){
            System.out.println(customer);
        }

    }

    private static void seeAllRooms(){
        Collection<IRoom> rooms = adminResource.getAllRooms();
        for(IRoom allRooms : rooms){
            System.out.println(allRooms);
        }

    }

    private static void seeAllReservations(){
        adminResource.displayAllReservations();

    }

    private static void addARoom(Scanner scanner){
        System.out.println("Enter a Unique Room Number: " );
        String rN = scanner.next();
        System.out.println("Enter Room Price: ");
        Double price = scanner.nextDouble();
        System.out.println("What is the Room Type? A Single (Press 1) or a Double (Press 2)?");
        String rTS = scanner.next();
        RoomType roomType = null;
        try{
            roomType = RoomType.getByValue(Integer.parseInt(rTS));
        }catch(Exception ex){
            if(rTS == null){
                System.out.println("Invalid Input. Try Again!");
            }

        }

        IRoom room = new Room(rN,price,roomType);
        List<IRoom> rooms = new ArrayList<>();
        rooms.add(room);

        adminResource.addRoom(rooms);

        System.out.println("Congratulations! You've added a room to the database!" +
                "\n Would you like to add another room? (Yes or No?)");
        String s = scanner.next();

        if(s.equalsIgnoreCase("yes")){
            addARoom(scanner);
        }
        else{
            adminMenuDisplay();
        }



    }
}
