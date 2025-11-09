package codigo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Controller principal da aplicação
// Gerencia as rotas da página inicial
@Controller
public class HomeController {

    // Rota: GET /
    // Exibe a página inicial
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("titulo", "Carbon Flight");
        model.addAttribute("subtitulo", "Sistema de Voos Sustentáveis");
        return "index";
    }

    // Rota: GET /sobre
    // Exibe informações sobre o projeto
    @GetMapping("/sobre")
    public String sobre(Model model) {
        model.addAttribute("titulo", "Sobre o Projeto");
        return "sobre";
    }
}
