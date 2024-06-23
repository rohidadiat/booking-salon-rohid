package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;

public class MenuService {
    private static List<Person> personList = PersonRepository.getAllPerson();
    private static List<Service> serviceList = ServiceRepository.getAllService();
    private static List<Reservation> reservationList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);
    private static ValidationService validate = new ValidationService(); 

    public static void mainMenu() {
    	int num=1;
        String[] mainMenuArr = {"Show Data", "Create Reservation", "Complete/cancel reservation", "Exit"};
        String[] subMenuArr = {"Recent Reservation", "Show Customer", "Show Available Employee", "Reservation History & total Keuntungan", "Back to main menu"};
        
        PrintService print = new PrintService();
    
        int optionMainMenu;
        int optionSubMenu;

		boolean backToMainMenu = false;
        boolean backToSubMenu = false;
        do {
            PrintService.printMenu("Main Menu", mainMenuArr);
            optionMainMenu = Integer.valueOf(validate.validateInput("^[0-9]+$","Pilih menu 0-3: ", 0, 3, true));
            switch (optionMainMenu) {
                case 1:
                    do {
                        PrintService.printMenu("Show Data", subMenuArr);
                        optionSubMenu = Integer.valueOf(validate.validateInput("^[0-9]+$","Pilih menu 0-4: ", 0, 4, true));
                        // Sub menu - menu 1
                        switch (optionSubMenu) {
                            case 1:
                                // panggil fitur tampilkan recent reservation
                            	print.showRecentReservation(reservationList);
                                break;
                            case 2:
                                // panggil fitur tampilkan semua customer
                            	print.showAllCustomer(personList);
                                break;
                            case 3:
                                // panggil fitur tampilkan semua employee
                            	print.showAllEmployee(personList);
                                break;
                            case 4:
                                // panggil fitur tampilkan history reservation + total keuntungan
                            	print.showHistoryReservation(reservationList);
                                break;
                            case 0:
                                backToSubMenu = true;
                                break;
                        }
                    } while (!backToSubMenu);
                    break;
                case 2:
                    // panggil fitur menambahkan reservation
                	ReservationService.createReservation(num);
                	num++;
                    break;
                case 3:
                    // panggil fitur mengubah workstage menjadi finish/cancel
                	ReservationService.validateReservation(reservationList);
                    break;
                case 0:
                    backToMainMenu = true;
                    break;
            }
        } while (!backToMainMenu);
		
	}
    
    public static void addReservation(Reservation res) {
    	res.calculateReservationPrice();
    	reservationList.add(res);
    }
    
    public static List<Reservation>  getallreservation(){
    	return reservationList;
    }
    
    public static void finishReservation(Reservation res) {
    	for(Reservation R: reservationList) {
    		if(R.getReservationId().equals(res.getReservationId())) {
    			R.setWorkstage("Finish");
    		}
    	}
    }
    
    public static void cancelReservation(Reservation res) {
    	for(Reservation R: reservationList) {
    		if(R.getReservationId().equals(res.getReservationId())) {
    			R.setWorkstage("Canceled");
    		}
    	}
    }
    
}
