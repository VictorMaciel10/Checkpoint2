import java.util.ArrayList;

public class Playlist {

    // ==================== ATRIBUTOS PROTECTED ====================

    protected String nome;
    protected ArrayList<Musica> musicas = new ArrayList<>();
    protected String descricao;

    // ==================== CONSTRUTORES ====================

    // Construtor padrão
    public Playlist() {
        this("Sem nome");
    }

    // Construtor parametrizado
    public Playlist(String nome) {
        setNome(nome);
        this.descricao = "";
    }

    // Construtor com descrição
    public Playlist(String nome, String descricao) {
        setNome(nome);
        this.descricao = (descricao != null) ? descricao : "";
    }

    // ==================== GETTERS ====================

    public String getNome() {
        return nome;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public String getDescricao() {
        return descricao;
    }

    // ==================== SETTERS COM VALIDAÇÃO ====================

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da playlist não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    public void setDescricao(String descricao) {
        this.descricao = (descricao != null) ? descricao : "";
    }

    // ==================== MÉTODOS ====================

    public void adicionarMusica(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("Não é possível adicionar uma música nula.");
        }
        this.musicas.add(musica);
        System.out.println("Música \"" + musica.getTitulo() + "\" adicionada à playlist \"" + this.nome + "\"!");
    }

    public void removerMusica(int indice) {
        if (indice < 0 || indice >= this.musicas.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        String tituloRemovido = this.musicas.get(indice).getTitulo();
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

    // Método reproduzir — pode ser sobrescrito por subclasses
    public void reproduzir() {
        System.out.println("\n🎵 Reproduzindo playlist: " + nome);
        if (musicas.size() == 0) {
            System.out.println("A playlist está vazia.");
            return;
        }
        for (Musica m : musicas) {
            System.out.println("  ▶ " + m.getTitulo() + " - " + m.getArtista());
        }
    }

    public int getDuracaoTotal() {
        int total = 0;
        for (int i = 0; i < this.musicas.size(); i++) {
            total += this.musicas.get(i).getDuracaoSegundos();
        }
        return total;
    }

    public int getQuantidadeMusicas() {
        return this.musicas.size();
    }

}
