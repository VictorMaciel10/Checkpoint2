import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    // ==================== ATRIBUTOS DO SISTEMA ====================

    static ArrayList<Musica> musicas = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>(); // ArrayList polimórfico
    static Usuario usuarioLogado = null;
    static Scanner scanner = new Scanner(System.in);

    // ==================== MAIN ====================

    public static void main(String[] args) {
        adicionarMusicasTeste();

        System.out.println("\n==== BEM-VINDO AO STREAMING ====");

        int opcao;
        do {
            exibirMenuInicial();
            opcao = lerOpcao();
            processarMenuInicial(opcao);
        } while (opcao != 0);

        System.out.println("\n🎵 Até logo! 🎵");
        scanner.close();
    }

    // ==================== MENU INICIAL (sem login) ====================

    public static void exibirMenuInicial() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Criar novo usuário");
        System.out.println("2. Login");
        System.out.println("3. Listar usuários");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    public static void processarMenuInicial(int opcao) {
        switch (opcao) {
            case 1: criarUsuario(); break;
            case 2: fazerLogin(); break;
            case 3: listarUsuarios(); break;
            case 0: break;
            default: System.out.println("Opção inválida!");
        }
    }

    // ==================== CRIAR USUÁRIO ====================

    public static void criarUsuario() {
        System.out.println("\n--- CRIAR NOVO USUÁRIO ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("\nTipo de conta:");
        System.out.println("1. Free");
        System.out.println("2. Premium");
        System.out.print("Escolha: ");
        int tipo = lerOpcao();

        try {
            if (tipo == 2) {
                System.out.print("Plano (Mensal/Anual/Familiar): ");
                String plano = scanner.nextLine();
                usuarios.add(new UsuarioPremium(nome, email, plano));
                System.out.println("✅ Usuário Premium criado!");
            } else {
                usuarios.add(new UsuarioFree(nome, email));
                System.out.println("✅ Usuário Free criado!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar usuário: " + e.getMessage());
        }
    }

    // ==================== LOGIN ====================

    public static void fazerLogin() {
        if (usuarios.size() == 0) {
            System.out.println("Nenhum usuário cadastrado. Crie um primeiro.");
            return;
        }

        System.out.println("\n--- LOGIN ---");
        listarUsuarios();
        System.out.print("Escolha o número do usuário: ");
        int indice = lerOpcao() - 1;

        if (indice < 0 || indice >= usuarios.size()) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        usuarioLogado = usuarios.get(indice);

        // Uso de instanceof para identificar o tipo real do usuário
        if (usuarioLogado instanceof UsuarioPremium) {
            UsuarioPremium premium = (UsuarioPremium) usuarioLogado; // downcasting
            System.out.println("✅ Login realizado: " + premium.getNome() + " (Premium - " + premium.getTipoPlano() + ")");
        } else if (usuarioLogado instanceof UsuarioFree) {
            System.out.println("✅ Login realizado: " + usuarioLogado.getNome() + " (Free)");
        }

        // Entra no menu principal do usuário logado
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("👋 Logout realizado.");
        usuarioLogado = null;
    }

    // ==================== LISTAR USUÁRIOS ====================

    public static void listarUsuarios() {
        System.out.println("\n--- USUÁRIOS CADASTRADOS ---");
        if (usuarios.size() == 0) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            String tipo;
            // Uso de instanceof para identificar o tipo do usuário polimorficamente
            if (u instanceof UsuarioPremium) {
                UsuarioPremium p = (UsuarioPremium) u; // downcasting
                tipo = "Premium (" + p.getTipoPlano() + ")";
            } else {
                tipo = "Free";
            }
            System.out.println((i + 1) + ". " + u.getNome() + " - " + tipo);
        }
    }

    // ==================== MENU PRINCIPAL (com usuário logado) ====================

    public static void exibirMenuPrincipal() {
        if (usuarioLogado instanceof UsuarioPremium) {
            System.out.println("\n=== STREAMING - PREMIUM ===");
            System.out.println("1. Reproduzir música (Alta Qualidade)");
            System.out.println("2. Ver histórico");
            System.out.println("3. Criar playlist (ilimitado)");
            System.out.println("4. Gerenciar playlists");
            System.out.println("5. Baixar música");
            System.out.println("6. Ver músicas baixadas");
            System.out.println("7. Playlists automáticas");
            System.out.println("8. Cadastrar música");
            System.out.println("9. Listar músicas");
            System.out.println("10. Buscar música");
            System.out.println("11. Editar música");
            System.out.println("12. Exibir estatísticas");
            System.out.println("0. Logout");
        } else {
            System.out.println("\n=== STREAMING - FREE ===");
            System.out.println("1. Reproduzir música");
            System.out.println("2. Ver histórico");
            System.out.println("3. Criar playlist (máx. 3)");
            System.out.println("4. Gerenciar playlists");
            System.out.println("5. 💎 Fazer upgrade para Premium");
            System.out.println("6. Playlists automáticas");
            System.out.println("7. Cadastrar música");
            System.out.println("8. Listar músicas");
            System.out.println("9. Buscar música");
            System.out.println("10. Editar música");
            System.out.println("11. Exibir estatísticas");
            System.out.println("0. Logout");
        }
        System.out.print("Escolha: ");
    }

    public static void processarOpcao(int opcao) {
        if (usuarioLogado instanceof UsuarioPremium) {
            processarOpcaoPremium(opcao);
        } else {
            processarOpcaoFree(opcao);
        }
    }

    public static void processarOpcaoFree(int opcao) {
        switch (opcao) {
            case 1: reproduzirMusicaMenu(); break;
            case 2: usuarioLogado.exibirHistorico(); break;
            case 3: criarPlaylist(); break;
            case 4: gerenciarPlaylists(); break;
            case 5: fazerUpgradePremium(); break;
            case 6: menuPlaylistsAutomaticas(); break;
            case 7: cadastrarMusica(); break;
            case 8: listarMusicas(); break;
            case 9: buscarMusica(); break;
            case 10: editarMusica(); break;
            case 11: mostrarEstatisticas(); break;
            case 0: break;
            default: System.out.println("Opção inválida!");
        }
    }

    public static void processarOpcaoPremium(int opcao) {
        UsuarioPremium premium = (UsuarioPremium) usuarioLogado; // downcasting
        switch (opcao) {
            case 1: reproduzirMusicaMenu(); break;
            case 2: usuarioLogado.exibirHistorico(); break;
            case 3: criarPlaylist(); break;
            case 4: gerenciarPlaylists(); break;
            case 5: baixarMusica(premium); break;
            case 6: premium.listarMusicasBaixadas(); break;
            case 7: menuPlaylistsAutomaticas(); break;
            case 8: cadastrarMusica(); break;
            case 9: listarMusicas(); break;
            case 10: buscarMusica(); break;
            case 11: editarMusica(); break;
            case 12: mostrarEstatisticas(); break;
            case 0: break;
            default: System.out.println("Opção inválida!");
        }
    }

    // ==================== REPRODUZIR MÚSICA ====================

    public static void reproduzirMusicaMenu() {
        if (musicas.size() == 0) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }
        listarMusicas();
        System.out.print("Escolha o índice da música: ");
        int indice = lerOpcao();
        if (indice < 0 || indice >= musicas.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        // Polimorfismo em ação — chama a versão correta do método dependendo do tipo real do usuário
        usuarioLogado.reproduzirMusica(musicas.get(indice));
    }

    // ==================== BAIXAR MÚSICA (PREMIUM) ====================

    public static void baixarMusica(UsuarioPremium premium) {
        if (musicas.size() == 0) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }
        listarMusicas();
        System.out.print("Escolha o índice da música para baixar: ");
        int indice = lerOpcao();
        if (indice < 0 || indice >= musicas.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        premium.baixarMusica(musicas.get(indice));
    }

    // ==================== UPGRADE PARA PREMIUM ====================

    public static void fazerUpgradePremium() {
        System.out.println("\n💎 UPGRADE PARA PREMIUM");
        System.out.println("Escolha o plano:");
        System.out.println("1. Mensal (R$ 19,90)");
        System.out.println("2. Anual (R$ 139,00)");
        System.out.println("3. Familiar (R$ 29,90)");
        System.out.print("Escolha: ");
        int tipoPlano = lerOpcao();
        String plano;
        switch (tipoPlano) {
            case 2: plano = "Anual"; break;
            case 3: plano = "Familiar"; break;
            default: plano = "Mensal"; break;
        }

        UsuarioPremium premium = new UsuarioPremium(usuarioLogado.getNome(), usuarioLogado.getEmail(), plano);

        // Migra playlists e histórico
        for (Playlist p : usuarioLogado.getPlaylists()) {
            premium.adicionarPlaylist(p);
        }
        for (Musica m : usuarioLogado.getHistoricoReproducao()) {
            premium.getHistoricoReproducao().add(m);
        }

        // Substitui o usuário na lista polimórfica
        int indice = usuarios.indexOf(usuarioLogado);
        if (indice >= 0) {
            usuarios.set(indice, premium);
        }

        usuarioLogado = premium;
        System.out.println("✅ Upgrade realizado com sucesso! Plano: " + plano);
    }

    // ==================== PLAYLISTS AUTOMÁTICAS ====================

    public static void menuPlaylistsAutomaticas() {
        System.out.println("\n=== PLAYLISTS AUTOMÁTICAS ===");
        System.out.println("1. Top 10 Mais Tocadas");
        System.out.println("2. Recomendadas para Você");
        System.out.println("3. Adicionadas Recentemente");
        System.out.print("Escolha: ");
        int opcao = lerOpcao();

        String criterio;
        String nomePlaylist;

        switch (opcao) {
            case 1:
                criterio = "top";
                nomePlaylist = "Top 10 Mais Tocadas";
                break;
            case 2:
                criterio = "recomendadas";
                nomePlaylist = "Recomendadas para Você";
                break;
            case 3:
                criterio = "recentes";
                nomePlaylist = "Adicionadas Recentemente";
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        System.out.println("🤖 Gerando playlist \"" + nomePlaylist + "\"...");

        // Cria e atualiza a PlaylistAutomatica (polimorfismo — é uma Playlist)
        PlaylistAutomatica automatica = new PlaylistAutomatica(nomePlaylist, criterio);
        automatica.atualizar(musicas);

        if (automatica.getQuantidadeMusicas() == 0) {
            System.out.println("Não foi possível gerar a playlist.");
            return;
        }

        // Adiciona ao usuário como uma Playlist (upcasting implícito)
        usuarioLogado.adicionarPlaylist(automatica);

        System.out.println("\nDeseja reproduzir agora? (1-Sim / outro-Não): ");
        int reproduzir = lerOpcao();
        if (reproduzir == 1) {
            automatica.reproduzir(); // Polimorfismo — chama a versão de PlaylistAutomatica
        }
    }

    // ==================== CADASTRAR MÚSICA ====================

    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Artista: ");
        String artista = scanner.nextLine();

        System.out.print("Duração em segundos: ");
        int duracao;
        try {
            duracao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Duração inválida.");
            return;
        }

        System.out.println("Gêneros disponíveis: Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica");
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        try {
            Musica novaMusica = new Musica(titulo, artista, duracao, genero);
            musicas.add(novaMusica);
            System.out.println("Música cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar música: " + e.getMessage());
        }
    }

    // ==================== LISTAR MÚSICAS ====================

    public static void listarMusicas() {
        System.out.println("\n--- MÚSICAS CADASTRADAS ---");

        if (musicas.size() == 0) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        for (int i = 0; i < musicas.size(); i++) {
            System.out.print("[" + i + "]");
            musicas.get(i).exibir();
        }
    }

    // ==================== BUSCAR MÚSICA ====================

    public static void buscarMusica() {
        System.out.println("\n--- BUSCAR MÚSICA ---");
        System.out.println("1. Buscar por título");
        System.out.println("2. Buscar por artista");
        System.out.println("3. Buscar por gênero");
        System.out.print("Escolha: ");

        int opcao = lerOpcao();

        switch (opcao) {
            case 1: buscarPorTitulo(); break;
            case 2: buscarPorArtista(); break;
            case 3: buscarPorGenero(); break;
            default: System.out.println("Opção inválida.");
        }
    }

    public static void buscarPorTitulo() {
        System.out.print("Digite o título: ");
        String busca = scanner.nextLine();
        boolean encontrou = false;
        for (int i = 0; i < musicas.size(); i++) {
            if (musicas.get(i).contemTitulo(busca)) {
                musicas.get(i).exibir();
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhuma música encontrada.");
    }

    public static void buscarPorArtista() {
        System.out.print("Digite o artista: ");
        String busca = scanner.nextLine();
        boolean encontrou = false;
        for (int i = 0; i < musicas.size(); i++) {
            if (musicas.get(i).contemArtista(busca)) {
                musicas.get(i).exibir();
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhuma música encontrada.");
    }

    public static void buscarPorGenero() {
        System.out.print("Digite o gênero: ");
        String busca = scanner.nextLine().toLowerCase();
        boolean encontrou = false;
        for (int i = 0; i < musicas.size(); i++) {
            if (musicas.get(i).getGenero().toLowerCase().contains(busca)) {
                musicas.get(i).exibir();
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhuma música encontrada.");
    }

    // ==================== CRIAR PLAYLIST ====================

    public static void criarPlaylist() {
        System.out.println("\n--- CRIAR PLAYLIST ---");
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        try {
            usuarioLogado.criarPlaylist(nome);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar playlist: " + e.getMessage());
        }
    }

    // ==================== GERENCIAR PLAYLISTS ====================

    public static void gerenciarPlaylists() {
        int opcao;
        do {
            System.out.println("\n=== GERENCIAR PLAYLISTS ===");
            System.out.println("1. Listar minhas playlists");
            System.out.println("2. Adicionar música a uma playlist");
            System.out.println("3. Remover música de uma playlist");
            System.out.println("4. Exibir detalhes de uma playlist");
            System.out.println("5. Reproduzir playlist");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            opcao = lerOpcao();

            switch (opcao) {
                case 1: usuarioLogado.listarPlaylists(); break;
                case 2: adicionarMusicaNaPlaylist(); break;
                case 3: removerMusicaDaPlaylist(); break;
                case 4: exibirDetalhesPlaylist(); break;
                case 5: reproduzirPlaylist(); break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    public static void adicionarMusicaNaPlaylist() {
        if (usuarioLogado.getPlaylists().size() == 0) {
            System.out.println("Você não tem playlists. Crie uma primeiro.");
            return;
        }
        if (musicas.size() == 0) {
            System.out.println("Não há músicas cadastradas no sistema.");
            return;
        }
        usuarioLogado.listarPlaylists();
        System.out.print("Escolha o índice da playlist: ");
        int indicePlaylist = lerOpcao();
        Playlist playlist = usuarioLogado.getPlaylist(indicePlaylist);
        if (playlist == null) {
            System.out.println("Playlist não encontrada.");
            return;
        }
        listarMusicas();
        System.out.print("Escolha o índice da música: ");
        int indiceMusica = lerOpcao();
        if (indiceMusica < 0 || indiceMusica >= musicas.size()) {
            System.out.println("Música não encontrada.");
            return;
        }
        try {
            playlist.adicionarMusica(musicas.get(indiceMusica));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void removerMusicaDaPlaylist() {
        if (usuarioLogado.getPlaylists().size() == 0) {
            System.out.println("Você não tem playlists.");
            return;
        }
        usuarioLogado.listarPlaylists();
        System.out.print("Escolha o índice da playlist: ");
        int indicePlaylist = lerOpcao();
        Playlist playlist = usuarioLogado.getPlaylist(indicePlaylist);
        if (playlist == null) {
            System.out.println("Playlist não encontrada.");
            return;
        }
        if (playlist.getQuantidadeMusicas() == 0) {
            System.out.println("A playlist está vazia.");
            return;
        }
        playlist.listarMusicas();
        System.out.print("Escolha o índice da música para remover: ");
        int indiceMusica = lerOpcao();
        playlist.removerMusica(indiceMusica);
    }

    public static void exibirDetalhesPlaylist() {
        if (usuarioLogado.getPlaylists().size() == 0) {
            System.out.println("Você não tem playlists.");
            return;
        }
        usuarioLogado.listarPlaylists();
        System.out.print("Escolha o índice da playlist: ");
        int indice = lerOpcao();
        Playlist playlist = usuarioLogado.getPlaylist(indice);
        if (playlist == null) {
            System.out.println("Playlist não encontrada.");
            return;
        }
        playlist.listarMusicas();
        int duracaoTotal = playlist.getDuracaoTotal();
        int min = duracaoTotal / 60;
        int seg = duracaoTotal % 60;
        System.out.println("\nDuração total: " + String.format("%d:%02d", min, seg));

        // Uso de instanceof para exibir detalhes específicos de PlaylistAutomatica
        if (playlist instanceof PlaylistAutomatica) {
            PlaylistAutomatica automatica = (PlaylistAutomatica) playlist; // downcasting
            System.out.println("🤖 Tipo: Automática | Critério: " + automatica.getCriterio());
        }
    }

    public static void reproduzirPlaylist() {
        if (usuarioLogado.getPlaylists().size() == 0) {
            System.out.println("Você não tem playlists.");
            return;
        }
        usuarioLogado.listarPlaylists();
        System.out.print("Escolha o índice da playlist: ");
        int indice = lerOpcao();
        Playlist playlist = usuarioLogado.getPlaylist(indice);
        if (playlist == null) {
            System.out.println("Playlist não encontrada.");
            return;
        }
        // Polimorfismo — chama reproduzir() da subclasse correta
        playlist.reproduzir();
    }

    // ==================== EDITAR MÚSICA ====================

    public static void editarMusica() {
        System.out.println("\n--- EDITAR MÚSICA ---");
        if (musicas.size() == 0) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }
        listarMusicas();
        System.out.print("Escolha o índice da música para editar: ");
        int indice = lerOpcao();
        if (indice < 0 || indice >= musicas.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        Musica musica = musicas.get(indice);
        System.out.println("\nO que deseja editar?");
        System.out.println("1. Título (atual: " + musica.getTitulo() + ")");
        System.out.println("2. Artista (atual: " + musica.getArtista() + ")");
        System.out.println("3. Duração (atual: " + musica.getDuracaoFormatada() + ")");
        System.out.println("4. Gênero (atual: " + musica.getGenero() + ")");
        System.out.print("Escolha: ");
        int campo = lerOpcao();
        try {
            switch (campo) {
                case 1:
                    System.out.print("Novo título: ");
                    musica.setTitulo(scanner.nextLine());
                    System.out.println("Título atualizado!");
                    break;
                case 2:
                    System.out.print("Novo artista: ");
                    musica.setArtista(scanner.nextLine());
                    System.out.println("Artista atualizado!");
                    break;
                case 3:
                    System.out.print("Nova duração em segundos: ");
                    try {
                        int novaDuracao = Integer.parseInt(scanner.nextLine());
                        musica.setDuracaoSegundos(novaDuracao);
                        System.out.println("Duração atualizada!");
                    } catch (NumberFormatException e) {
                        System.out.println("Duração inválida.");
                    }
                    break;
                case 4:
                    System.out.println("Gêneros disponíveis: Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica");
                    System.out.print("Novo gênero: ");
                    musica.setGenero(scanner.nextLine());
                    System.out.println("Gênero atualizado!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao editar: " + e.getMessage());
        }
    }

    // ==================== ESTATÍSTICAS ====================

    public static void mostrarEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");

        // --- Estatísticas de usuários (percorre ArrayList polimórfico) ---
        int totalFree = 0;
        int totalPremium = 0;
        int reproducoesFree = 0;
        int reproducoesPremium = 0;
        int totalAnuncios = 0;

        for (Usuario u : usuarios) {
            // Uso de instanceof para diferenciar tipos
            if (u instanceof UsuarioPremium) {
                totalPremium++;
                reproducoesPremium += u.getHistoricoReproducao().size();
            } else if (u instanceof UsuarioFree) {
                totalFree++;
                UsuarioFree free = (UsuarioFree) u; // downcasting
                reproducoesFree += free.getContadorReproducoes();
                totalAnuncios += free.getContadorReproducoes() / 3;
            }
        }

        int totalUsuarios = totalFree + totalPremium;
        int totalReproducoes = reproducoesFree + reproducoesPremium;

        System.out.println("Total de usuários: " + totalUsuarios);
        System.out.println("- Free: " + totalFree + " usuário(s)");
        System.out.println("- Premium: " + totalPremium + " usuário(s)");

        System.out.println("\nReproduções totais: " + totalReproducoes);
        if (totalReproducoes > 0) {
            int pctFree = (reproducoesFree * 100) / totalReproducoes;
            int pctPremium = (reproducoesPremium * 100) / totalReproducoes;
            System.out.println("- Free: " + reproducoesFree + " reproduções (" + pctFree + "%)");
            System.out.println("- Premium: " + reproducoesPremium + " reproduções (" + pctPremium + "%)");
        }

        System.out.println("\nAnúncios exibidos: " + totalAnuncios);

        // --- Estatísticas do usuário logado ---
        System.out.println("\n--- SEU PERFIL ---");
        System.out.println("Usuário: " + usuarioLogado.getNome());
        System.out.println("Total de playlists: " + usuarioLogado.getPlaylists().size());
        System.out.println("Músicas no histórico: " + usuarioLogado.getHistoricoReproducao().size());

        if (usuarioLogado instanceof UsuarioFree) {
            UsuarioFree free = (UsuarioFree) usuarioLogado; // downcasting
            System.out.println("Tipo de conta: Free");
            System.out.println("Reproduções realizadas: " + free.getContadorReproducoes());
            System.out.println("Playlists disponíveis: " + free.getPlaylists().size() + "/3");
        } else if (usuarioLogado instanceof UsuarioPremium) {
            UsuarioPremium premium = (UsuarioPremium) usuarioLogado; // downcasting
            System.out.println("Tipo de conta: Premium (" + premium.getTipoPlano() + ")");
            System.out.println("Músicas baixadas: " + premium.getMusicasBaixadas().size());
        }

        // --- Estatísticas de músicas ---
        if (musicas.size() > 0) {
            System.out.println("\n--- MÚSICAS DO SISTEMA ---");
            int somaDuracao = 0;
            for (Musica m : musicas) {
                somaDuracao += m.getDuracaoSegundos();
            }
            double media = (double) somaDuracao / musicas.size();
            int mediaInt = (int) media;
            int minTotal = somaDuracao / 60;
            int segTotal = somaDuracao % 60;
            int minMedia = mediaInt / 60;
            int segMedia = mediaInt % 60;

            System.out.println("Total de músicas: " + musicas.size());
            System.out.println("Duração total: " + String.format("%d:%02d", minTotal, segTotal));
            System.out.println("Duração média: " + String.format("%d:%02d", minMedia, segMedia));

            // Gênero mais comum
            String generoMaisComum = "";
            int maiorContagem = 0;
            for (int i = 0; i < musicas.size(); i++) {
                String generoAtual = musicas.get(i).getGenero();
                int contagem = 0;
                for (int j = 0; j < musicas.size(); j++) {
                    if (musicas.get(j).getGenero().equalsIgnoreCase(generoAtual)) {
                        contagem++;
                    }
                }
                if (contagem > maiorContagem) {
                    maiorContagem = contagem;
                    generoMaisComum = generoAtual;
                }
            }
            System.out.println("Gênero mais comum: " + generoMaisComum);
        }
    }

    // ==================== UTILITÁRIOS ====================

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // ==================== DADOS DE TESTE ====================

    public static void adicionarMusicasTeste() {
        musicas.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        musicas.add(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
        musicas.add(new Musica("So What", "Miles Davis", 560, "Jazz"));
        musicas.add(new Musica("Lose Yourself", "Eminem", 326, "Hip-Hop"));
    }

}
