# 🛠️ Tecnologias do CarbonFlight

## 📋 Visão Geral

O CarbonFlight é uma aplicação web construída com uma arquitetura **cliente-servidor** moderna, combinando tecnologias de backend (Java/Spring Boot) com frontend (HTML/CSS/JavaScript).

---

## 🏗️ Arquitetura do Projeto

```
┌─────────────────────────────────────────┐
│          NAVEGADOR (Cliente)            │
│  ┌───────────────────────────────────┐  │
│  │   HTML + CSS + JavaScript         │  │
│  │   (Interface do Usuário)          │  │
│  └───────────────────────────────────┘  │
└──────────────────┬──────────────────────┘
                   │ HTTP (Requisições)
                   ▼
┌─────────────────────────────────────────┐
│      SERVIDOR (Backend)                 │
│  ┌───────────────────────────────────┐  │
│  │     Spring Boot (Java 11)         │  │
│  │  ┌─────────────────────────────┐  │  │
│  │  │  Controllers (Rotas)        │  │  │
│  │  │  Services (Lógica)          │  │  │
│  │  │  Models (Dados)             │  │  │
│  │  └─────────────────────────────┘  │  │
│  │           ▲                        │  │
│  │           │ Thymeleaf              │  │
│  │           ▼                        │  │
│  │  ┌─────────────────────────────┐  │  │
│  │  │  Templates HTML             │  │  │
│  │  └─────────────────────────────┘  │  │
│  └───────────────────────────────────┘  │
└──────────────────┬──────────────────────┘
                   │ API REST
                   ▼
┌─────────────────────────────────────────┐
│        API EXTERNA (Amadeus)            │
│     (Dados de Voos e Emissões)          │
└─────────────────────────────────────────┘
```

---

## 🔧 Tecnologias e Suas Funções

### 1. ☕ **Java 11**
**O que é:** Linguagem de programação principal do backend

**Função no projeto:**
- Processa toda a **lógica de negócio**
- Calcula emissões de carbono
- Gerencia requisições HTTP
- Manipula dados de voos

**Onde está:**
- `src/main/java/codigo/`
  - `main.java` - Ponto de entrada console (legado)
  - `Application.java` - Ponto de entrada web
  - `model/` - Classes de dados (VooComEmissao, TGrafo)
  - `service/` - Lógica de negócio (FlightCarbonService)
  - `util/` - Utilitários (CarbonEmissionCalculator)
  - `controller/` - Controladores web (FlightController)

**Exemplo de código:**
```java
// Calcula emissão de carbono
public double calcularEmissao(double distancia, int escalas) {
    double emissaoBase = distancia * EMISSION_FACTOR;
    double penalidade = escalas * STOPOVER_PENALTY;
    return emissaoBase + penalidade;
}
```

**Por que Java?**
- ✅ Robusta e confiável
- ✅ Excelente para cálculos complexos
- ✅ Grande ecossistema de bibliotecas
- ✅ Boa integração com Spring Boot

---

### 2. 🍃 **Spring Boot 2.7.18**
**O que é:** Framework Java para criar aplicações web rapidamente

**Função no projeto:**
- **Servidor web embarcado** (Tomcat na porta 8080)
- **Roteamento** de URLs
- **Injeção de dependências**
- **Gerenciamento** de controllers e services

**Onde está:**
- `pom.xml` - Dependência configurada
- `Application.java` - Classe principal com `@SpringBootApplication`
- `controller/FlightController.java` - Rotas web

**Como funciona:**
```java
@SpringBootApplication  // ← Marca como aplicação Spring Boot
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);  // Inicia servidor
    }
}
```

**Anotações importantes:**
- `@Controller` - Define um controlador de rotas
- `@GetMapping("/rota")` - Mapeia URL para método
- `@PostMapping("/rota")` - Recebe dados de formulário
- `@RequestParam` - Captura parâmetros da URL

