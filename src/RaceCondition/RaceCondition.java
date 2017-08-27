package RaceCondition;

import java.util.ArrayList;

/**
 * Created by igaurav on 5/8/2017.
 */

class TicketBooking implements Runnable {

    int ticketsAvailable = 1;

    @Override
    public void run() {
        System.out.println("Waiting to book ticket for: " + Thread.currentThread().getName());

        synchronized (this) {
            if (ticketsAvailable > 0) {
                System.out.println("Booking tickets for: " + Thread.currentThread().getName());

                //Let's say system takes some time in booking ticket (here we have taken 1 second time)
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }

                ticketsAvailable--;
                System.out.println("Tickets Booked for :" + Thread.currentThread().getName());
                System.out.println("Currently tickects available :" + ticketsAvailable);
            } else {
                System.out.println("Ticket NOT BOOKED for :" + Thread.currentThread().getName());
            }
        }
    }

}


public class RaceCondition {

    public static void main(String args[]) {
        TicketBooking obj = new TicketBooking();

        Thread t1 = new Thread(obj, "Passenger One");
        Thread t2 = new Thread(obj, "Passenger Two");

        t1.start();
        t2.start();

        ArrayList bucketArray = new ArrayList();


    }

}
