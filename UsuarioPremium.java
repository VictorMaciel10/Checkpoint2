import java.util.ArrayList;

public class UsuarioPremium extends Usuario {

    // ==================== ATRIBUTOS PRIVADOS ====================

    private String tipoPlano; // Mensal, Anual, Familiar
    private ArrayList<Musica> musicasBaixadas;

    // Planos válidos
    private static final String[] PLANOS_VALIDOS = {"Mensal", "Anual", "Familiar"};

    // ==================== CONSTRUTORES ====================

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email); // Chama construtor da superclasse
        setTipoPlano(plano);
        this.musicasBaixadas = new ArrayList<>();
    }

    // ==================== GETTERS ====================

    public String getTipoPlano() {
        return tipoPlano;
    }

    public ArrayList<Musica> getMusicasBaixadas() {
        return musicasBaixadas;
    }

    // ==================== SETTERS COM VALIDAÇÃO ====================

    public void setTipoPlano(String plano) {
        if (plano == null) {
            throw new IllegalArgumentException("Plano não pode ser nulo.");
        }
        for (String p : PLANOS_VALIDOS) {
            if (p.equalsIgnoreCase(plano.trim())) {
                this.tipoPlano = p;
                return;
            }
        }
        throw new IllegalArgumentException("Plano inválido: \"" + plano + "\". Planos aceitos: Mensal, Anual, Familiar.");
    }

    // ==================== MÉTODOS ====================

    @Override
    public void reproduzirMusica(Musica musica) {
        System.out.println("🎵 [Alta Qualidade]");
        super.reproduzirMusica(musica); // Chama método da superclasse
    }

    public void baixarMusica(Musica musica) {
        if (musica == null) {
            System.out.println("Música inválida.");
            return;
        }
        musicasBaixadas.add(musica);
        System.out.println("⬇️ Música \"" + musica.getTitulo() + "\" baixada com sucesso!");
    }

    public void listarMusicasBaixadas() {
        System.out.println("\n--- MÚSICAS BAIXADAS ---");
        if (musicasBaixadas.size() == 0) {
            System.out.println("Nenhuma música baixada.");
            return;
        }
        for (int i = 0; i < musicasBaixadas.size(); i++) {
            System.out.print("[" + i + "] ");
            musicasBaixadas.get(i).exibir();
        }
    }

}
