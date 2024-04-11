import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    private static final int PORT = 1234;
    private static HotelManager hotelManager = new HotelManager();

    public static void main(String[] args) {
        // Aggiunta degli hotel di esempio
        aggiungiHotelDiEsempio();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server in ascolto sulla porta " + PORT + "...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Nuova connessione da: " + clientSocket.getInetAddress().getHostAddress());

                    handleClient(clientSocket);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            boolean running = true;

            while (running && (inputLine = in.readLine()) != null) {
                System.out.println("Comando ricevuto dal client: " + inputLine);

                String response = processCommand(inputLine);
                out.println(response);

                // Verifica se il comando ricevuto è "EXIT" per terminare la connessione
                if (inputLine.trim().equalsIgnoreCase("EXIT")) {
                    running = false;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String processCommand(String command) {
        command = command.toUpperCase().trim();

        switch (command) {
            case "ALL":
                // Restituisce la lista di tutti gli hotel
                StringBuilder allHotels = new StringBuilder();
                for (Hotel hotel : hotelManager.getHotels()) {
                    allHotels.append(hotel.toString()).append("\n");
                }
                return allHotels.toString();

            case "SORTED_BY_NAME":
                // Restituisce la lista di tutti gli hotel ordinati per nome
                StringBuilder sortedHotelsByName = new StringBuilder();
                for (Hotel hotel : hotelManager.getHotelsSortedByName()) {
                    sortedHotelsByName.append(hotel.toString()).append("\n");
                }
                return sortedHotelsByName.toString();

            case "WITH_SPA":
                // Restituisce gli hotel con Spa
                StringBuilder spaHotels = new StringBuilder();
                List<Hotel> spaHotelList = hotelManager.getHotelsWithSpa();
                if (!spaHotelList.isEmpty()) {
                    for (Hotel hotel : spaHotelList) {
                        spaHotels.append(hotel.toString()).append("\n");
                    }
                } else {
                    spaHotels.append("Nessun hotel con Spa presente\n");
                }
                return spaHotels.toString();

            default:
                return "Comando non valido";
        }
    }

    private static void aggiungiHotelDiEsempio() {
        hotelManager.aggiungiHotel(new Hotel("Le Porte di Venezia", 100, false, 5));
        hotelManager.aggiungiHotel(new Hotel("Sette Nani", 70, true, 5));
        hotelManager.aggiungiHotel(new Hotel("Mi Casa Es Tu Casa", 40, true, 4));
        hotelManager.aggiungiHotel(new Hotel("Alcione", 60, true, 3));
        hotelManager.aggiungiHotel(new Hotel("Four Seasons", 40, false, 4));
        hotelManager.aggiungiHotel(new Hotel("Rustico di Scampia", 30, true, 4));
        hotelManager.aggiungiHotel(new Hotel("Bella Napoli", 60, false, 2));
    }
}