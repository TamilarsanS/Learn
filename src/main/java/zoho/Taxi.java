package zoho;

public class Taxi {
    private int id;
    private Place place;
    private int fare;
    private int freeAt;

    public int getId() {
        return id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public int getFreeAt() {
        return freeAt;
    }

    public void setFreeAt(int freeAt) {
        this.freeAt = freeAt;
    }

    public Taxi(int id, Place place) {
        this.id = id;
        this.place = place;
        this.fare = 0;
        this.freeAt = 0;
    }

    @Override
    public String toString() {
        return "\nTaxi{" +
                "id=" + id +
                ", place=" + place.getPlaceName() +
                ", fare=" + fare +
                ", freeAt=" + freeAt +
                '}';
    }
}
