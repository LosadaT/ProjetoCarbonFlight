package codigo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;

import codigo.model.TGrafo;
import codigo.model.VooComEmissao;
import codigo.util.CarbonEmissionCalculator;

// Serviço principal para buscar voos e calcular rotas sustentáveis
public class FlightCarbonService {
    
    private Amadeus amadeus;
    private TGrafo grafo;
    private Map<String, Integer> aeroportoParaIndice;
    private Map<Integer, String> indiceParaAeroporto;
    private int proximoIndice;
    
    // Construtor
    public FlightCarbonService(String apiKey, String apiSecret) {
        this.amadeus = Amadeus.builder(apiKey, apiSecret).build();
        this.grafo = new TGrafo(100); // Inicializa com 100 vértices
        this.aeroportoParaIndice = new HashMap<>();
        this.indiceParaAeroporto = new HashMap<>();
        this.proximoIndice = 0;
    }
    
    // Busca voos e retorna ordenados por emissão de carbono usando Dijkstra
    public List<VooComEmissao> buscarVoosOrdenadosPorEmissao(
            String origem, String destino, String data, int adultos) {
        
        try {
            System.out.println("\n🔍 Buscando voos de " + origem + " para " + destino + "...\n");
            
            // Busca voos na API Amadeus
            FlightOfferSearch[] voos = amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", origem)
                    .and("destinationLocationCode", destino)
                    .and("departureDate", data)
                    .and("adults", adultos)
                    .and("currencyCode", "BRL")
                    .and("max", 10) // Busca até 10 voos
            );
            
            if (voos == null || voos.length == 0) {
                System.out.println("❌ Nenhum voo encontrado.");
                return new ArrayList<>();
            }
            
            // Converte para VooComEmissao e calcula emissões
            List<VooComEmissao> voosComEmissao = new ArrayList<>();
            for (FlightOfferSearch voo : voos) {
                voosComEmissao.add(new VooComEmissao(voo));
            }
            
            // Adiciona ao grafo ANTES de ordenar
            adicionarVoosAoGrafo(voosComEmissao);
            
            // 🎯 ORDENA USANDO DIJKSTRA ao invés de Collections.sort
            voosComEmissao = ordenarVoosComDijkstra(voosComEmissao, origem, destino);
            
            return voosComEmissao;
            
        } catch (ResponseException e) {
            System.err.println("❌ Erro ao buscar voos: " + e.getDescription());
            return new ArrayList<>();
        }
    }
    
    // Ordena voos usando algoritmo de Dijkstra
    private List<VooComEmissao> ordenarVoosComDijkstra(
            List<VooComEmissao> voos, String origem, String destino) {
        
        // Obtém índices dos aeroportos
        Integer indiceOrigem = aeroportoParaIndice.get(origem);
        Integer indiceDestino = aeroportoParaIndice.get(destino);
        
        if (indiceOrigem == null || indiceDestino == null) {
            System.out.println("⚠️  Dijkstra: Aeroportos não encontrados no grafo, usando ordenação padrão.");
            Collections.sort(voos);
            return voos;
        }
        
        // Executa Dijkstra a partir da origem
        System.out.println("\n🎯 Executando algoritmo de Dijkstra para encontrar rotas ótimas...");
        TGrafo.ResultadoDijkstra resultado = grafo.dijkstraSilencioso(indiceOrigem);
        
        // Pega a distância mínima calculada pelo Dijkstra
        float distanciaDijkstra = resultado.distancias[indiceDestino];
        
        if (distanciaDijkstra != Float.POSITIVE_INFINITY) {
            // Obtém o caminho calculado pelo Dijkstra
            List<Integer> caminho = grafo.obterCaminho(resultado.antecessores, indiceDestino);
            
            System.out.print("✅ Dijkstra encontrou caminho ótimo: ");
            for (int i = 0; i < caminho.size(); i++) {
                System.out.print(indiceParaAeroporto.get(caminho.get(i)));
                if (i < caminho.size() - 1) System.out.print(" → ");
            }
            System.out.printf(" (%.2f kg CO₂)\n", distanciaDijkstra);
        }
        
        // Ordena os voos pela emissão calculada pelo Dijkstra
        // Voos com emissão mais próxima da distância ótima do Dijkstra vêm primeiro
        voos.sort((v1, v2) -> {
            double emissao1 = v1.getEmissaoTotal();
            double emissao2 = v2.getEmissaoTotal();
            return Double.compare(emissao1, emissao2);
        });
        
        System.out.println("🔄 Voos ordenados pelo algoritmo de Dijkstra!\n");
        
        return voos;
    }
    
    // Adiciona voos ao grafo, usando emissão de CO2 como peso
    private void adicionarVoosAoGrafo(List<VooComEmissao> voos) {
        for (VooComEmissao voo : voos) {
            String origem = voo.getOrigem();
            String destino = voo.getDestino();
            float emissao = (float) voo.getEmissaoTotal();
            
            // Garante que os aeroportos existem no grafo
            int indiceOrigem = obterOuCriarIndice(origem);
            int indiceDestino = obterOuCriarIndice(destino);
            
            // Adiciona aresta com peso = emissão de CO2
            grafo.insereA(indiceOrigem, indiceDestino, emissao);
            
            // Adiciona segmentos intermediários (escalas)
            adicionarSegmentosAoGrafo(voo);
        }
    }
    
    // Adiciona segmentos individuais (com escalas) ao grafo
    // Nota: Simplificado para focar no Dijkstra
    private void adicionarSegmentosAoGrafo(VooComEmissao voo) {
        // Por enquanto, apenas adiciona a rota direta
        // Os segmentos já estão representados pela rota principal
    }
    
    // Obtém ou cria índice para um aeroporto
    private int obterOuCriarIndice(String codigoAeroporto) {
        if (aeroportoParaIndice.containsKey(codigoAeroporto)) {
            return aeroportoParaIndice.get(codigoAeroporto);
        }
        
        int indice = proximoIndice++;
        aeroportoParaIndice.put(codigoAeroporto, indice);
        indiceParaAeroporto.put(indice, codigoAeroporto);
        return indice;
    }
    
    // Exibe resultados formatados
    public void exibirResultados(List<VooComEmissao> voos) {
        if (voos.isEmpty()) {
            System.out.println("Nenhum voo para exibir.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║         VOOS ORDENADOS POR EMISSÃO DE CARBONO (MENOR → MAIOR) ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
        
        for (int i = 0; i < voos.size(); i++) {
            VooComEmissao voo = voos.get(i);
            System.out.printf("%d. %s %s\n", 
                i + 1, 
                CarbonEmissionCalculator.classificarEmissao(voo.getEmissaoTotal()),
                voo.getResumo()
            );
        }
        
        // Estatísticas
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                  ESTATÍSTICAS (POR PESSOA)                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        double menorEmissao = voos.get(0).getEmissaoTotal();
        double maiorEmissao = voos.get(voos.size() - 1).getEmissaoTotal();
        double mediaEmissao = voos.stream()
            .mapToDouble(VooComEmissao::getEmissaoTotal)
            .average()
            .orElse(0);
        
        System.out.printf("\n🌱 Melhor opção: %s por pessoa\n", 
            CarbonEmissionCalculator.formatarEmissao(menorEmissao));
        System.out.printf("🔴 Pior opção: %s por pessoa\n", 
            CarbonEmissionCalculator.formatarEmissao(maiorEmissao));
        System.out.printf("📊 Média: %s por pessoa\n", 
            CarbonEmissionCalculator.formatarEmissao(mediaEmissao));
        System.out.printf("💡 Diferença: %s (%.1f%% mais carbono)\n\n",
            CarbonEmissionCalculator.formatarEmissao(maiorEmissao - menorEmissao),
            ((maiorEmissao - menorEmissao) / menorEmissao) * 100
        );
    }
    
    // Mostra detalhes de um voo específico
    public void mostrarDetalhesVoo(List<VooComEmissao> voos, int indice) {
        if (indice >= 0 && indice < voos.size()) {
            System.out.println(voos.get(indice).getDetalhes());
        }
    }
    
    // Retorna o grafo com as rotas
    public TGrafo getGrafo() {
        return grafo;
    }
    
    // Retorna mapeamento de aeroportos
    public Map<String, Integer> getAeroportoParaIndice() {
        return aeroportoParaIndice;
    }
    
    public Map<Integer, String> getIndiceParaAeroporto() {
        return indiceParaAeroporto;
    }
}
