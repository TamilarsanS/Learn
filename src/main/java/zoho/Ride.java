package zoho;

public class Ride {
    int bookingId;
    int cusId;
    String from;
    String to;
    int pickupTime;
    int dropTime;
    int amount;
    int taxtId;

    public Ride(int bookingId, int cusId, String from, String to, int pickupTime, int dropTime, int amount, int taxtId) {
        this.bookingId = bookingId;
        this.cusId = cusId;
        this.from = from;
        this.to = to;
        this.pickupTime = pickupTime;
        this.dropTime = dropTime;
        this.amount = amount;
        this.taxtId = taxtId;
    }

    @Override
    public String toString() {
        return bookingId +
                ", " + cusId +
                ", " + from +
                ", " + to +
                ", " + pickupTime +
                ", " + dropTime +
                ", " + amount +
                '\n';
    }
}
