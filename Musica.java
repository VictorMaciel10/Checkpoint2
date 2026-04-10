public class Musica {

    // ==================== ATRIBUTOS PRIVADOS ====================

    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private String genero;

    // Gêneros válidos aceitos pelo sistema
    private static final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};

    // ==================== CONSTRUTORES ====================

    // Construtor padrão — inicializa com valores padrão válidos
    public Musica() {
        this("Sem título", "Desconhecido", 1, "Pop");
    }

    // Construtor parametrizado — cria música já em estado válido
    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracaoSegundos(duracaoSegundos);
        setGenero(genero);
    }

    // ==================== GETTERS ====================

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public String getGenero() {
        return genero;
    }

    // ==================== SETTERS COM VALIDAÇÃO ====================

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser nulo ou vazio.");
        }
        this.titulo = titulo.trim();
    }

    public void setArtista(String artista) {
        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("Artista não pode ser nulo ou vazio.");
        }
        this.artista = artista.trim();
    }

    public void setDuracaoSegundos(int duracaoSegundos) {
        if (duracaoSegundos <= 0 || duracaoSegundos >= 3600) {
            throw new IllegalArgumentException("Duração deve ser maior que 0 e menor que 3600 segundos.");
        }
        this.duracaoSegundos = duracaoSegundos;
    }

    public void setGenero(String genero) {
        if (genero == null) {
            throw new IllegalArgumentException("Gênero não pode ser nulo.");
        }
        // Validação case-insensitive
        for (String g : GENEROS_VALIDOS) {
            if (g.equalsIgnoreCase(genero.trim())) {
                this.genero = g; // Armazena no formato padrão (ex: "Rock")
                return;
            }
        }
        throw new IllegalArgumentException(
            "Gênero inválido: \"" + genero + "\". Gêneros aceitos: Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica."
        );
    }

    // ==================== MÉTODOS ====================

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
