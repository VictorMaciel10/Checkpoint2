import java.util.ArrayList;

public class Usuario {

    String nome;
    ArrayList<Playlist> playlists = new ArrayList<>();

    public void criarPlaylist(String nome) {
        Playlist nova = new Playlist();
        nova.nome = nome;
        this.playlists.add(nova);
        System.out.println(" Playlist \"" + nome + "\" criada com sucesso!");
    }

    public Playlist getPlaylist(int indice) {
        if (indice < 0 || indice >= this.playlists.size()) {
            return null;
        }
        return this.playlists.get(indice);
    }


    public void listarPlaylists() {
        if (this.playlists.size() == 0) {
            System.out.println("Você não tem nenhuma playlist criada.");
            return;
        }

        System.out.println("\n Playlists de " + this.nome + ":");
        for (int i = 0; i < this.playlists.size(); i++) {
            Playlist p = this.playlists.get(i);
            System.out.println("[" + i + "] " + p.nome + " - " + p.getQuantidadeMusicas() + " música(s)");
        }
    }

}