**Por que Spring Boot?**
- ✅ Configuração automática (zero XML!)
- ✅ Servidor embutido (não precisa instalar Tomcat)
- ✅ Padrão da indústria para Java web
- ✅ Fácil de testar e desenvolver

---

### 3. 🌿 **Thymeleaf 3.0.15**
**O que é:** Motor de templates para gerar HTML dinamicamente

**Função no projeto:**
- **Gera HTML** no servidor com dados Java
- **Renderiza** listas de voos
- **Preenche** valores dinâmicos (origem, destino, preço)
- **Condiciona** exibição (se não há voos, mostra mensagem)

**Onde está:**
- `src/main/resources/templates/` - Todos os arquivos `.html`
  - `index.html` - Página inicial
  - `voos/buscar.html` - Formulário de busca
  - `voos/resultados.html` - Lista de voos
  - `sobre.html` - Página sobre

**Como funciona:**
```html
<!-- Thymeleaf usa prefixo th: -->
<h2 th:text="${titulo}">Título padrão</h2>
<!-- No servidor, ${titulo} é substituído pelo valor Java -->

<!-- Loop por lista -->
<div th:each="voo : ${voos}">
    <p th:text="${voo.origem}">GRU</p>
    <p th:text="${voo.destino}">JFK</p>
</div>

<!-- Condicionais -->
<div th:if="${voos.isEmpty()}">
    <p>Nenhum voo encontrado</p>
</div>
```

**Fluxo completo:**
1. Controller passa dados: `model.addAttribute("voos", listaVoos);`
2. Thymeleaf recebe: `${voos}`
3. HTML é gerado no servidor
4. Navegador recebe HTML pronto

**Por que Thymeleaf?**
- ✅ Templates são HTML válido (podem abrir no navegador)
- ✅ Integração perfeita com Spring Boot
- ✅ Sintaxe simples e intuitiva
- ✅ Suporta internacionalização

---

### 4. 📦 **Maven 3.9.11**
**O que é:** Ferramenta de gerenciamento de projetos e dependências

**Função no projeto:**
- **Gerencia dependências** (Spring Boot, Amadeus SDK, etc.)
- **Compila** código Java
- **Empacota** em arquivo JAR
- **Executa** a aplicação

**Onde está:**
- `pom.xml` - Arquivo de configuração principal

**Estrutura do pom.xml:**
```xml
<project>
    <!-- Informações do projeto -->
    <groupId>codigo</groupId>
    <artifactId>ProjetoCarbonFlight</artifactId>
    <version>1.0-SNAPSHOT</version>
    
    <!-- Dependências -->
    <dependencies>
        <!-- Spring Boot -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <!-- Thymeleaf -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        
        <!-- Amadeus API -->
        <dependency>
            <groupId>com.amadeus</groupId>
            <artifactId>amadeus-java</artifactId>
            <version>8.1.0</version>
        </dependency>
    </dependencies>
</project>
```

**Comandos principais:**
```bash
mvn compile              # Compila código
mvn clean                # Limpa pasta target/
mvn package              # Cria arquivo JAR
mvn spring-boot:run      # Executa aplicação
```

**Por que Maven?**
- ✅ Padrão para projetos Java
- ✅ Gerenciamento automático de dependências
- ✅ Estrutura de pastas padronizada
- ✅ Fácil integração com IDEs

---

### 5. 🌐 **HTML5**
**O que é:** Linguagem de marcação para estruturar páginas web

**Função no projeto:**
- **Estrutura** das páginas
- **Formulários** de busca
- **Semântica** (header, nav, main, footer)
- **Acessibilidade** (aria-labels, roles)

**Onde está:**
- `src/main/resources/templates/*.html`

**Estrutura típica:**
```html
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>CarbonFlight</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <header>
        <nav><!-- Menu --></nav>
    </header>
    <main>
        <!-- Conteúdo principal -->
    </main>
    <footer>
        <!-- Rodapé -->
    </footer>
    <script src="/js/accessibility.js"></script>
</body>
</html>
```

