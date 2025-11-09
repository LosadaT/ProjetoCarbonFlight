package codigo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

// Classe principal do Spring Boot
// Inicializa a aplicação web
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // Carrega variáveis do arquivo .env
        try {
            Dotenv dotenv = Dotenv.configure()
                .directory(".")
                .ignoreIfMissing()
                .load();
            
            // Define as variáveis como propriedades do sistema
            dotenv.entries().forEach(entry -> 
                System.setProperty(entry.getKey(), entry.getValue())
            );
            
            System.out.println("✅ Variáveis do .env carregadas com sucesso!");
        } catch (Exception e) {
            System.out.println("⚠️  Arquivo .env não encontrado ou erro ao carregar");
        }
        
        SpringApplication.run(Application.class, args);
    }
}
