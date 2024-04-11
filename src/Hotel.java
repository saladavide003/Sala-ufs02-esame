public class Hotel {
    private String nome;
    private int numeroStanze;
    private boolean spa;
    private int mediaRecensioni;


    public Hotel(String nome, int numeroStanze, boolean spa, int mediaRecensioni) {
        this.nome = nome;
        this.numeroStanze = numeroStanze;
        this.spa = spa;
        this.mediaRecensioni = mediaRecensioni;
    }

    //nome
    public String getNome() {
        return nome; //string
    }

    //numero stanze
    public int getNumeroStanze() {
        return numeroStanze; //int
    }

    //spa true/false
    public static boolean getSpa() {
        return spa; //boolean
    }

    //media delle recensioni da 1 a 5
    public float getMediaRecensioni() {
        return mediaRecensioni; //int
    }

    @Override
    public static String toString() {
        return ("Nome hotel: " + nome + ", numero stanze tot: " + numeroStanze + ", spa presente: " + spa + ", media recensioni: " + mediaRecensioni);
    }
}
