public class UsuarioFree extends Usuario {

    // ==================== ATRIBUTOS PRIVADOS ====================

    private static final int MAX_PLAYLISTS = 3;
    private int contadorReproducoes;

    // ==================== CONSTRUTORES ====================

    public UsuarioFree(String nome, String email) {
        super(nome, email); // Chama construtor da superclasse
        this.contadorReproducoes = 0;
    }

    // ==================== GETTERS ====================

    public int getContadorReproducoes() {
        return contadorReproducoes;
    }

    // ==================== MÉTODOS ====================

    @Override
    public void reproduzirMusica(Musica musica) {
        contadorReproducoes++;

        // A cada 3 músicas, exibe anúncio
        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }

        super.reproduzirMusica(musica); // Chama método da superclasse
    }

    @Override
    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("❌ Limite de playlists atingido!");
            System.out.println("💎 Assine Premium para playlists ilimitadas!");
            return;
        }

        Playlist playlist = new Playlist(nome);
        playlists.add(playlist);
        System.out.println("✅ Playlist criada!");
    }

    private void exibirAnuncio() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("📢 ANÚNCIO: Assine Premium e ouça sem interrupções!");
        System.out.println("-".repeat(50) + "\n");
    }

}
