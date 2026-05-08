import java.util.ArrayList;

public class PlaylistAutomatica extends Playlist {

    // ==================== ATRIBUTOS PRIVADOS ====================

    private String criterio; // "top", "recomendadas", "recentes"

    // Critérios válidos
    private static final String[] CRITERIOS_VALIDOS = {"top", "recomendadas", "recentes"};

    // ==================== CONSTRUTORES ====================

    public PlaylistAutomatica(String nome, String criterio) {
        super(nome, "Playlist gerada automaticamente pelo sistema");
        setCriterio(criterio);
    }

    // ==================== GETTERS ====================

    public String getCriterio() {
        return criterio;
    }

    // ==================== SETTERS COM VALIDAÇÃO ====================

    public void setCriterio(String criterio) {
        if (criterio == null) {
            throw new IllegalArgumentException("Critério não pode ser nulo.");
        }
        for (String c : CRITERIOS_VALIDOS) {
            if (c.equalsIgnoreCase(criterio.trim())) {
                this.criterio = c;
                return;
            }
        }
        throw new IllegalArgumentException("Critério inválido: \"" + criterio + "\". Critérios aceitos: top, recomendadas, recentes.");
    }

    // ==================== MÉTODOS ====================

    @Override
    public void reproduzir() {
        System.out.println("\n🤖 Playlist Automática: " + nome);
        System.out.println("📊 Critério: " + criterio);
        super.reproduzir(); // Chama método da superclasse
    }

    // Atualiza a playlist com base no critério e na lista de todas as músicas
    public void atualizar(ArrayList<Musica> todasMusicas) {
        if (todasMusicas == null || todasMusicas.size() == 0) {
            System.out.println("Nenhuma música disponível para gerar a playlist.");
            return;
        }

        musicas.clear();

        if (criterio.equals("top")) {
            // Adiciona até 10 músicas (simula as mais tocadas — ordem reversa da lista)
            int limite = Math.min(10, todasMusicas.size());
            for (int i = todasMusicas.size() - 1; i >= todasMusicas.size() - limite; i--) {
                musicas.add(todasMusicas.get(i));
            }
            System.out.println("✅ Playlist \"" + nome + "\" atualizada com as " + musicas.size() + " músicas mais tocadas!");

        } else if (criterio.equals("recomendadas")) {
            // Adiciona músicas de gênero variado (uma de cada gênero disponível)
            ArrayList<String> generosAdicionados = new ArrayList<>();
            for (Musica m : todasMusicas) {
                if (!generosAdicionados.contains(m.getGenero())) {
                    musicas.add(m);
                    generosAdicionados.add(m.getGenero());
                }
            }
            System.out.println("✅ Playlist \"" + nome + "\" atualizada com " + musicas.size() + " músicas recomendadas!");

        } else if (criterio.equals("recentes")) {
            // Adiciona as últimas músicas cadastradas (até 5)
            int limite = Math.min(5, todasMusicas.size());
            for (int i = todasMusicas.size() - 1; i >= todasMusicas.size() - limite; i--) {
                musicas.add(todasMusicas.get(i));
            }
            System.out.println("✅ Playlist \"" + nome + "\" atualizada com as " + musicas.size() + " músicas adicionadas recentemente!");
        }
    }

}
