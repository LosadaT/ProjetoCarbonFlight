package codigo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.amadeus.resources.FlightOfferSearch;

import codigo.util.CarbonEmissionCalculator;

// Classe para representar um voo com informações de emissão de carbono
public class VooComEmissao implements Comparable<VooComEmissao> {
    private FlightOfferSearch voo;
    private double emissaoTotal; // kg CO2
    private double distanciaTotal; // km
    private String origem;
    private String destino;
    
    // Mapeamento de códigos IATA para nomes de companhias
    private static final Map<String, String> COMPANHIAS = new HashMap<>();
    static {
        COMPANHIAS.put("LA", "LATAM");
        COMPANHIAS.put("AV", "Avianca");
        COMPANHIAS.put("CM", "Copa Airlines");
        COMPANHIAS.put("AA", "American Airlines");
        COMPANHIAS.put("DL", "Delta");
        COMPANHIAS.put("UA", "United");
        COMPANHIAS.put("AC", "Air Canada");
        COMPANHIAS.put("TP", "TAP Portugal");
        COMPANHIAS.put("AF", "Air France");
        COMPANHIAS.put("KL", "KLM");
        COMPANHIAS.put("IB", "Iberia");
        COMPANHIAS.put("BA", "British Airways");
        COMPANHIAS.put("LH", "Lufthansa");
        COMPANHIAS.put("G3", "Gol");
        COMPANHIAS.put("AD", "Azul");
    }
    
    public VooComEmissao(FlightOfferSearch voo) {
        this.voo = voo;
        calcularEmissao();
    }
    
    // Calcula a emissão total do voo baseado nos segmentos
    // PRIORIDADE: Usa dados reais da API Amadeus quando disponíveis
    private void calcularEmissao() {
        if (voo.getItineraries() == null || voo.getItineraries().length == 0) {
            this.emissaoTotal = 0;
            this.distanciaTotal = 0;
            return;
        }
        
        FlightOfferSearch.Itinerary itinerario = voo.getItineraries()[0];
        FlightOfferSearch.SearchSegment[] segmentos = itinerario.getSegments();
        
        // Pega origem e destino
        this.origem = segmentos[0].getDeparture().getIataCode();
        this.destino = segmentos[segmentos.length - 1].getArrival().getIataCode();
        
        // Tenta usar dados reais de CO2 da API Amadeus
        double emissaoAcumulada = 0;
        double distanciaAcumulada = 0;
        boolean usouDadosReais = false;
        
        for (int i = 0; i < segmentos.length; i++) {
            FlightOfferSearch.SearchSegment segmento = segmentos[i];
            String origemSegmento = segmento.getDeparture().getIataCode();
            String destinoSegmento = segmento.getArrival().getIataCode();
            
            // Pega o modelo do avião da API
            String codigoAviao = null;
            if (segmento.getAircraft() != null) {
                codigoAviao = segmento.getAircraft().getCode();
            }
            
            // 🌱 PRIORIDADE 1: Usar dados reais de CO2 da API Amadeus
            FlightOfferSearch.Co2Emissions[] co2Emissions = segmento.getCo2Emissions();
            if (co2Emissions != null && co2Emissions.length > 0) {
                // Pega a emissão do primeiro objeto (geralmente é o da cabine econômica)
                int weightInGrams = co2Emissions[0].getWeight();
                double emissaoSegmentoKg = weightInGrams / 1000.0; // Converte de gramas para kg
                emissaoAcumulada += emissaoSegmentoKg;
                usouDadosReais = true;
                
                String modeloInfo = codigoAviao != null ? 
                    " [" + CarbonEmissionCalculator.getNomeModelo(codigoAviao) + "]" : "";
                System.out.println("  ✅ Usando CO₂ real da API: " + emissaoSegmentoKg + " kg (" + 
                                 origemSegmento + "→" + destinoSegmento + modeloInfo + ")");
            } 
            // 🛩️ PRIORIDADE 2: Usar modelo específico do avião (se disponível)
            else if (codigoAviao != null && CarbonEmissionCalculator.isModeloConhecido(codigoAviao)) {
                double distanciaSegmento = CarbonEmissionCalculator.estimarDistanciaPorCodigo(
                    origemSegmento, destinoSegmento
                );
                double emissaoSegmento = CarbonEmissionCalculator.calcularEmissaoPorModelo(
                    codigoAviao, distanciaSegmento
                );
                emissaoAcumulada += emissaoSegmento;
                
                System.out.println("  🛩️  Usando modelo " + CarbonEmissionCalculator.getNomeModelo(codigoAviao) + 
                                 ": " + emissaoSegmento + " kg (" + origemSegmento + "→" + destinoSegmento + ")");
            }
            // 📊 PRIORIDADE 3: Cálculo genérico por distância
            else {
                double distanciaSegmento = CarbonEmissionCalculator.estimarDistanciaPorCodigo(
                    origemSegmento, destinoSegmento
                );
                double emissaoSegmento = CarbonEmissionCalculator.calcularEmissao(distanciaSegmento, 0);
                emissaoAcumulada += emissaoSegmento;
                
                String modeloInfo = codigoAviao != null ? " [modelo " + codigoAviao + " desconhecido]" : "";
                System.out.println("  ⚠️  Usando cálculo genérico: " + emissaoSegmento + " kg (" + 
                                 origemSegmento + "→" + destinoSegmento + modeloInfo + ")");
            }
            
            // Calcula distância para referência
            double distanciaSegmento = CarbonEmissionCalculator.estimarDistanciaPorCodigo(
                origemSegmento, destinoSegmento
            );
            distanciaAcumulada += distanciaSegmento;
        }
        
        // Adiciona penalidade por escalas apenas se não usou dados reais
        // (dados reais da API já consideram o tipo de voo)
        if (!usouDadosReais) {
            int numeroEscalas = segmentos.length - 1;
            emissaoAcumulada += numeroEscalas * 50.0; // 50kg CO2 por escala
        }
        
        this.distanciaTotal = distanciaAcumulada;
        this.emissaoTotal = emissaoAcumulada;
    }
    
