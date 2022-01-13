package application;

import ModelClasses.Customer;
import ModelClasses.IRoom;
import ModelClasses.Reservation;
import ServiceClasses.CustomerService;
import ServiceClasses.ReservationService;
import api.HotelResource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/*
* http://tutorials.jenkov.com/java-date-time/java-util-calendar.html
* */

public class MainMenu {

    public static CustomerService customerService = CustomerService.getInstance();
    public static ReservationService reservationService = ReservationService.getInstance();
    public static HotelResource hotelResource = HotelResource.getInstance();

    public static final String dateformat = "MM/dd/yyyy";
    public static final SimpleDateFormat formatter = new SimpleDateFormat(dateformat);

    public static void menuSU(){
        boolean running = true;
        while(running){
            Scanner scanner = new Scanner(System.in);
            displayMenu();
            int menuChoice = Integer.parseInt(scanner.next());
            switch (menuChoice){
                case 1:
                    System.out.println("Nice, let's get you set up with a room!\n");
                    findAndReserveRooms(scanner);
                    break;
                case 2:
                    System.out.println("You want to see your reservation? Sure thing!\n");
                    seeMyReservation(scanner);
                    break;
                case 3:
                    System.out.println("Ohhhh, I see you're new here! Let's get you started with a new account!\n");
                    createAccount();
                    break;
                case 4:
                    System.out.println("Taking you to the Administrative Section, Boss.\n");
                    AdminMenu.adminMenu();

                case 5:
                    System.out.println("Exiting Program Now! Thanks for Stopping By!");
                    running = false;
                    break;
            }

        }
    }


    public static void displayMenu() {
        System.out.println("------Welcome to the Hotel Mercado's Reservation System! Please Select What You Would Like to do First!------");
        System.out.println("1. Find and Reserve a Room");
        System.out.println("2. Check your Reservation");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin Access");
        System.out.println("5. Exit!");
        System.out.println("-------------------------------------------------------------------------------------------------------------");
    }

    public static void findAndReserveRooms(Scanner scanner) {
        Date uCID = getCheckInDate(scanner);
        Date uCOD = getCheckOutDate(scanner, uCID);
        //Customer customer = checkValidCustAcc(scanner);
        Collection<IRoom> avRooms = findAvailableRooms(uCID, uCOD);
        //if a search through the findRooms function in reservation service results in available rooms for the specified date
        if (avRooms != null) {
            System.out.println("Great, we found an assortment of rooms that accommodate your specified dates!" +
                    "\nBefore we proceed, do you already have an account with us? Yes or No? ");
            String answer = scanner.next();
            if(answer.equalsIgnoreCase("yes")){
                System.out.println("Enter your email here: ");
                String e = scanner.next();
                if(hotelResource.getCustomer(e)==null){
                    System.out.println("Aww man, we couldn't retrieve your email unfortunately :(");
                    findAndReserveRooms(scanner);
                }
                else{
                    hotelResource.getCustomer(e);
                    System.out.println("Nice, we've confirmed you're already a member!" +
                            "\nPick a Room, any Room!");
                    String rN = scanner.next();
                    IRoom room = hotelResource.getRoom(rN);
                    hotelResource.bookARoom(e,room,uCID,uCOD);
                }

            }
            else if (answer.equalsIgnoreCase("no")){
                System.out.println("Let's set you up with a new account. Would you like to set one up? (Yes or No) ");
                String response = scanner.next();
                if(response.equalsIgnoreCase("yes")){
                    createAccount();
                }
            }

        }
        else {
            System.out.println("We will search for any sort of alternative dates with available rooms now!");
            Date aCID = alternativeDates(uCID, -7);
            Date aCOD = alternativeDates(uCOD, 7);
            Collection<IRoom> fTPANR = findAvailableRooms(aCID, aCOD);
            //if there are new rooms for the alternative dates, provided, we're going to find this person a room.
            if (fTPANR != null) {

                System.out.println("Before we proceed, do you already have an account with us? Yes or No? ");
                String answer = scanner.next();
                if(answer.equalsIgnoreCase("yes")){
                    System.out.println("Enter your email here: ");
                    String e = scanner.next();
                    if(hotelResource.getCustomer(e)==null){
                        System.out.println("Aww man, we couldn't retrieve your email unfortunately :(");
                    }
                    else{
                        hotelResource.getCustomer(e);
                        System.out.println("Pick a Room, any Room!");
                        String aRN = scanner.next();
                        IRoom room = hotelResource.getRoom(aRN);
                        hotelResource.bookARoom(e,room,uCID,uCOD);
                    }

                }
                else if (answer.equalsIgnoreCase("no")){
                    System.out.println("Let's set you up with a new account. Would you like to set one up? (Yes or No) ");
                    String response = scanner.next();
                    if(response.equalsIgnoreCase("yes")){
                        createAccount();
                    }
                }
            }
            else{
                System.out.println("Unfortunately, there are no available rooms, even considering alternative dates");
            }
        }
    }



