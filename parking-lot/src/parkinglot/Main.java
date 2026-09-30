package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Vehicle car   = new Vehicle("KA53MJ6743",VehicleType.CAR);
        Vehicle bike  = new Vehicle("KA03KV6023",VehicleType.BIKE);
        Vehicle truck = new Vehicle("KA01KK7203",VehicleType.TRUCK);

        ParkingSpot carspot   = new ParkingSpot(1,SpotType.CAR);
        ParkingSpot bikespot  = new ParkingSpot(2,SpotType.BIKE);
        ParkingSpot truckspot = new ParkingSpot(3,SpotType.TRUCK);

        List<ParkingSpot> parkingSpotList = new ArrayList<>();
        parkingSpotList.add(carspot);
        parkingSpotList.add(bikespot);
        parkingSpotList.add(truckspot);

        SpotAssignmentStrategy strategy = new FirstAvailableSpotStrategy();
        ParkingSpotManager parkingSpotManager = new ParkingSpotManager(parkingSpotList,strategy);

        ParkingFloor parkingFloor1  = new ParkingFloor(1,parkingSpotManager);
        List<ParkingFloor> parkingFloors = new ArrayList<>();
        parkingFloors.add(parkingFloor1);

        TicketService ticketService = new TicketService();
        FeeCalculator feeCalculator = new FeeCalculator();

        ParkingLot parkingLot = new ParkingLot(parkingFloors,ticketService,feeCalculator);

        Ticket carTicket   = parkingLot.parkVehicle(car);
        Ticket bikeTicket  = parkingLot.parkVehicle(bike);
        Ticket truckTicket = parkingLot.parkVehicle(truck);
        Ticket carTicket2  = parkingLot.parkVehicle(car);

        if(carTicket2 != null)
            System.out.println("Vehicle sucessfully Parked");
        else
            System.out.println("Sorry parking is full");

        System.out.println(carTicket.getTicketId());
        System.out.println(car.getVehicleType());
        System.out.println(carTicket.getParkingSpot().getId());

        System.out.println(bikeTicket.getTicketId());
        System.out.println(bike.getVehicleType());
        System.out.println(bikeTicket.getParkingSpot().getId());

        System.out.println(truckTicket.getTicketId());
        System.out.println(truck.getVehicleType());
        System.out.println(truckTicket.getParkingSpot().getId());

        double carfee = parkingLot.unparkVehicle(carTicket);
        System.out.println("Parking Fee: Rs."+ carfee);

        double bikefee = parkingLot.unparkVehicle(bikeTicket);
        System.out.println("Parking Fee: Rs."+ bikefee);

        double truckfee = parkingLot.unparkVehicle(truckTicket);
        System.out.println("Parking Fee: Rs."+ truckfee);

        Ticket carTicket3  = parkingLot.parkVehicle(car);

        if(carTicket3 != null)
            System.out.println("Vehicle sucessfully Parked");
        else
            System.out.println("Sorry parking is full");
    }
}
