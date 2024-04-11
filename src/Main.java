import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server in ascolto sulla porta " + PORT + "...");

            HotelManager hotelManager = new HotelManager();

            // Aggiunta degli hotel
            hotelManager.aggiungiHotel(new Hotel("Le Porte di Venezia", 100, true, 5));
            hotelManager.aggiungiHotel(new Hotel("Sette Nani", 70, true, 5));
            hotelManager.aggiungiHotel(new Hotel("Mi Casa Es Tu Casa", 40, true, 4));
            hotelManager.aggiungiHotel(new Hotel("Alcione", 60, true, 3));
            hotelManager.aggiungiHotel(new Hotel("Four Seasons", 40, true, 4));
            hotelManager.aggiungiHotel(new Hotel("Rustico di Scampia", 30, true, 4));
            hotelManager.aggiungiHotel(new Hotel("Bella Napoli", 60, true, 2));

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Nuova connessione da: " + clientSocket.getInetAddress().getHostAddress());

                    String command = in.readLine();
                    System.out.println("Comando ricevuto dal client: " + command);

                    if (command != null) {
                        command = command.toUpperCase().trim();

                        switch (command) {
                            case "ALL":
                                // Invia la lista di tutti gli hotel
                                for (Hotel hotel : hotelManager.getHotels()) {
                                    out.println(hotel.toString());
                                }
                                break;

                            case "SORTED_BY_NAME":
                                // Invia la lista di tutti gli hotel ordinati per nome
                                List<Hotel> sortedHotelsByName = hotelManager.getHotelsSortedByName();
                                for (Hotel hotel : sortedHotelsByName) {
                                    out.println(hotel.toString());
                                }
                                break;

                            case "WITH_SPA":
                                // Invia gli hotel con Spa
                                List<Hotel> spaHotels = hotelManager.getHotelsWithSpa();
                                if (!spaHotels.isEmpty()) {
                                    for (Hotel hotel : spaHotels) {
                                        out.println(hotel.toString());
                                    }
                                } else {
                                    out.println("Nessun hotel con Spa presente");
                                }
                                break;

                            default:
                                out.println("Comando non valido");
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}