    // Compara voos pela emissão de carbono (para ordenação)
    @Override
    public int compareTo(VooComEmissao outro) {
        return Double.compare(this.emissaoTotal, outro.emissaoTotal);
    }
    
    // Getters
    public FlightOfferSearch getVoo() {
        return voo;
    }
    
    public double getEmissaoTotal() {
        return emissaoTotal;
    }
    
    public double getDistanciaTotal() {
        return distanciaTotal;
    }
    
    public String getOrigem() {
        return origem;
    }
    
    public String getDestino() {
        return destino;
    }
    
    public String getPreco() {
        return voo.getPrice().getTotal() + " " + voo.getPrice().getCurrency();
    }
    
    // Retorna apenas o valor numérico do preço
    public double getPrecoValor() {
        try {
            return Double.parseDouble(voo.getPrice().getTotal());
        } catch (Exception e) {
            return 0.0;
        }
    }
    
    // Retorna apenas a moeda do preço
    public String getPrecoMoeda() {
        return voo.getPrice().getCurrency();
    }
    
    public int getNumeroEscalas() {
        return voo.getItineraries()[0].getSegments().length - 1;
    }
    
    public int getNumeroConexoes() {
        return getNumeroEscalas(); // Alias para compatibilidade com template
    }
    
    // Retorna o código da companhia aérea principal
    public String getCodigoCompanhia() {
        try {
            return voo.getItineraries()[0].getSegments()[0].getCarrierCode();
        } catch (Exception e) {
            return "N/A";
        }
    }
    
    // Retorna o nome da companhia aérea principal
    public String getNomeCompanhia() {
        String codigo = getCodigoCompanhia();
        return COMPANHIAS.getOrDefault(codigo, codigo);
    }
    
    public String getCompanhia() {
        return getNomeCompanhia(); // Alias para template
    }
    
    // Retorna o número do voo principal (primeiro segmento)
    public String getNumeroVoo() {
        try {
            String codigo = getCodigoCompanhia();
            String numero = voo.getItineraries()[0].getSegments()[0].getNumber();
            return codigo + " " + numero;
        } catch (Exception e) {
            return "N/A";
        }
    }
    
    // Retorna o modelo do avião principal (primeiro segmento)
    public String getModeloAviao() {
        try {
            var segmento = voo.getItineraries()[0].getSegments()[0];
            if (segmento.getAircraft() != null && segmento.getAircraft().getCode() != null) {
                String codigoAviao = segmento.getAircraft().getCode();
                String nomeModelo = CarbonEmissionCalculator.getNomeModelo(codigoAviao);
                
                // Se o nome é diferente do código, retorna o nome completo
                if (!nomeModelo.equals(codigoAviao)) {
                    return nomeModelo;
                }
                return codigoAviao;
            }
            return "N/A";
        } catch (Exception e) {
            return "N/A";
        }
    }
    
