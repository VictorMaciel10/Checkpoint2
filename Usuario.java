import java.util.ArrayList;

public class Usuario {

    // ==================== ATRIBUTOS PRIVADOS ====================

    private String nome;
    private ArrayList<Playlist> playlists = new ArrayList<>();

    // ==================== CONSTRUTORES ====================

    // Construtor padrão
    public Usuario() {
        this("Usuário");
    }

    // Construtor parametrizado
    public Usuario(String nome) {
        setNome(nome);
    }

    // ==================== GETTERS ====================

    public String getNome() {
        return nome;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    // ==================== SETTERS COM VALIDAÇÃO ====================

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    // ==================== MÉTODOS ====================

    public void criarPlaylist(String nome) {
        Playlist nova = new Playlist(nome);
        this.playlists.add(nova);
        System.out.println(" Playlist \"" + nome + "\" criada com sucesso!");
    }

    public void adicionarPlaylist(Playlist playlist) {
        if (playlist == null) {
            throw new IllegalArgumentException("Não é possível adicionar uma playlist nula.");
        }
        this.playlists.add(playlist);
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
            System.out.println("[" + i + "] " + p.getNome() + " - " + p.getQuantidadeMusicas() + " música(s)");
        }
    }

}
