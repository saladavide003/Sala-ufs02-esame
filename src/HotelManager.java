import java.util.ArrayList;
import java.util.List;

public class HotelManager {
    private List<Hotel> hotels;

    public HotelManager() {
        this.hotels = new ArrayList<>();
    }

    //popola la list
    public void aggiungiHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    //controlla se un hotel ha la spa
    public Hotel hotelConSpa() {
        //fa un ciclo per ogni elemento di hotels
        for (Hotel hotel : hotels) {
            if (Hotel.getSpa()) {
                System.out.println(toString());
            }
        }
    return 0;
    }
}
