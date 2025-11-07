package codigo;

import codigo.model.TGrafo;
import codigo.model.VooComEmissao;
import codigo.service.FlightCarbonService;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class main {
    
    // Carrega variáveis do arquivo .env (se existir) ou do ambiente
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    
    // Credenciais da API Amadeus (via .env ou variáveis de ambiente)
    private static final String API_KEY = dotenv.get("AMADEUS_API_KEY", System.getenv("AMADEUS_API_KEY"));
    private static final String API_SECRET = dotenv.get("AMADEUS_API_SECRET", System.getenv("AMADEUS_API_SECRET"));
    
    private void menu() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║        CARBON FLIGHT - Sistema de Voos Sustentáveis   ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println("1. Ler dados do arquivo grafo.txt");
        System.out.println("2. Gravar dados no arquivo grafo.txt");
        System.out.println("3. Inserir vértice");
        System.out.println("4. Inserir aresta");
        System.out.println("5. Remover vértice");
        System.out.println("6. Remover aresta");
        System.out.println("7. Mostrar conteúdo do arquivo");
        System.out.println("8. Mostrar grafo");
        System.out.println("9. Apresentar a conexidade do grafo e o reduzido");
        System.out.println("10. 🌱 BUSCAR VOOS POR EMISSÃO DE CARBONO 🌱");
        System.out.println("0. Encerrar a aplicação"); 
    }
    public static void main(String[] args) throws IOException {
        main app = new main();
        Scanner s = new Scanner(System.in);
        int opcao = -1;
        int numVertices = 0;
        int numArestas = 0;
        TGrafo g = new TGrafo(numVertices);

        while (opcao != 0) {
            app.menu();
            System.out.print("\nEscolha uma opção: ");
            opcao = s.nextInt();
            s.nextLine(); // Limpa buffer
            switch(opcao) {
                //Ler dados do arquivo
                case 1:
                    BufferedReader br = new BufferedReader(new FileReader("/Users/francisco/Documents/ProjetoCarbonFlight/codigo/data/grafoEntrada.txt"));
                    br.readLine(); // Ignora a primeira linha
                    numVertices = Integer.parseInt(br.readLine().trim());
                    g = new TGrafo(numVertices); // Inicializa com o número correto de vértices
                    TGrafo gArquivo = TGrafo.fromArquivo(0, numVertices, br, g); // O método vai ler os vértices e arestas
                    gArquivo.show();
                    break;
                    
                //gravar dados no arquivo
                case 2:
                    g.toArquivo("/Users/francisco/Documents/ProjetoCarbonFlight/codigo/data/grafoSaida.txt", g);
                    System.out.println("Dados gravados no arquivo grafo.txt");
                    break;

                //inserir vertice
                case 3:
                    g.insereV();
                    System.out.println("Vertice Inserido");
                    break;

                //inserir aresta
                case 4:
                    System.out.print("Digite o vértice de origem: ");
                    int vOrigem = s.nextInt();
                    System.out.print("Digite o vértice de destino: ");
                    int vDestino = s.nextInt();
                    System.out.print("Digite o peso da aresta: ");
                    int peso = s.nextInt();
                    g.insereA(vOrigem, vDestino, peso);
                    System.out.println("Aresta Inserida");
                    break;

                //remove vertice
                case 5:
                    System.out.print("Digite o vértice a ser removido: ");
                    int vRemover = s.nextInt();
                    g.removeV(vRemover);
                    System.out.println("Vértice Removido");
                    break;

                //remover aresta
                case 6:
                    System.out.print("Digite o vértice de origem da aresta a ser removida: ");
                    int vOrigemRemover = s.nextInt();
                    System.out.print("Digite o vértice de destino da aresta a ser removida: ");
                    int vDestinoRemover = s.nextInt();
                    g.removeA(vOrigemRemover, vDestinoRemover);
                    System.out.println("Aresta Removida");
                    break;

                //mostrar conteudo arquivo
                case 7:
                    TGrafo.showArquivo("/Users/francisco/Documents/ProjetoCarbonFlight/codigo/data/grafoEntrada.txt");
                    break;

                //mostrar grafo
                case 8:
                    g.show();
                    break;

                //Conexidade e Reduzido
                case 9:
                    TGrafo gReduzido = g.grafoReduzido();
                    System.out.println("Grafo Reduzido:");
                    gReduzido.show();
                    System.out.println("Categoria de Conexidade: " + g.categoriaConexidade());
                    break;
                    
                // Buscar voos por emissão de carbono
                case 10:
                    buscarVoosSustentaveis(s);
                    break;
                    
                //Sair
                case 0:
                    System.out.println("\n👋 Encerrando o sistema. Até logo!");
                    break;
                    
                default:
                    System.out.println("\n❌ Opção inválida! Tente novamente.");
            }
        }
        s.close();
    }
    
    /**
     * Método principal para buscar voos sustentáveis
     */
    public static void buscarVoosSustentaveis(Scanner s) {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║         BUSCA DE VOOS SUSTENTÁVEIS                     ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
        
        // Solicita informações do usuário
        System.out.print("Aeroporto de origem (código IATA, ex: GRU): ");
        String origem = s.nextLine().toUpperCase();
        
        System.out.print("Aeroporto de destino (código IATA, ex: JFK): ");
        String destino = s.nextLine().toUpperCase();
        
        System.out.print("Data de partida (YYYY-MM-DD, ex: 2025-12-15): ");
        String data = s.nextLine();
        
        System.out.print("Número de adultos: ");
        int adultos = s.nextInt();
        s.nextLine(); // Limpa buffer
        
        // Valida credenciais da API
        if (API_KEY == null || API_SECRET == null || API_KEY.isEmpty() || API_SECRET.isEmpty()) {
            System.out.println("\n❌ ERRO: Credenciais da API Amadeus não configuradas!");
            System.out.println("\n📋 OPÇÃO 1: Criar arquivo .env (Recomendado)");
            System.out.println("  1. Crie um arquivo .env na raiz do projeto");
            System.out.println("  2. Adicione as linhas:");
            System.out.println("     AMADEUS_API_KEY=sua_chave_aqui");
            System.out.println("     AMADEUS_API_SECRET=seu_secret_aqui");
            System.out.println("\n📋 OPÇÃO 2: Variáveis de ambiente");
            System.out.println("  export AMADEUS_API_KEY=\"sua_chave_aqui\"");
            System.out.println("  export AMADEUS_API_SECRET=\"seu_secret_aqui\"");
            System.out.println("\n📖 Veja instruções completas em: CREDENCIAIS.md");
            System.out.println("🌐 Obtenha suas credenciais em: https://developers.amadeus.com\n");
            return;
        }
        
        // Cria o serviço e busca voos
        FlightCarbonService service = new FlightCarbonService(API_KEY, API_SECRET);
        List<VooComEmissao> voos = service.buscarVoosOrdenadosPorEmissao(origem, destino, data, adultos);
        
        if (voos.isEmpty()) {
            return;
        }
        
        // Exibe resultados
        service.exibirResultados(voos);
        
        // Loop para ver detalhes de múltiplos voos
        while (true) {
            System.out.print("\nDeseja ver detalhes de algum voo? (1-" + voos.size() + ", 0 para voltar): ");
            int escolha = s.nextInt();
            s.nextLine(); // Limpa buffer
            
            if (escolha == 0) {
                break; // Sai do loop
            }
            
            if (escolha > 0 && escolha <= voos.size()) {
                service.mostrarDetalhesVoo(voos, escolha - 1);
            } else {
                System.out.println("❌ Opção inválida! Escolha entre 1 e " + voos.size() + " ou 0 para voltar.");
            }
        }
        
        System.out.println("\n✅ Rotas adicionadas ao grafo com sucesso!");
        System.out.println("Pressione ENTER para continuar...");
        s.nextLine();
    }
}
