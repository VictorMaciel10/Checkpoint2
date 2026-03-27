import java.util.ArrayList;

public class Playlist {

    String nome;
    ArrayList<Musica> musicas = new ArrayList<>();

    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
        System.out.println("Música \"" + musica.titulo + "\" adicionada à playlist \"" + this.nome + "\"!");
    }

    public void removerMusica(int indice) {
        if (indice < 0 || indice >= this.musicas.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        String tituloRemovido = this.musicas.get(indice).titulo;
        this.musicas.remove(indice);
        System.out.println("Música \"" + tituloRemovido + "\" removida da playlist.");
    }

    public void listarMusicas() {
        if (this.musicas.size() == 0) {
            System.out.println("A playlist \"" + this.nome + "\" está vazia.");
            return;
        }

        System.out.println("\n Playlist: " + this.nome);
        System.out.println("Total: " + this.getQuantidadeMusicas() + " música(s)");

        for (int i = 0; i < this.musicas.size(); i++) {
            System.out.print("[" + i + "] ");
            this.musicas.get(i).exibir();
        }
    }

    public int getDuracaoTotal() {
        int total = 0;
        for (int i = 0; i < this.musicas.size(); i++) {
            total += this.musicas.get(i).duracaoSegundos;
        }
        return total;
    }

    public int getQuantidadeMusicas() {
        return this.musicas.size();
    }

}
