package com.booking.service;

import com.booking.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;

public class ReservationService {
	private static Scanner input = new Scanner(System.in);
	private static ValidationService validate = new ValidationService();
	private static List<Person> personList = PersonRepository.getAllPerson();
	private static List<Service> serviceList = ServiceRepository.getAllService();
	
    public static void createReservation(int num){
    	String id = "Ord-"+num;
    	Person person = getCustomerByCustomerId();
    	Person employee = getEmployeeByEmployeeId();
    	List<Service> list = services();
    	
    	Reservation rev = Reservation.builder()
    			.reservationId(id)
    			.customer((Customer) person)
    			.employee((Employee) employee)
    			.services(list)
    			.workstage("In Process")
    			.build();
    	
    	MenuService.addReservation(rev);
    }
    
    public static List<Service> services(){
    	List<Service> listservice = new ArrayList<>();
    	boolean loop = true;
    	String[] MenuArr = new String[serviceList.size()+1];
	
    	int i=0,optionSubMenu;
    	for (Service service : serviceList) {
    		MenuArr[i] = service.getServiceName();
    		i++;
        }
    	MenuArr[serviceList.size()] = "Selesai pilih menu";

    	do {
    		PrintService.printMenu("Service Menu", MenuArr); 
    		optionSubMenu = Integer.valueOf(validate.validateInput("^[0-9]+$","Pilih service menu 0-5: ", 0, 5, true));
    		switch(optionSubMenu) {
    		case 1 : {
    			Service sr = com.booking.repositories.ServiceRepository.getAllService().get(0);
    			if(validate.validateService(listservice, sr)) {
    				listservice.add(sr);    				
    			} else {
    				System.out.println("Service telah dipilih. silahkan pilih service lain");
    			}
    			break;
    		}
    		case 2 :{
    			Service sr = com.booking.repositories.ServiceRepository.getAllService().get(1);
    			if(validate.validateService(listservice, sr)) {
    				listservice.add(sr);    				
    			} else {
    				System.out.println("Service telah dipilih. silahkan pilih service lain");
    			}
    			break;
    		}
    		case 3 : {
    			Service sr = com.booking.repositories.ServiceRepository.getAllService().get(2);
    			if(validate.validateService(listservice, sr)) {
    				listservice.add(sr);    				
    			} else {
    				System.out.println("Service telah dipilih. silahkan pilih service lain");
    			}
    			break;
    		}
    		case 4 : {
    			Service sr = com.booking.repositories.ServiceRepository.getAllService().get(3);
    			if(validate.validateService(listservice, sr)) {
    				listservice.add(sr);    				
    			} else {
    				System.out.println("Service telah dipilih. silahkan pilih service lain");
    			}
    			break;
    		}
    		case 5 : {
    			Service sr = com.booking.repositories.ServiceRepository.getAllService().get(4);
    			if(validate.validateService(listservice, sr)) {
    				listservice.add(sr);    				
    			} else {
    				System.out.println("Service telah dipilih. silahkan pilih service lain");
    			}
    			break;
    		}
    		case 0 :
    			loop = false;
    			break;
    		}
    		
    		if(serviceList.size() == listservice.size()) {
    			loop = false;
    		}
             
    	} while(loop);
    	
    	
    	return listservice;
    }
    
    public static Person getEmployeeByEmployeeId(){
    	Person person = new Employee();
    	boolean loop = true;
    	
    	do {
            String id  = validate.validateInput("^[a-zA-Z0-9-]+$","Masukan Employee Id: ", 0, 20, false);
            Person p = validate.validateEmployeeId(personList,id);
            if(p != null) {
            	person = p;
            	loop = false;
            } else {
            	 System.out.println("Employee yang dicari tidak tersedia");
            }
             
    	} while(loop);
    	
    	return person;    	
    }

    public static Person getCustomerByCustomerId(){
    	Person person = new Customer();
    	boolean loop = true;
    	
    	do {
             String id  = validate.validateInput("^[a-zA-Z0-9-]+$","Masukan Customer Id: ", 0, 20, false);
             Person p = validate.validateCustomerId(personList,id);
             if(p != null) {
            	 person = p;
            	 loop = false;
             } else {
            	 System.out.println("Customer yang dicari tidak tersedia");
             }
             
    	} while(loop);
    	
    	return person;    	
    }

    public static void validateReservation(List<Reservation> reservationList){
    	boolean loop = true;
    	PrintService print = new PrintService();
    	
    	do {
    		if(reservationList.size() == 0) {
    			System.out.println("Reservation kosong");
    			loop=false;
    		} else {
    			print.showRecentReservation(reservationList);
	            String id  = validate.validateInput("^[a-zA-Z0-9-]+$","Masukan Reservation Id: ", 0, 20, false);
	            Reservation res = validate.validateReservation(reservationList ,id);
	            if(res != null) {
	            	System.out.println("Pilih Reservation \n1. Finish \n2. Canceled");
	            	System.out.print("Masukan pilihan: ");
	                int menu  = input.nextInt();
	                if(menu==1) {
	                	MenuService.finishReservation(res);;
	                	System.out.println("Finish Reservation berhasil");
	                } else if(menu==2) {
	                	MenuService.cancelReservation(res);
	                	System.out.println("Cansel Reservation berhasil");
	                }
	            	loop = false;
	            } else {
	            	System.out.println("Reservation yang dicari tidak tersedia");
	            }
    		}
             
    	} while(loop);
    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