    // Retorna a data/hora de partida formatada
    public String getDataPartida() {
        try {
            String isoDate = voo.getItineraries()[0].getSegments()[0].getDeparture().getAt();
            // Converte de ISO 8601 (2025-01-20T10:30:00) para dd/mm/aaaa HH:mm
            LocalDateTime dateTime = LocalDateTime.parse(isoDate, DateTimeFormatter.ISO_DATE_TIME);
            return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } catch (Exception e) {
            return "N/A";
        }
    }
    
    // Retorna a data/hora de chegada formatada
    public String getDataChegada() {
        try {
            var segmentos = voo.getItineraries()[0].getSegments();
            String isoDate = segmentos[segmentos.length - 1].getArrival().getAt();
            // Converte de ISO 8601 (2025-01-20T22:45:00) para dd/mm/aaaa HH:mm
            LocalDateTime dateTime = LocalDateTime.parse(isoDate, DateTimeFormatter.ISO_DATE_TIME);
            return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } catch (Exception e) {
            return "N/A";
        }
    }
    
    // Retorna a duração total do voo
    public String getDuracao() {
        try {
            String duration = voo.getItineraries()[0].getDuration();
            // Formato ISO 8601: PT12H30M -> 12h 30min
            return duration.replace("PT", "").replace("H", "h ").replace("M", "min");
        } catch (Exception e) {
            return "N/A";
        }
    }
    
    // Retorna a emissão de carbono formatada
    public double getEmissaoCarbono() {
        return emissaoTotal;
    }
    
    // Retorna a rota completa com todas as escalas
    public String getRota() {
        try {
            var segmentos = voo.getItineraries()[0].getSegments();
            StringBuilder rota = new StringBuilder();
            rota.append(segmentos[0].getDeparture().getIataCode());
            for (var seg : segmentos) {
                rota.append(" → ").append(seg.getArrival().getIataCode());
            }
            return rota.toString();
        } catch (Exception e) {
            return origem + " → " + destino;
        }
    }
    
    // Retorna uma string formatada com informações do voo
    public String getResumo() {
        return String.format(
            "%s → %s | %s | %s | %d escala(s) | %s/pessoa",
            origem,
            destino,
            getNomeCompanhia(),
            getPreco(),
            getNumeroEscalas(),
            CarbonEmissionCalculator.formatarEmissao(emissaoTotal)
        );
    }
    
    // Retorna informações detalhadas do voo
    public String getDetalhes() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================\n");
        sb.append(String.format("🛫 %s → %s 🛬\n", origem, destino));
        sb.append("========================================\n");
        sb.append(String.format("✈️  Companhia: %s\n", getNomeCompanhia()));
        sb.append(String.format("💰 Preço: %s\n", getPreco()));
        sb.append(String.format("🔄 Escalas: %d\n", getNumeroEscalas()));
        sb.append(String.format("🌱 Emissão CO₂: %s por pessoa\n", 
            CarbonEmissionCalculator.formatarEmissao(emissaoTotal)));
        sb.append(String.format("📊 Nível: %s\n", 
            CarbonEmissionCalculator.classificarEmissao(emissaoTotal)));
        sb.append("\nSegmentos:\n");
        
        try {
            var itineraries = voo.getItineraries();
            if (itineraries != null && itineraries.length > 0) {
                var segmentos = itineraries[0].getSegments();
                for (int i = 0; i < segmentos.length; i++) {
                    var seg = segmentos[i];
                    String codigoCompanhia = seg.getCarrierCode();
                    String nomeCompanhia = COMPANHIAS.getOrDefault(codigoCompanhia, codigoCompanhia);
                    
                    // Pega o modelo do avião
                    String modeloAviao = "N/A";
                    if (seg.getAircraft() != null && seg.getAircraft().getCode() != null) {
                        String codigoAviao = seg.getAircraft().getCode();
                        modeloAviao = codigoAviao;
                        String nomeModelo = CarbonEmissionCalculator.getNomeModelo(codigoAviao);
                        
                        // Se o nome é diferente do código, mostra ambos
                        if (!nomeModelo.equals(codigoAviao)) {
                            modeloAviao = nomeModelo + " (" + codigoAviao + ")";
                        }
                    }
                    
                    sb.append(String.format("  %d. %s → %s | %s %s %s\n",
                        i + 1,
                        seg.getDeparture().getIataCode(),
                        seg.getArrival().getIataCode(),
                        nomeCompanhia,
                        codigoCompanhia,
                        seg.getNumber()
                    ));
                    sb.append(String.format("     🛩️  Aeronave: %s\n", modeloAviao));
                }
            }
        } catch (Exception e) {
            sb.append("  (Erro ao carregar segmentos)\n");
        }
        sb.append("========================================\n");
        
        return sb.toString();
    }
}
