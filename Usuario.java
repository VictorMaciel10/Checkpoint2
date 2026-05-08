import java.util.ArrayList;

public class Usuario {

    // ==================== ATRIBUTOS PROTECTED ====================

    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists;
    protected ArrayList<Musica> historicoReproducao;

    // ==================== CONSTRUTORES ====================

    // Construtor padrão
    public Usuario() {
        this("Usuário", "usuario@email.com");
    }

    // Construtor parametrizado
    public Usuario(String nome, String email) {
        setNome(nome);
        setEmail(email);
        this.playlists = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
    }

    // ==================== GETTERS ====================

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public ArrayList<Musica> getHistoricoReproducao() {
        return historicoReproducao;
    }

    // ==================== SETTERS COM VALIDAÇÃO ====================

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    // Método final — validação crítica de e-mail que não deve ser alterada por subclasses
    public final void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido: deve conter '@'.");
        }
        this.email = email.trim();
    }

    // ==================== MÉTODOS ====================

    public void reproduzirMusica(Musica musica) {
        System.out.println("\n🎵 Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void exibirHistorico() {
        System.out.println("\n--- HISTÓRICO DE REPRODUÇÃO ---");
        if (historicoReproducao.size() == 0) {
            System.out.println("Nenhuma música reproduzida ainda.");
            return;
        }
        for (Musica m : historicoReproducao) {
            m.exibir();
        }
    }

    public void criarPlaylist(String nome) {
        Playlist nova = new Playlist(nome);
        this.playlists.add(nova);
        System.out.println("✅ Playlist \"" + nome + "\" criada com sucesso!");
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

        System.out.println("\n🎵 Playlists de " + this.nome + ":");
        for (int i = 0; i < this.playlists.size(); i++) {
            Playlist p = this.playlists.get(i);
            String tipo = (p instanceof PlaylistAutomatica) ? " 🤖[Automática]" : "";
            System.out.println("[" + i + "] " + p.getNome() + tipo + " - " + p.getQuantidadeMusicas() + " música(s)");
        }
    }

}
