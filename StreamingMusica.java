import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<Musica> musicas = new ArrayList<>();

    // Usando construtor parametrizado do Usuario
    static Usuario usuario = new Usuario("Usuário");

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        adicionarMusicasTeste();

        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("\n🎵 Até logo! 🎵");
        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. Editar música");
        System.out.println("7. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarMusica();
                break;
            case 2:
                listarMusicas();
                break;
            case 3:
                buscarMusica();
                break;
            case 4:
                criarPlaylist();
                break;
            case 5:
                gerenciarPlaylists();
                break;
            case 6:
                editarMusica();
                break;
            case 7:
                mostrarEstatisticas();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida!");
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

        // Usando construtor parametrizado — validações ocorrem dentro da classe
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
            case 1:
                buscarPorTitulo();
                break;
            case 2:
                buscarPorArtista();
                break;
            case 3:
                buscarPorGenero();
                break;
            default:
                System.out.println("Opção inválida.");
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

        if (!encontrou) {
            System.out.println("Nenhuma música encontrada.");
        }
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

        if (!encontrou) {
            System.out.println("Nenhuma música encontrada.");
        }
    }

    public static void buscarPorGenero() {
        System.out.print("Digite o gênero: ");
        String busca = scanner.nextLine().toLowerCase();

        boolean encontrou = false;
        for (int i = 0; i < musicas.size(); i++) {
            // Usando getter getGenero() em vez de acesso direto
            if (musicas.get(i).getGenero().toLowerCase().contains(busca)) {
                musicas.get(i).exibir();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma música encontrada.");
        }
    }

    // ==================== CRIAR PLAYLIST ====================

    public static void criarPlaylist() {
        System.out.println("\n--- CRIAR PLAYLIST ---");
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();

        try {
            usuario.criarPlaylist(nome);
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
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    usuario.listarPlaylists();
                    break;
                case 2:
                    adicionarMusicaNaPlaylist();
                    break;
                case 3:
                    removerMusicaDaPlaylist();
                    break;
                case 4:
                    exibirDetalhesPlaylist();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    public static void adicionarMusicaNaPlaylist() {
        if (usuario.getPlaylists().size() == 0) {
            System.out.println("Você não tem playlists. Crie uma primeiro.");
            return;
        }

        if (musicas.size() == 0) {
            System.out.println("Não há músicas cadastradas no sistema.");
            return;
        }

        usuario.listarPlaylists();
        System.out.print("Escolha o índice da playlist: ");
        int indicePlaylist = lerOpcao();

        Playlist playlist = usuario.getPlaylist(indicePlaylist);
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
        if (usuario.getPlaylists().size() == 0) {
            System.out.println("Você não tem playlists.");
            return;
        }

        usuario.listarPlaylists();
        System.out.print("Escolha o índice da playlist: ");
        int indicePlaylist = lerOpcao();

        Playlist playlist = usuario.getPlaylist(indicePlaylist);
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
        if (usuario.getPlaylists().size() == 0) {
            System.out.println("Você não tem playlists.");
            return;
        }

        usuario.listarPlaylists();
        System.out.print("Escolha o índice da playlist: ");
        int indice = lerOpcao();

        Playlist playlist = usuario.getPlaylist(indice);
        if (playlist == null) {
            System.out.println("Playlist não encontrada.");
            return;
        }

        playlist.listarMusicas();

        int duracaoTotal = playlist.getDuracaoTotal();
        int min = duracaoTotal / 60;
        int seg = duracaoTotal % 60;
        System.out.println("\nDuração total: " + String.format("%d:%02d", min, seg));
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
        System.out.println("\n--- ESTATÍSTICAS ---");

        if (musicas.size() == 0) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        int somaDuracao = 0;
        for (int i = 0; i < musicas.size(); i++) {
            // Usando getter getDuracaoSegundos() em vez de acesso direto
            somaDuracao += musicas.get(i).getDuracaoSegundos();
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
        // Usando getter getPlaylists() em vez de acesso direto
        System.out.println("Total de playlists: " + usuario.getPlaylists().size());

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

    // ==================== DADOS DE TESTE ====================

    public static void adicionarMusicasTeste() {
        // Usando construtor parametrizado — forma correta no CP3
        Musica m1 = new Musica("Bohemian Rhapsody", "Queen", 354, "Rock");
        musicas.add(m1);

        Musica m2 = new Musica("Billie Jean", "Michael Jackson", 293, "Pop");
        musicas.add(m2);
    }

}
