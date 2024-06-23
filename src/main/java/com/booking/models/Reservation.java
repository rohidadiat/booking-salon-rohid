package com.booking.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;
    private String workstage;
    //   workStage (In Process, Finish, Canceled)

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services,
            String workstage) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
//        this.reservationPrice = calculateReservationPrice();
        this.workstage = workstage;
    };

    public void calculateReservationPrice(){
    	double result=0;
    	for(Service s: services) {
    		result+=s.getPrice();
    	}
    	
    	if(customer.getMember().getMembershipName().equals("Silver")) {
    		this.reservationPrice= result-(result*0.05);
    	} else if(customer.getMember().getMembershipName().equals("Gold")) {
    		this.reservationPrice= result-(result*0.1);
    	} else {
    		this.reservationPrice= result;
    	}
    }
}
