# 🚀 Como Executar o Carbon Flight

> **📖 Documentação Completa:** [DOCUMENTACAO.md](DOCUMENTACAO.md)

---

## ⚡ Início Rápido

### 1️⃣ Configure as credenciais

Edite o arquivo `src/main/resources/application.properties`:

```properties
amadeus.api.key=SUA_API_KEY_AQUI
amadeus.api.secret=SEU_API_SECRET_AQUI
```

> **Não tem credenciais?** Veja [CREDENCIAIS.md](CREDENCIAIS.md)

### 2️⃣ Compile o projeto

```bash
mvn clean compile
```

### 3️⃣ Execute a aplicação

```bash
mvn spring-boot:run
```

### 4️⃣ Acesse no navegador

Abra seu navegador e acesse:
```
http://localhost:8080
```

🎉 **Pronto!** Você verá a página inicial do Carbon Flight.

---

## 🌐 Usando a Interface Web

### Página Inicial
- Clique em **"Buscar Voos"** para começar

### Busca de Voos
1. **Origem**: Digite o nome da cidade ou código IATA
   - Exemplo: "São Paulo" ou "GRU"
   - Use o autocomplete para selecionar o aeroporto correto
   
2. **Destino**: Digite o nome da cidade ou código IATA
   - Exemplo: "Lisboa" ou "LIS"
   - 741 aeroportos disponíveis!

3. **Data**: Selecione a data de partida (formato dd/mm/aaaa)
   - Exemplo: 25/01/2025

4. **Passageiros**: Número de adultos
   - Exemplo: 1

5. Clique em **"Buscar Voos"**

### Visualizando Resultados
- Voos ordenados por **menor emissão de CO₂**
- Cores indicativas:
  - 🟢 **Verde**: Baixa emissão (ótima escolha!)
  - 🟡 **Amarelo**: Média emissão
  - 🟠 **Laranja**: Alta emissão
  - 🔴 **Vermelho**: Muito alta emissão

- Informações exibidas:
  - Emissão de CO₂ por passageiro
  - Preço em reais (BRL)
  - Companhia aérea
  - Horários de partida e chegada
  - Número de escalas
  - Duração total do voo
  - Modelo da aeronave (quando disponível)

---

## 📍 Aeroportos Disponíveis

O sistema possui **741 aeroportos** cadastrados:

### Brasil (104 aeroportos) 🇧🇷
- Todas as capitais (São Paulo, Rio, Brasília, Salvador, etc.)
- Principais cidades (Foz do Iguaçu, Uberlândia, Joinville, etc.)
- Destinos turísticos (Fernando de Noronha, Porto Seguro, etc.)

### América do Norte (142 aeroportos) 🌎
- **EUA**: 58 aeroportos (Nova York, Los Angeles, Miami, Chicago, etc.)
- **México**: 27 aeroportos (Cancún, Guadalajara, Puerto Vallarta, etc.)
- **Canadá**: 15 aeroportos (Toronto, Vancouver, Montreal, etc.)

### América do Sul (112 aeroportos) 🌎
- Argentina, Chile, Peru, Colômbia, Equador, Venezuela
- Destinos: Buenos Aires, Santiago, Lima, Bogotá, Cusco, etc.

### Europa (145+ aeroportos) 🇪🇺
- Reino Unido, França, Espanha, Itália, Alemanha, Portugal
- Destinos: Londres, Paris, Roma, Madrid, Lisboa, Barcelona, etc.

### Ásia (120+ aeroportos) 🌏
- China, Japão, Índia, Tailândia, Singapura, Emirados Árabes
- Destinos: Tóquio, Dubai, Bangkok, Singapura, Hong Kong, etc.

### África (85+ aeroportos) 🌍
- África do Sul, Egito, Marrocos, Quênia, Nigéria
- Destinos: Cidade do Cabo, Cairo, Marrakesh, Nairobi, etc.

### Oceania (45+ aeroportos) 🌊
- Austrália, Nova Zelândia, ilhas do Pacífico
- Destinos: Sydney, Melbourne, Auckland, Fiji, Tahiti, etc.

