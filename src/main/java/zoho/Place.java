package zoho;

public class Place {
    private int placeId;
    private String placeName;
    private int distanceFromStart;

    public int getPlaceId() {
        return placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public Place(int placeId, String placeName, int distanceFromStart) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.distanceFromStart = distanceFromStart;
    }
}