**Tags importantes usadas:**
- `<form>` - Formulário de busca
- `<input>` - Campos (origem, destino, data)
- `<button>` - Botões de ação
- `<div class="flight-card">` - Cards de voos

---

### 6. 🎨 **CSS3**
**O que é:** Linguagem de estilo para design visual

**Função no projeto:**
- **Cores** e identidade visual (marrom/bege)
- **Layout** responsivo (flexbox, grid)
- **Animações** e transições
- **Acessibilidade** (alto contraste, tamanhos de fonte)

**Onde está:**
- `src/main/resources/static/css/`
  - `style.css` - Estilos principais
  - `forms.css` - Formulários
  - `autocomplete.css` - Autocomplete
  - `loading.css` - Loading spinner
  - `accessibility.css` - Recursos de acessibilidade

**Exemplos de estilos:**
```css
/* Cores do tema */
body {
    background-color: #EBD7A5;  /* Bege */
    color: #333;
}

.navbar {
    background: linear-gradient(135deg, #876000, #6b4d00);  /* Marrom */
}

/* Layout com Flexbox */
.navbar .container {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

/* Cards de voos */
.flight-card {
    background: white;
    border-radius: 8px;
    padding: 20px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}
```

**Técnicas usadas:**
- **Gradientes** para fundos suaves
- **Flexbox/Grid** para layouts
- **Media queries** para responsividade
- **Transitions** para animações suaves

---

### 7. ⚡ **JavaScript (ES6)**
**O que é:** Linguagem de programação do navegador

**Função no projeto:**
- **Autocomplete** de aeroportos
- **Validação** de formulários
- **Extração** de códigos IATA
- **Acessibilidade** dinâmica
- **Interatividade** da interface

**Onde está:**
- `src/main/resources/static/js/`
  - `airports.js` - Dados de 741 aeroportos
  - `autocomplete.js` - Sistema de busca
  - `accessibility.js` - Sistema de acessibilidade

**Exemplo - Autocomplete:**
```javascript
// airports.js - Base de dados
const airports = [
    { iata: "GRU", name: "Aeroporto...", city: "São Paulo", country: "Brasil" },
    { iata: "JFK", name: "John F Kennedy", city: "Nova York", country: "EUA" }
];

// autocomplete.js - Busca
function filterAirports(query) {
    return airports.filter(airport => 
        airport.city.toLowerCase().includes(query.toLowerCase()) ||
        airport.iata.toLowerCase().includes(query.toLowerCase())
    );
}
```

**Exemplo - Acessibilidade:**
```javascript
// accessibility.js
class AccessibilityManager {
    toggleHighContrast() {
        document.body.classList.toggle('high-contrast');
        localStorage.setItem('highContrast', this.highContrast);
    }
    
    setFontSize(size) {
        document.body.className = `font-${size}`;
    }
}
```

**Features JavaScript:**
- **Classes ES6** para organização
- **LocalStorage** para persistência
- **Event Listeners** para interatividade
- **Fetch API** (se necessário para chamadas AJAX)

---

## 🔗 Como as Tecnologias se Integram

### 🎯 **Fluxo Completo de uma Busca de Voos**

