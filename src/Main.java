import java.io.*;
import java.net.*;

public class Main {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server in ascolto sulla porta " + PORT + "...");

            HotelManager hotelManager = new HotelManager();

            //aggiunta degli alberghi
            //nome albergo, numero stanze, spa presente, media recensioni da 1 a 10
            hotelManager.aggiungiHotel(new Hotel("Le Porte di Venezia", 100, true, 5));
            hotelManager.aggiungiHotel(new Hotel("Sette Nani", 70, true, 5));
            hotelManager.aggiungiHotel(new Hotel("Mi Casa Es Tu Casa", 40, true, 4));
            hotelManager.aggiungiHotel(new Hotel("Alcione", 60, true, 3));
            hotelManager.aggiungiHotel(new Hotel("Four Seasons", 40, true, 4));
            hotelManager.aggiungiHotel(new Hotel("Rustico di Scampia", 30, true, 4));
            hotelManager.aggiungiHotel(new Hotel("Bella Napoli", 60, true, 2));

            do {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuova connessione da: " + clientSocket.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String command = in.readLine();
                System.out.println("Comando ricevuto dal client: " + command);
                //trasforma tutto in maiuscolo il comando
                command = command.toUpperCase();
                //toglie gli spazi bianchi prima e dopo il comando
                command = command.trim();

                //selezione di cosa fare
                switch (command) {
                    case "ALL":
                        // Invia la lista di tutti gli alberghi
                        for (Hotel hotel) out.println(Hotel.toString());
                        break;

                    case "SORTED_BY_NAME":
                        // Invia la lista di tutti gli alberghi in ordine alfabetico
                        break;

                    case "WITH_SPA":
                        // Invia gli alberghi muniti di Spa
                        hotelManager.hotelConSpa();
                        break;
                    default:
                        out.println("Nessun albergo trovato");
                }
                clientSocket.close();
                out.println("");
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
