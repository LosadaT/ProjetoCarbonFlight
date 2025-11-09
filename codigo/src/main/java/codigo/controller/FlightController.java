package codigo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import codigo.model.VooComEmissao;
import codigo.service.FlightCarbonService;

// Controller para busca de voos
// Gerencia as rotas de busca e visualização de voos
@Controller
@RequestMapping("/voos")
public class FlightController {

    @Value("${AMADEUS_API_KEY:}")
    private String apiKey;

    @Value("${AMADEUS_API_SECRET:}")
    private String apiSecret;

    // Rota: GET /voos/buscar
    // Exibe o formulário de busca de voos
    @GetMapping("/buscar")
    public String formularioBusca(Model model) {
        model.addAttribute("titulo", "Buscar Voos Sustentáveis");
        return "voos/buscar";
    }

    // Rota: POST /voos/buscar
    // Processa a busca de voos
    @PostMapping("/buscar")
    public String buscarVoos(
            @RequestParam String origem,
            @RequestParam String destino,
            @RequestParam String data,
            @RequestParam(defaultValue = "1") int adultos,
            Model model) {

        // Valida as credenciais
        if (apiKey == null || apiKey.isEmpty() || apiSecret == null || apiSecret.isEmpty()) {
            model.addAttribute("erro", "Credenciais da API Amadeus não configuradas!");
            model.addAttribute("titulo", "Erro de Configuração");
            return "voos/erro";
        }

        try {
            // Busca os voos
            FlightCarbonService service = new FlightCarbonService(apiKey, apiSecret);
            List<VooComEmissao> voos = service.buscarVoosOrdenadosPorEmissao(
                    origem.toUpperCase(),
                    destino.toUpperCase(),
                    data,
                    adultos
            );

            // Adiciona os resultados ao model
            model.addAttribute("voos", voos);
            model.addAttribute("origem", origem.toUpperCase());
            model.addAttribute("destino", destino.toUpperCase());
            model.addAttribute("data", data);
            model.addAttribute("adultos", adultos);
            model.addAttribute("titulo", "Resultados da Busca");

            // Calcula estatísticas
            if (!voos.isEmpty()) {
                double menorEmissao = voos.get(0).getEmissaoTotal();
                double maiorEmissao = voos.get(voos.size() - 1).getEmissaoTotal();
                double mediaEmissao = voos.stream()
                        .mapToDouble(VooComEmissao::getEmissaoTotal)
                        .average()
                        .orElse(0);

                model.addAttribute("menorEmissao", menorEmissao);
                model.addAttribute("maiorEmissao", maiorEmissao);
                model.addAttribute("mediaEmissao", mediaEmissao);
                model.addAttribute("diferencaEmissao", maiorEmissao - menorEmissao);
                model.addAttribute("percentualDiferenca", 
                    ((maiorEmissao - menorEmissao) / menorEmissao) * 100);
            }

            return "voos/resultados";

        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao buscar voos: " + e.getMessage());
            model.addAttribute("titulo", "Erro na Busca");
            return "voos/erro";
        }
    }
}