```
1. USUÁRIO digita no navegador
   └─> http://localhost:8080/voos/buscar

2. SPRING BOOT recebe requisição
   └─> @GetMapping("/voos/buscar") no FlightController.java
   └─> Retorna template "voos/buscar.html"

3. THYMELEAF processa template
   └─> Substitui ${titulo} por "Buscar Voos"
   └─> Gera HTML completo

4. NAVEGADOR recebe HTML + CSS + JavaScript
   └─> Carrega style.css (cores marrom/bege)
   └─> Carrega airports.js (741 aeroportos)
   └─> Carrega autocomplete.js (busca interativa)
   └─> Carrega accessibility.js (recursos de acessibilidade)

5. USUÁRIO digita "São" no campo origem
   └─> JavaScript autocomplete.js filtra airports
   └─> Mostra "São Paulo (GRU)", "São Luís (SLZ)"...

6. USUÁRIO preenche formulário e clica "Buscar"
   └─> HTML <form method="POST" action="/voos/resultados">
   └─> Dados enviados: origem=GRU, destino=JFK, data=2025-12-25

7. SPRING BOOT recebe POST
   └─> @PostMapping("/voos/resultados") no FlightController
   └─> Chama FlightCarbonService.buscarVoos(origem, destino, data)

8. JAVA processa busca
   └─> FlightCarbonService usa Amadeus SDK
   └─> Busca voos na API externa
   └─> Calcula emissão de carbono (CarbonEmissionCalculator)
   └─> Ordena por menor emissão
   └─> Retorna List<VooComEmissao>

9. CONTROLLER prepara resposta
   └─> model.addAttribute("voos", listaVoos);
   └─> return "voos/resultados"

10. THYMELEAF gera HTML com dados
    └─> <div th:each="voo : ${voos}">
    └─> Preenche origem, destino, preço, emissão...

11. NAVEGADOR recebe HTML pronto
    └─> CSS aplica estilos aos cards
    └─> JavaScript adiciona nome da cidade
    └─> USUÁRIO vê resultados ordenados!
```

---

## 📁 **Estrutura de Arquivos e Responsabilidades**

```
codigo/
│
├── pom.xml                          ← Maven: Dependências e configuração
│
├── src/main/
│   ├── java/codigo/                 ← JAVA: Backend
│   │   ├── Application.java         → Spring Boot: Inicia servidor
│   │   ├── controller/
│   │   │   └── FlightController.java → Spring: Rotas web
│   │   ├── service/
│   │   │   └── FlightCarbonService.java → Java: Lógica de negócio
│   │   ├── model/
│   │   │   ├── VooComEmissao.java   → Java: Dados de voo
│   │   │   └── TGrafo.java          → Java: Grafo de rotas
│   │   └── util/
│   │       └── CarbonEmissionCalculator.java → Java: Cálculos
│   │
│   └── resources/                   ← Recursos estáticos e templates
│       ├── templates/               ← THYMELEAF: HTML dinâmico
│       │   ├── index.html           → Página inicial
│       │   ├── sobre.html           → Sobre
│       │   └── voos/
│       │       ├── buscar.html      → Formulário
│       │       └── resultados.html  → Lista de voos
│       │
│       ├── static/                  ← HTML/CSS/JS estáticos
│       │   ├── css/                 → CSS: Estilos visuais
│       │   │   ├── style.css        → Estilos principais
│       │   │   ├── forms.css        → Formulários
│       │   │   └── accessibility.css → Acessibilidade
│       │   │
│       │   └── js/                  → JAVASCRIPT: Interatividade
│       │       ├── airports.js      → 741 aeroportos
│       │       ├── autocomplete.js  → Busca interativa
│       │       └── accessibility.js → Alto contraste, fontes
│       │
│       └── application.properties   ← Spring Boot: Configurações
│
└── target/                          ← Maven: Arquivos compilados
    ├── classes/                     → .class Java compilados
    └── ProjetoCarbonFlight.jar      → JAR executável
```

---

## 🚀 **Processo de Compilação e Execução**

### **1. Maven compila Java**
```bash
mvn compile
```
- Lê `pom.xml`
- Baixa dependências (Spring Boot, Amadeus SDK)
- Compila `*.java` → `*.class`
- Coloca em `target/classes/`

### **2. Maven executa aplicação**
```bash
mvn spring-boot:run
```
- Executa `Application.java`
- Spring Boot:
  - Inicia Tomcat na porta 8080
  - Registra rotas do `FlightController`
  - Configura Thymeleaf para processar templates
  - Mapeia pasta `static/` para servir CSS/JS

