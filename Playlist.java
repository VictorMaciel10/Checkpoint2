import java.util.ArrayList;

public class Playlist {

    // ==================== ATRIBUTOS PRIVADOS ====================

    private String nome;
    private ArrayList<Musica> musicas = new ArrayList<>();

    // ==================== CONSTRUTORES ====================

    // Construtor padrão
    public Playlist() {
        this("Sem nome");
    }

    // Construtor parametrizado
    public Playlist(String nome) {
        setNome(nome);
    }

    // ==================== GETTERS ====================

    public String getNome() {
        return nome;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    // ==================== SETTERS COM VALIDAÇÃO ====================

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da playlist não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
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