    /*This function will make it easier to access a user's profile to reserve a room. This function checks to see if the user has a valid account with the hotel
    public static Customer checkValidCustAcc(Scanner scanner){
        System.out.println("Just want to double check with you, do you already have an account with us? Yes or No: ");
        String answer = scanner.next();
        if(answer.equalsIgnoreCase("yes")){
            System.out.println("Enter your email here: ");
            String e = scanner.next();
            if(hotelResource.getCustomer(e)==null){
                System.out.println("Aww man, we couldn't retrieve your email unfortunately :(");
            }
            else{
                return hotelResource.getCustomer(e);
            }

        }
        else if (answer.equalsIgnoreCase("no")){
            System.out.println("Let's set you up with a new account. Would you like to set one up? (Yes or No) ");
            String response = scanner.next();
            if(response.equalsIgnoreCase("yes")){
                return createAccount();
            }
        }
        return null;
    }
*/


    //This function is meant to help find alternative days for reservation if there are no available rooms on the days the user entered!
    public static Date alternativeDates(Date date, int d){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, d);
        //System.out.println(calendar.getTime());
        return calendar.getTime();
    }

    public static Date checkDate(String gd, SimpleDateFormat sDF){
        try{
            return sDF.parse(gd);
        }
        catch (ParseException e) {
            System.out.println("Oh no, issue retrieving the date!");
            e.printStackTrace();
        }
        return null;
    }

    public static boolean validDateFormat(String vDF){
        DateFormat df = new SimpleDateFormat (dateformat);
        df.setLenient(false);//specifies that the date we're checking can't be lenient, has to be a precise input
        try{
            df.parse(vDF);
            return true;
        }
        catch (ParseException e){
            System.out.println("Please enter a valid date!");
            return false;
        }
    }

    public static Date getCheckInDate(Scanner scanner){

        boolean checkInDate = false;
        while(!checkInDate){
            try {
                System.out.println("Let's get you started. ");
                System.out.println("Enter a check in date using the following format: 'MM/dd/yyyy");
                String input = scanner.next();
                checkInDate = validDateFormat(input);
                if(checkInDate) {
                    return checkDate(input, formatter);
                }
            }
            catch(IllegalArgumentException e){
                System.out.println("Sorry, you input an invalid date format. ");
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Date getCheckOutDate(Scanner scanner, Date ciDOP){

        boolean checkOutDate = false;
        while(!checkOutDate){
            try {
                System.out.println("Alright, let's figure out a check out date ");
                System.out.println("Enter a check out date using the following format: 'MM/dd/yyyy");
                String input = scanner.next();
                checkOutDate = validDateFormat(input);
                if(checkOutDate){
                    return checkDate(input,formatter);
                }
            }
            catch(IllegalArgumentException e){
                System.out.println("Sorry, you input an invalid date format. ");
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Collection <IRoom> findAvailableRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> avRooms = hotelResource.findARoom(checkInDate, checkOutDate);
        if(avRooms == null || avRooms.isEmpty()){
            System.out.println("Unfortunately, there are no rooms currently available during the days you selected.");
        }
        else {
            System.out.println("Here are the rooms available during the time period you listed.: ");
            return avRooms;
        }
        return null;
    }

    // this bad boy ain't working!

    public static void seeMyReservation(Scanner scanner){

        System.out.println("Welcome Back! Here to check a reservation? Enter your email in the following format: 'my@email.com'");
        String resCheckEmail = scanner.next();
        Customer resCheckCustomer = hotelResource.getCustomer(resCheckEmail);
        Collection<Reservation> reservations = hotelResource.getCustomerReservation(String.valueOf(resCheckCustomer));
        //if customer doesn't have an account with the hotel, it'll open the createAccount function and let them make an account!
        if(resCheckCustomer == null){
            System.out.println("Sorry, no account is associated with this email :/. Maybe you should try making a brand new account with us and try again!");
            System.out.println("\n Bringing you to the new account creation menu!");
            createAccount();
        }
        else {
            if(reservations.isEmpty() ||reservations == null){
                System.out.println("There are no reservations under this account. ");
            }
            else{
                System.out.println(reservations);
            }

        }
    }



    public static Customer createAccount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("New here? Let's start by entering your email! Ex: 'my@email.com' ");
        String bnEmail = scanner.next();
        System.out.println("Perfect, now let's get to know each other better! What's your first name? ");
        String bnFirstName = scanner.next();
        System.out.println("Great! Almost done! Things are about to get a little more intimate though... What's your last name?");
        String bnLastName = scanner.next();

        //going to try to push through the user's input to the createACustomer function within the hotel resource class
        try {
            hotelResource.createACustomer(bnEmail,bnFirstName,bnLastName);
            System.out.println("Nice! You successfully made your account!! Good job!");
            }
        //if the email is invalid, we're going to throw an exception at the invalid email.
        catch(IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
            createAccount();
        }
        return hotelResource.getCustomer(bnEmail);
    }

}
