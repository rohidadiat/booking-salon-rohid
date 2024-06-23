package com.booking.service;

import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.models.Employee;

public class ValidationService {
	private static Scanner scanner = new Scanner(System.in);
	
    // Buatlah function sesuai dengan kebutuhan
    public String validateInput(String regex, String title, int min, int max, boolean number){
    	String reg = regex, result="";
    	boolean isValid = false;
    	
    	do {
    		System.out.print(title);
    		String input = scanner.nextLine();
    		if(input.matches(reg)) {
    			if(number && Integer.parseInt(input) >= min && Integer.parseInt(input) <= max) {
    				result = input;
        			isValid=true;
    			} else if(!number && input.length() <= max) {
    				result = input;
        			isValid=true;
    			} else {
    				System.out.println("input yang dimasukan melebihi batas");
    			}
    		} else {
				System.out.println("input yang dimasukan tidak sesuai");
			}
    	} while(!isValid);
    	
    	return result;
    }

    public static Person validateCustomerId(List<Person> personList, String id){
    	for(Person p: personList) {
    		if(p.getId().equalsIgnoreCase(id)) {
	    		if(p instanceof Customer) {
	    			return (Customer) p;
	    		}
    		}
    	}
    	return null;
    }
    
    public static Person validateEmployeeId(List<Person> personList, String id){
    	for(Person p: personList) {
    		if(p.getId().equalsIgnoreCase(id)) {
	    		if(p instanceof Employee) {
	    			return (Employee) p;
	    		}
    		}
    	}
    	return null;
    }
    
    public static boolean validateService(List<Service> service, Service sr) {
    	for(Service srv: service) {
    		if(srv.getServiceName().equals(sr.getServiceName())) {
    			return false;
    		}
    	}
		return true;
    }
    
    public static Reservation validateReservation(List<Reservation> reservationList, String id){
    	for(Reservation R: reservationList) {
    		if(R.getReservationId().equalsIgnoreCase(id)) {
	    		return R;
    		}
    	}
    	return null;
    }
}