---
## ⚠️ Notas Importantes

### Sobre Dados de CO₂

O sistema usa uma abordagem híbrida de **3 níveis** para máxima precisão:

1. **✅ Dados Reais da API**: Quando API Amadeus fornece (máxima precisão)
2. **🛩️ Cálculo por Modelo de Aeronave**: Usa eficiência específica de cada avião (45+ modelos cadastrados)
3. **⚠️ Estimativa Genérica**: Baseado em metodologia ICAO quando modelo é desconhecido

**Novo!** O sistema agora mostra o modelo específico de cada aeronave nos detalhes do voo, permitindo que você escolha voos com aviões mais eficientes (ex: Boeing 787 Dreamliner, Airbus A350).

### Sobre a API Amadeus
- **Limites gratuitos**: 2.000 chamadas/mês
- **Dados**: Voos reais com preços atualizados
- **Disponibilidade**: Voos até 11 meses no futuro
- **Horário**: Use datas futuras (hoje + alguns dias)

---

## 🐛 Solução de Problemas

### Erro: "BUILD FAILURE"
```bash
# Limpar e recompilar
mvn clean compile
```

### Erro: "Connection refused" ou porta 8080 em uso
```bash
# Verificar se algo está usando a porta 8080
lsof -i :8080

# Matar o processo (substitua PID pelo número real)
kill -9 PID

# Ou usar outra porta no application.properties:
# server.port=8081
```

### Erro: "API Error 401 - Unauthorized"
- Verifique se as credenciais estão corretas em `application.properties`
- Confirme que copiou API Key e API Secret corretamente
- Teste suas credenciais em: https://developers.amadeus.com/

### Erro: "No flights found"
- Tente outra data (alguns dias no futuro)
- Verifique se os códigos IATA estão corretos
- Nem todas as rotas têm voos disponíveis

### Autocomplete não funciona
```bash
# Limpe o cache do navegador:
# Chrome/Edge: Ctrl+Shift+Delete
# Firefox: Ctrl+Shift+Delete
# Safari: Command+Option+E

# Ou force refresh da página:
# Windows/Linux: Ctrl+Shift+R
# Mac: Command+Shift+R
```

### Programa não inicia
```bash
# Verificar Java
java -version  # Deve ser 11+

# Verificar Maven
mvn -version  # Deve ser 3.6+

# Recompilar tudo
mvn clean install

# Verificar se o Spring Boot está funcionando
mvn spring-boot:run
```

---

## 📊 Exemplo de Saída na Web

### Tela de Resultados

Após buscar voos de **São Paulo (GRU) → Lisboa (LIS)**, você verá:

#### Header da Rota
```
São Paulo (GRU) → Lisboa (LIS)
25/01/2025 • 5 voos encontrados
```

#### Card de Voo (Exemplo)
```
┌─────────────────────────────────────────────┐
│ 💚 237.5 kg CO₂/passageiro                  │
│                                             │
│ TAP Air Portugal                            │
│                                             │
│ 🛫 Partida: 25/01/2025 10:30               │
│ 🛬 Chegada: 25/01/2025 23:00               │
│                                             │
│ ⏱️ Direto • 10h 30min                       │
│ 💰 3.245,00 BRL                             │
│ ✈️ Airbus A330-900neo                       │
└─────────────────────────────────────────────┘
```

#### Classificação por Cores
- 🟢 **Verde** (< 250 kg): Baixa emissão - Ótima escolha!
- 🟡 **Amarelo** (250-400 kg): Média emissão
- 🟠 **Laranja** (400-600 kg): Alta emissão
- 🔴 **Vermelho** (> 600 kg): Muito alta emissão

---

## 📋 Códigos IATA Populares

