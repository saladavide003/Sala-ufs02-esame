import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HotelManager {
    private List<Hotel> hotels;

    public HotelManager() {
        this.hotels = new ArrayList<>();
    }

    public void aggiungiHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public List<Hotel> getHotelsSortedByName() {
        List<Hotel> sortedList = new ArrayList<>(hotels);
        sortedList.sort(Comparator.comparing(Hotel::getNome));
        return sortedList;
    }

    public List<Hotel> getHotelsWithSpa() {
        List<Hotel> spaHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            if (hotel.isSpaPresente()) {
                spaHotels.add(hotel);
            }
        }
        return spaHotels;
    }
}