package zoho;

import java.util.*;
import java.util.stream.Collectors;

public class Booking {

    List<Taxi> taxiList = new ArrayList<>();
    Map<String, Place> placeList = new HashMap<>();
    List<Ride> rideLog = new ArrayList<>();

    Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        Booking booking = new Booking();
        booking.initializePlace();
        booking.initializeTaxi();
        for(int i=0;i<3;i++)
            booking.book();
        booking.print();
    }

    void print() {
        for(Taxi taxi : taxiList) {
            System.out.println(taxi);
            System.out.println(rideLog.stream().filter(l->l.taxtId ==taxi.getId()).collect(Collectors.toList()));
        }
    }

    void initializeTaxi() {
        for(int i=1;i<5;i++) {
            Taxi taxi = new Taxi(i, placeList.get("A"));
            taxiList.add(taxi);
        }
    }

    void initializePlace() {
        Place place = new Place(1, "A", 0);
        placeList.put("A", place);
        place = new Place(2, "B", 15);
        placeList.put("B", place);
        place = new Place(3, "C", 30);
        placeList.put("C",place);
        place = new Place(4, "D", 45);
        placeList.put("D", place);
        place = new Place(5, "E", 60);
        placeList.put("E", place);
        place = new Place(6, "F", 75);
        placeList.put("F", place);
    }

    void book() {
        String startPlace, endPlace;
        int pickupTime, cusid;
        System.out.print("\nCustomer Id : ");
        cusid = reader.nextInt();
        System.out.print("\nStart location : ");
        startPlace = reader.next();
        System.out.print("\nDrop location : ");
        endPlace = reader.next();
        System.out.print("\nPickup time : ");
        pickupTime = reader.nextInt();
        System.out.println(startPlace + " to " + endPlace + " @ " + pickupTime);
        Taxi taxi = allotTaxi(pickupTime, startPlace);
        if(taxi != null ) {
            int temp[] = calculateFareAndTime(startPlace, endPlace);
            taxi.setFare(taxi.getFare() + temp[0]);
            taxi.setFreeAt(pickupTime + temp[1]);
            taxi.setPlace(placeList.get(endPlace));
            System.out.println("Allotted taxi : " + taxi);
            rideLog.add(new Ride(rideLog.size()+1, cusid, startPlace,
                    endPlace, pickupTime, pickupTime+temp[1], temp[0], taxi.getId()));
        } else {
            System.out.println("Booking is rejected");
        }
    }

    Taxi allotTaxi(int pickupTime, String startPlace) {
        List<Taxi> availableTaxi = taxiList.stream().filter(t->startPlace.equalsIgnoreCase(t.getPlace().getPlaceName())
                && pickupTime >= t.getFreeAt()).sorted(Comparator.comparingInt(Taxi::getFare)).collect(Collectors.toList());
        if(availableTaxi.size() == 0) {
            availableTaxi = taxiList.stream().filter(t->pickupTime >= t.getFreeAt()).sorted((o1, o2) -> {
                int compareValue = Math.abs(o1.getPlace().getDistanceFromStart() - placeList.get(startPlace).getDistanceFromStart())
                                    - Math.abs(o2.getPlace().getDistanceFromStart() - placeList.get(startPlace).getDistanceFromStart());
                if(compareValue != 0)
                    return compareValue;
                return o1.getFare() - o2.getFare();
            }).collect(Collectors.toList());
        }
        return 0 < availableTaxi.size() ? availableTaxi.get(0) : null;
    }

    int[] calculateFareAndTime(String startPlace, String endPlace) {
        int distance, fare = 100, timeTaken;
        Place start = placeList.get(startPlace);
        Place end = placeList.get(endPlace);
        distance = Math.abs(start.getDistanceFromStart() - end.getDistanceFromStart()) - 5;
        fare += distance * 10;
        timeTaken = Math.abs(start.getPlaceId() - end.getPlaceId());
        return new int[] {fare, timeTaken};
    }

    int calculateFreeAt(Taxi taxi, int timeTaken, String startPlace) {
        int travelTime;
        travelTime = timeTaken ;
                //+ Math.abs(taxi.getPlace().getDistanceFromStart() - placeList.get(startPlace).getDistanceFromStart())/15;
        return travelTime;
    }

}