### Brasil 🇧🇷
| Código | Cidade | Aeroporto |
|--------|--------|-----------|
| GRU | São Paulo | Guarulhos |
| GIG | Rio de Janeiro | Galeão |
| BSB | Brasília | Presidente Juscelino Kubitschek |
| SSA | Salvador | Deputado Luís Eduardo Magalhães |
| CGH | São Paulo | Congonhas |
| SDU | Rio de Janeiro | Santos Dumont |
| CNF | Belo Horizonte | Confins |
| POA | Porto Alegre | Salgado Filho |
| CWB | Curitiba | Afonso Pena |
| FOR | Fortaleza | Pinto Martins |

### América do Norte 🌎
| Código | Cidade | País |
|--------|--------|------|
| JFK | Nova York | 🇺🇸 EUA |
| LAX | Los Angeles | 🇺🇸 EUA |
| MIA | Miami | 🇺🇸 EUA |
| ORD | Chicago | 🇺🇸 EUA |
| YYZ | Toronto | 🇨🇦 Canadá |
| MEX | Cidade do México | 🇲🇽 México |
| CUN | Cancún | �🇽 México |

### Europa 🇪🇺
| Código | Cidade | País |
|--------|--------|------|
| LIS | Lisboa | 🇵🇹 Portugal |
| OPO | Porto | 🇵🇹 Portugal |
| LHR | Londres | 🇬🇧 Reino Unido |
| CDG | Paris | 🇫🇷 França |
| MAD | Madrid | 🇪🇸 Espanha |
| BCN | Barcelona | 🇪🇸 Espanha |
| FCO | Roma | 🇮🇹 Itália |
| FRA | Frankfurt | 🇩🇪 Alemanha |

### América do Sul 🌎
| Código | Cidade | País |
|--------|--------|------|
| EZE | Buenos Aires | 🇦🇷 Argentina |
| SCL | Santiago | 🇨🇱 Chile |
| LIM | Lima | 🇵🇪 Peru |
| BOG | Bogotá | 🇨🇴 Colômbia |
| UIO | Quito | 🇪🇨 Equador |
| MVD | Montevidéu | 🇺🇾 Uruguai |

---

## 💡 Dicas de Uso

### Para Desenvolvedores
- Use `mvn clean` antes de compilar para evitar problemas de cache
- Monitore os logs do Spring Boot para debug
- A porta padrão é 8080, mas pode ser alterada no `application.properties`
- O autocomplete carrega 741 aeroportos - pode demorar alguns segundos na primeira vez

### Para Viajantes
- **Escolha voos diretos** quando possível - geralmente têm menor emissão
- **Prefira aviões modernos** como Boeing 787, Airbus A350, A330neo
- **Evite aviões antigos** como A340, 747 (maior consumo)
- **Compare preço x emissão** - nem sempre o mais barato é o mais sustentável
- **Reserve com antecedência** para melhores opções

### Economia de Emissões
Diferença entre o melhor e pior voo pode chegar a:
- ✅ **50% menos CO₂** em rotas longas
- ✅ **30-40% menos** em rotas médias
- ✅ **15-25% menos** em rotas curtas

---

## 🔗 Links Rápidos

- 📖 **[Documentação Completa](DOCUMENTACAO.md)** - Tudo sobre o sistema
- 🔑 **[Configurar Credenciais](CREDENCIAIS.md)** - Passo a passo
- 📝 **[README](README.md)** - Visão geral do projeto
- 🌐 **[API Amadeus](https://developers.amadeus.com/)** - Portal oficial

---

*Última atualização: 9 de Novembro de 2025*

**Desenvolvido com 💚 para um futuro mais sustentável** 🌱✈️
  2. BOG → JFK | Avianca AV 244
     🛩️  Aeronave: Airbus A320neo (32N)
========================================
```

---

## 📚 Mais Informações

- **Documentação completa**: `docs/`
- **Diário de desenvolvimento**: `docs/DIARIO_DESENVOLVIMENTO.md`
- **Explicação de emissões**: `docs/EMISSOES_CO2_EXPLICACAO.md`
- **Estrutura do projeto**: `docs/ESTRUTURA_PROJETO.md`

---

**Última atualização**: 07/11/2025
**Versão**: 1.0.0
