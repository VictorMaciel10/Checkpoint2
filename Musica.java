public class Musica {

    String titulo;
    String artista;
    int duracaoSegundos;
    String genero;

    public void exibir() {
        System.out.println("\n " + this.titulo);
        System.out.println("Artista: " + this.artista);
        System.out.println("Duração: " + this.getDuracaoFormatada());
        System.out.println("Gênero: " + this.genero);
    }


    public String getDuracaoFormatada() {
        int min = this.duracaoSegundos / 60;
        int seg = this.duracaoSegundos % 60;
        return String.format("%d:%02d", min, seg);
    }

    public boolean contemTitulo(String busca) {
        return this.titulo.toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemArtista(String busca) {
        return this.artista.toLowerCase().contains(busca.toLowerCase());
    }

}
