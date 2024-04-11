public class Hotel {
    private String nome;
    private int numeroStanze;
    private boolean spaPresente;
    private int mediaRecensioni;

    public Hotel(String nome, int numeroStanze, boolean spaPresente, int mediaRecensioni) {
        this.nome = nome;
        this.numeroStanze = numeroStanze;
        this.spaPresente = spaPresente;
        this.mediaRecensioni = mediaRecensioni;
    }

    public String getNome() {
        return nome;
    }

    public boolean isSpaPresente() {
        return spaPresente;
    }

    @Override
    public String toString() {
        return nome + " - Stanze: " + numeroStanze + ", Spa: " + (spaPresente ? "presente" : "non presente") + ", Media recensioni: " + mediaRecensioni;
    }
}