### **3. Servidor aguarda requisições**
```
Servidor Spring Boot rodando em http://localhost:8080
├─ GET /                    → index.html
├─ GET /voos/buscar         → buscar.html
├─ POST /voos/resultados    → resultados.html (com dados)
└─ GET /sobre               → sobre.html
```

### **4. Navegador faz requisição**
1. Usuário acessa `http://localhost:8080/voos/buscar`
2. Spring Boot chama `FlightController.buscarVoos()`
3. Controller retorna nome do template: `"voos/buscar"`
4. Thymeleaf processa `templates/voos/buscar.html`
5. Substitui variáveis `${...}` por valores Java
6. Gera HTML completo
7. HTML referencia CSS: `<link href="/css/style.css">`
8. HTML referencia JS: `<script src="/js/autocomplete.js">`
9. Navegador baixa CSS e JS de `static/`
10. Página pronta!

---

## 🎨 **Resumo de Responsabilidades**

| Tecnologia | Onde Roda | Função | Exemplo |
|------------|-----------|--------|---------|
| **Java** | Servidor | Lógica, cálculos, processamento | Calcular emissão de carbono |
| **Spring Boot** | Servidor | Framework web, rotas, servidor | Receber requisição `/voos/buscar` |
| **Thymeleaf** | Servidor | Gerar HTML dinâmico | Preencher lista de voos |
| **Maven** | Build | Compilar, gerenciar dependências | `mvn spring-boot:run` |
| **HTML** | Navegador | Estrutura da página | Formulário, cards de voos |
| **CSS** | Navegador | Estilo visual | Cores marrom/bege, layout |
| **JavaScript** | Navegador | Interatividade | Autocomplete, acessibilidade |

---

## 🔄 **Fluxo de Dados**

```
JavaScript ─────────┐
  (Frontend)        │
                    ▼
HTML Form ──────> Spring Boot Controller
                    │
                    ▼
              Service Layer
                    │
                    ▼
              Amadeus API (externa)
                    │
                    ▼
              Cálculos Java
                    │
                    ▼
              Model (dados)
                    │
                    ▼
              Thymeleaf (template)
                    │
                    ▼
              HTML gerado
                    │
                    ▼
              CSS aplicado ◄─── style.css
                    │
                    ▼
              JavaScript ◄───── autocomplete.js
              processa
                    │
                    ▼
              Página final no navegador
```

---

## ✅ **Vantagens dessa Arquitetura**

### **Separação de Responsabilidades**
- Backend (Java): Não se preocupa com visual
- Frontend (CSS/JS): Não se preocupa com cálculos
- Templates (Thymeleaf): Ponte entre os dois

### **Manutenibilidade**
- Mudar cor? → Edita CSS
- Mudar cálculo? → Edita Java
- Mudar layout? → Edita HTML/Thymeleaf

### **Escalabilidade**
- Adicionar mais rotas? → Novo @GetMapping
- Adicionar mais estilos? → Novo arquivo CSS
- Adicionar mais funcionalidades JS? → Novo arquivo .js

### **Desenvolvimento Paralelo**
- Designer trabalha em CSS
- Dev backend trabalha em Java
- Dev frontend trabalha em JavaScript
- Nenhum bloqueia o outro!

---

## 🎓 **Para Aprender Mais**

### Java
- Tutorial oficial: https://dev.java/learn/

### Spring Boot
- Guia oficial: https://spring.io/guides/gs/serving-web-content/

### Thymeleaf
- Documentação: https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html

### Maven
- Getting Started: https://maven.apache.org/guides/getting-started/

### JavaScript
- MDN Web Docs: https://developer.mozilla.org/pt-BR/docs/Web/JavaScript

---

**✅ Agora você entende como cada tecnologia trabalha em conjunto para criar o CarbonFlight!** 🚀🌍
