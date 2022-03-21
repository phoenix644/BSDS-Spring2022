package DAL;

import Model.LiftRide;

public class sendtoDB {

    LiftRideDao liftRideDao = new LiftRideDao();

    private void send(String message){


    }

    public static void main(String[] args) {
        LiftRideDao liftRideDao = new LiftRideDao();
        liftRideDao.createLiftRide(new LiftRide(10, 2, "3", "5", 500, 20));
    }

}
