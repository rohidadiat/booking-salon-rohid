package com.booking.service;

import java.util.List;

import com.booking.models.*;


public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public static String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public void showRecentReservation(List<Reservation> reservationList){
        int num = 1;
        System.out.println("+====================================================================================================================================================================+");
        System.out.printf("| %-4s | %-5s | %-15s | %-80s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+====================================================================================================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-5s | %-15s | %-80s | %-15s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
        System.out.println("+====================================================================================================================================================================+");
    }
    
    public void showHistoryReservation(List<Reservation> reservationList){
        int num = 1;
        double total=0;
        System.out.println("+====================================================================================================================================================================+");
        System.out.printf("| %-4s | %-5s | %-15s | %-80s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+====================================================================================================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Finish")) {
            	total+=reservation.getReservationPrice();
            }
            System.out.printf("| %-4s | %-5s | %-15s | %-80s | %-15s | %-15s | %-10s |\n",
            		num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
            num++;
        }
        System.out.println("+====================================================================================================================================================================+");
        System.out.printf("| %-113s | %-46s |\n", "Keuntungan Total: ", total);
        System.out.println("+====================================================================================================================================================================+");
    }

    public void showAllCustomer(List<Person> personList){
    	int num = 1;
    	System.out.println("+===============================================================+");
        System.out.printf("| %-4s | %-10s | %-15s | %-10s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Address", "Membership");
        System.out.println("+===============================================================+");
        for (Person person : personList) {
            if (person instanceof Customer) {
                System.out.printf("| %-4s | %-10s | %-15s | %-10s | %-10s |\n",
                num, person.getId(), person.getName(), person.getAddress(), ((Customer) person).getMember().getMembershipName());
                num++;
            }
        }
        System.out.println("+===============================================================+");
    }

    public void showAllEmployee(List<Person> personList){
    	int num = 1;
    	System.out.println("+===============================================================+");
        System.out.printf("| %-4s | %-10s | %-15s | %-10s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Address", "Experience");
        System.out.println("+===============================================================+");
        for (Person person : personList) {
            if (person instanceof Employee) {
                System.out.printf("| %-4s | %-10s | %-15s | %-10s | %-10s |\n",
                num, person.getId(), person.getName(), person.getAddress(), ((Employee) person).getExperience());
                num++;
            }
        }
        System.out.println("+===============================================================+");        
    }

    public void showHistoryReservation(){
        
    }
}
