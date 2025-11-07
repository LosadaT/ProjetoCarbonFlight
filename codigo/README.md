# 🌱 Carbon Flight - Sistema de Voos Sustentáveis# 🌱 



> Sistema inteligente para buscar e comparar voos baseado em **emissões de carbono**, ajudando a escolher as rotas mais sustentáveis.



[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)> Sistema inteligente para buscar e comparar voos baseado em **emissões de carbono**, ajudando a escolher as rotas mais sustentáveis.

[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)

[![API](https://img.shields.io/badge/API-Amadeus-red.svg)](https://developers.amadeus.com/)



---[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)> Sistema inteligente para buscar e comparar voos baseado em **emissões de carbono**, ajudando a escolher as rotas mais sustentáveis.



## 🚀 Início Rápido[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)



### Pré-requisitos[![API](https://img.shields.io/badge/API-Amadeus-red.svg)](https://developers.amadeus.com/)

- Java 11+

- Maven 3.6+

- Credenciais API Amadeus (gratuitas)

---[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)> Sistema inteligente para buscar e comparar voos baseado em **emissões de carbono**, ajudando a escolher as rotas mais sustentáveis.\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}

### 1️⃣ Configurar Credenciais 🔑



**Obtenha suas credenciais:**

1. Acesse: https://developers.amadeus.com## 🚀 Início Rápido[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)

2. Crie uma conta gratuita

3. Crie um app e copie API Key + API Secret



**Opção A: Arquivo .env (Recomendado - Mais Fácil!):**### Pré-requisitos[![API](https://img.shields.io/badge/API-Amadeus-red.svg)](https://developers.amadeus.com/)

```bash

# 1. Copie o arquivo de exemplo- Java 11+

cp .env.example .env

- Maven 3.6+

# 2. Edite o .env com suas credenciais:

#    AMADEUS_API_KEY=sua_chave_aqui- Credenciais API Amadeus (gratuitas)

#    AMADEUS_API_SECRET=seu_secret_aqui

```---[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)Sistema inteligente para buscar e comparar voos baseado em **emissões de carbono**, ajudando a escolher as rotas mais sustentáveis.{\colortbl;\red255\green255\blue255;}



**Opção B: Variáveis de ambiente:**### 1️⃣ Configurar Credenciais 🔑

```bash

export AMADEUS_API_KEY="sua_chave_aqui"

export AMADEUS_API_SECRET="seu_secret_aqui"

```**Obtenha suas credenciais:**



📖 **Instruções detalhadas:** [CREDENCIAIS.md](CREDENCIAIS.md)1. Acesse: https://developers.amadeus.com## 🚀 Início Rápido[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)



### 2️⃣ Compilar2. Crie uma conta gratuita

```bash

mvn clean compile3. Crie um app e copie API Key + API Secret

```



### 3️⃣ Executar

```bash**Configure no terminal (macOS/Linux):**### 1️⃣ Compilar[![API](https://img.shields.io/badge/API-Amadeus-red.svg)](https://developers.amadeus.com/){\*\expandedcolortbl;;}

mvn exec:java -Dexec.mainClass="codigo.main"

``````bash



### 4️⃣ Buscar Voosexport AMADEUS_API_KEY="sua_chave_aqui"```bash

Escolha a opção **10** no menu e forneça:

- Origem (ex: GRU)export AMADEUS_API_SECRET="seu_secret_aqui"

- Destino (ex: JFK)  

- Data (ex: 2025-12-15)```cd /Users/francisco/Documents/ProjetoCarbonFlight/codigo

- Passageiros (ex: 1)



---

📖 **Instruções completas:** [CREDENCIAIS.md](CREDENCIAIS.md)mvn clean compile

## 📖 Documentação Completa



**Toda a documentação está consolidada em um único arquivo:**

### 2️⃣ Compilar```---## 📁 Estrutura do Projeto\paperw11900\paperh16840\margl1440\margr1440\vieww11520\viewh8400\viewkind0

### 👉 [DOCUMENTACAO.md](DOCUMENTACAO.md)

```bash

Este arquivo contém:

- ✅ Guia completo de usocd /Users/francisco/Documents/ProjetoCarbonFlight/codigo

- ✅ Arquitetura do sistema

- ✅ Detalhes dos cálculos de CO₂mvn clean compile

- ✅ Explicação do algoritmo Dijkstra

- ✅ Documentação da API Amadeus```### 2️⃣ Executar

- ✅ Histórico de atualizações

- ✅ FAQ e troubleshooting

- ✅ Exemplos de saída

### 3️⃣ Executar```bash

---

```bash

## ⚡ Funcionalidades Principais

mvn exec:java -Dexec.mainClass="codigo.main"mvn exec:java -Dexec.mainClass="codigo.main"## 🚀 Início Rápido\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

### 🌱 Busca Sustentável

- Integração com API Amadeus```

- Até 10 opções de voos reais

- Ordenação por menor emissão de CO₂```



### 🛩️ Cálculo Preciso (3 Níveis)### 4️⃣ Buscar Voos

1. **✅ Dados Reais da API** - Máxima precisão

2. **🛩️ Cálculo por Modelo** - 45+ aviões cadastradosEscolha a opção **10** no menu e forneça:

3. **⚠️ Estimativa ICAO** - Fallback genérico

- Origem (ex: GRU)

### 📊 Análise Inteligente

- Algoritmo Dijkstra para rota ótima- Destino (ex: JFK)  ### 3️⃣ Buscar Voos

- Estatísticas comparativas

- Classificação visual (🟢🟡🟠🔴)- Data (ex: 2025-12-15)

- Modelo específico de cada aeronave

- Passageiros (ex: 1)Escolha a opção **10** no menu e forneça:### 1️⃣ Compilar```

---



## 📁 Estrutura

---- Origem (ex: GRU)

```

codigo/

├── .env.example                     # Modelo de configuração

├── .gitignore                       # Proteção de credenciais## 📖 Documentação Completa- Destino (ex: JFK)  ```bash

├── src/main/java/codigo/

│   ├── main.java                    # Aplicação principal

│   ├── model/                       # Modelos de dados

│   ├── service/                     # Lógica de negócio**Toda a documentação está consolidada em um único arquivo:**- Data (ex: 2025-12-15)

│   └── util/                        # Utilitários

├── data/

│   ├── grafoEntrada.txt            # Dados de entrada

│   └── grafoSaida.txt              # Dados de saída### 👉 [DOCUMENTACAO.md](DOCUMENTACAO.md)- Passageiros (ex: 1)cd /Users/francisco/Documents/ProjetoCarbonFlight/codigoProjetoCarbonFlight/codigo/\f0\fs24 \cf0 README}

├── DOCUMENTACAO.md                  # 📖 Documentação completa

├── CREDENCIAIS.md                   # 🔑 Como configurar API

├── README.md                        # Este arquivo

└── pom.xml                          # Maven configEste arquivo contém:

```

- ✅ Guia completo de uso

---

- ✅ Arquitetura do sistema---mvn clean compile├── src/main/java/codigo/

## 🎯 Exemplo de Resultado

- ✅ Detalhes dos cálculos de CO₂

```

╔════════════════════════════════════════════════════════════════╗- ✅ Explicação do algoritmo Dijkstra

║         VOOS ORDENADOS POR EMISSÃO DE CARBONO (MENOR → MAIOR) ║

╚════════════════════════════════════════════════════════════════╝- ✅ Documentação da API Amadeus



1. 🟢 BAIXA GRU → JFK | Avianca | 2401.37 BRL | 1 escala(s) | 1.03 toneladas CO₂/pessoa- ✅ Histórico de atualizações## 📖 Documentação Completa```│   ├── main.java                    # Aplicação principal com menu interativo

2. 🟡 MÉDIA GRU → JFK | Air Canada | 2184.59 BRL | 1 escala(s) | 1.09 toneladas CO₂/pessoa

- ✅ FAQ e troubleshooting

========================================

🛫 GRU → JFK 🛬- ✅ Exemplos de saída

========================================

✈️  Companhia: Avianca

💰 Preço: 2401.37 BRL

🔄 Escalas: 1---**Toda a documentação está consolidada em um único arquivo:**│   ├── model/                       # Modelos de dados

🌱 Emissão CO₂: 1.03 toneladas CO₂ por pessoa

📊 Nível: 🟢 BAIXA



Segmentos:## ⚡ Funcionalidades Principais

  1. GRU → BOG | Avianca AV 86

     🛩️  Aeronave: Boeing 787-8 Dreamliner (788)

  2. BOG → JFK | Avianca AV 244

     🛩️  Aeronave: Airbus A320neo (32N)### 🌱 Busca Sustentável### 👉 [DOCUMENTACAO.md](DOCUMENTACAO.md)### 2️⃣ Executar│   │   ├── TGrafo.java             # Estrutura de grafo para rotas

========================================

```- Integração com API Amadeus



---- Até 10 opções de voos reais



## 🆕 Novidades - Versão 2.0- Ordenação por menor emissão de CO₂



### 🛩️ Sistema de Modelos de AeronavesEste arquivo contém:```bash│   │   └── VooComEmissao.java      # Representação de voo com emissão

- **45+ modelos cadastrados** com eficiência real

- **Cálculo específico por avião** (Boeing 787, Airbus A350, etc.)### 🛩️ Cálculo Preciso (3 Níveis)

- **Exibição do modelo** nos detalhes do voo

- **Precisão aumentada** de 75% para 85-90%1. **✅ Dados Reais da API** - Máxima precisão- ✅ Guia completo de uso



### 🔒 Segurança de Credenciais2. **🛩️ Cálculo por Modelo** - 45+ aviões cadastrados

- **Suporte a arquivo .env** - fácil e seguro

- **Variáveis de ambiente** também suportadas3. **⚠️ Estimativa ICAO** - Fallback genérico- ✅ Arquitetura do sistemamvn exec:java -Dexec.mainClass="codigo.main"│   ├── service/                     # Serviços de negócio

- **Nunca** commitar credenciais no Git

- **`.gitignore`** configurado automaticamente



### 📊 Comparativo de Modelos (GRU → JFK)### 📊 Análise Inteligente- ✅ Detalhes dos cálculos de CO₂

| Modelo | Emissão | Economia |

|--------|---------|----------|- Algoritmo Dijkstra para rota ótima

| A350-1000 | 906 kg | -50% 🟢 |

| 787-9 | 922 kg | -49% 🟢 |- Estatísticas comparativas- ✅ Explicação do algoritmo Dijkstra```│   │   └── FlightCarbonService.java # Serviço de busca e análise de voos

| 737-800 | 1.421 kg | -21% 🟠 |

| A340-300 | 1.805 kg | 0% 🔴 |- Classificação visual (🟢🟡🟠🔴)



---- Modelo específico de cada aeronave- ✅ Documentação da API Amadeus



## 🔗 Links Úteis



- 📖 **[Documentação Completa](DOCUMENTACAO.md)**---- ✅ Histórico de atualizações│   ├── util/                        # Utilitários

- 🔑 **[Configurar Credenciais](CREDENCIAIS.md)**

- 🌐 **[API Amadeus](https://developers.amadeus.com/)**

- 🌱 **[ICAO Carbon Calculator](https://www.icao.int/environmental-protection/Carbonoffset/)**

## 📁 Estrutura- ✅ FAQ e troubleshooting

---



## 🔒 Segurança

```- ✅ Exemplos de saída### 3️⃣ Buscar Voos│   │   └── CarbonEmissionCalculator.java # Cálculo de emissões CO₂

### ⚠️ IMPORTANTE para GitHub:

codigo/

- ✅ Use arquivo **`.env`** (já no `.gitignore`)

- ✅ OU use **variáveis de ambiente**├── src/main/java/codigo/

- ✅ **Nunca** commite credenciais no código

- ✅ Arquivo **`.gitignore`** protege automaticamente│   ├── main.java                    # Aplicação principal



**Seguro para commit público no GitHub!** 🎉│   ├── model/                       # Modelos de dados---Escolha a opção **10** no menu e forneça:│   └── test/                        # Testes



---│   ├── service/                     # Lógica de negócio



## 📞 Suporte│   └── util/                        # Utilitários



### Encontrou um problema?├── data/

- Consulte o **[FAQ na documentação](DOCUMENTACAO.md#-faq)**

- Veja **[Configuração de Credenciais](CREDENCIAIS.md)**│   ├── grafoEntrada.txt            # Dados de entrada## ⚡ Funcionalidades Principais- Origem (ex: GRU)│       └── AmadeusFlightTest.java   # Teste da API Amadeus

- Reporte via GitHub Issues

│   └── grafoSaida.txt              # Dados de saída

### Quer contribuir?

- Sugira novos modelos de avião├── DOCUMENTACAO.md                  # 📖 Documentação completa

- Proponha melhorias

- Envie pull requests├── CREDENCIAIS.md                   # 🔑 Como configurar API



---├── README.md                        # Este arquivo### 🌱 Busca Sustentável- Destino (ex: JFK)├── data/                            # Arquivos de dados



## 📝 Licença└── pom.xml                          # Maven config



Projeto educacional. Consulte licenças das bibliotecas utilizadas.```- Integração com API Amadeus



---



**🌱 Voando com Consciência Ambiental**---- Até 10 opções de voos reais- Data (ex: 2025-12-15)│   ├── grafoEntrada.txt            # Arquivo de entrada do grafo



*Última atualização: 7 de Novembro de 2025*


## 🎯 Exemplo de Resultado- Ordenação por menor emissão de CO₂



```- Passageiros (ex: 1)│   └── grafoSaida.txt              # Arquivo de saída do grafo

╔════════════════════════════════════════════════════════════════╗

║         VOOS ORDENADOS POR EMISSÃO DE CARBONO (MENOR → MAIOR) ║### 🛩️ Cálculo Preciso (3 Níveis)

╚════════════════════════════════════════════════════════════════╝

1. **✅ Dados Reais da API** - Máxima precisão├── docs/                            # Documentação

1. 🟢 BAIXA GRU → JFK | Avianca | 2401.37 BRL | 1 escala(s) | 1.03 toneladas CO₂/pessoa

2. 🟡 MÉDIA GRU → JFK | Air Canada | 2184.59 BRL | 1 escala(s) | 1.09 toneladas CO₂/pessoa2. **🛩️ Cálculo por Modelo** - 45+ aviões cadastrados (NOVO!)



========================================3. **⚠️ Estimativa ICAO** - Fallback genérico---│   ├── AMADEUS_SETUP.md            # Guia de configuração da API

🛫 GRU → JFK 🛬

========================================

✈️  Companhia: Avianca

💰 Preço: 2401.37 BRL### 📊 Análise Inteligente│   ├── CARBON_FLIGHT_GUIDE.md      # Guia completo do sistema

🔄 Escalas: 1

🌱 Emissão CO₂: 1.03 toneladas CO₂ por pessoa- Algoritmo Dijkstra para rota ótima

📊 Nível: 🟢 BAIXA

- Estatísticas comparativas## 📖 Documentação Completa│   └── EXEMPLO_SAIDA.md            # Exemplos de saída

Segmentos:

  1. GRU → BOG | Avianca AV 86- Classificação visual (🟢🟡🟠🔴)

     🛩️  Aeronave: Boeing 787-8 Dreamliner (788)

  2. BOG → JFK | Avianca AV 244- Modelo específico de cada aeronave├── pom.xml                          # Configuração Maven

     🛩️  Aeronave: Airbus A320neo (32N)

========================================

```

---**Toda a documentação está consolidada em um único arquivo:**└── README.md                        # Este arquivo

---



## 🆕 Novidades - Versão 2.0

## 📁 Estrutura```

### 🛩️ Sistema de Modelos de Aeronaves

- **45+ modelos cadastrados** com eficiência real

- **Cálculo específico por avião** (Boeing 787, Airbus A350, etc.)

- **Exibição do modelo** nos detalhes do voo```### 👉 [DOCUMENTACAO.md](DOCUMENTACAO.md)

- **Precisão aumentada** de 75% para 85-90%

codigo/

### 🔒 Segurança de Credenciais

- **Variáveis de ambiente** para API keys├── src/main/java/codigo/## 🚀 Início Rápido

- **Nunca** commitar credenciais no Git

- **`.gitignore`** configurado automaticamente│   ├── main.java                    # Aplicação principal



### 📊 Comparativo de Modelos (GRU → JFK)│   ├── model/                       # Modelos de dadosEste arquivo contém:

| Modelo | Emissão | Economia |

|--------|---------|----------|│   ├── service/                     # Lógica de negócio

| A350-1000 | 906 kg | -50% 🟢 |

| 787-9 | 922 kg | -49% 🟢 |│   └── util/                        # Utilitários- ✅ Guia completo de uso### 1. Pré-requisitos

| 737-800 | 1.421 kg | -21% 🟠 |

| A340-300 | 1.805 kg | 0% 🔴 |├── data/



---│   ├── grafoEntrada.txt            # Dados de entrada- ✅ Arquitetura do sistema



## 🔗 Links Úteis│   └── grafoSaida.txt              # Dados de saída



- 📖 **[Documentação Completa](DOCUMENTACAO.md)**├── DOCUMENTACAO.md                  # 📖 Documentação completa- ✅ Detalhes dos cálculos de CO₂- **Java 11+**

- 🔑 **[Configurar Credenciais](CREDENCIAIS.md)**

- 🌐 **[API Amadeus](https://developers.amadeus.com/)**├── README.md                        # Este arquivo

- 🌱 **[ICAO Carbon Calculator](https://www.icao.int/environmental-protection/Carbonoffset/)**

├── COMO_EXECUTAR.md                 # Guia rápido- ✅ Explicação do algoritmo Dijkstra- **Maven 3.6+**

---

└── pom.xml                          # Maven config

## 🔒 Segurança

```- ✅ Documentação da API Amadeus- **Credenciais da API Amadeus** (gratuitas em https://developers.amadeus.com)

### ⚠️ IMPORTANTE para GitHub:



- ✅ As credenciais usam **variáveis de ambiente**

- ✅ Arquivo **`.gitignore`** já configurado---- ✅ Histórico de atualizações

- ✅ **Seguro** para commit público



**Nunca commite:**

- API Keys ou Secrets diretamente no código## 🎯 Exemplo de Resultado- ✅ FAQ e troubleshooting### 2. Compilar

- Arquivos .env com credenciais

- Credentials.properties ou similares



---```- ✅ Exemplos de saída



## 📞 Suporte╔════════════════════════════════════════════════════════════════╗



### Encontrou um problema?║         VOOS ORDENADOS POR EMISSÃO DE CARBONO (MENOR → MAIOR) ║```bash

- Consulte o **[FAQ na documentação](DOCUMENTACAO.md#-faq)**

- Veja **[Configuração de Credenciais](CREDENCIAIS.md)**╚════════════════════════════════════════════════════════════════╝

- Reporte via GitHub Issues

---cd /Users/francisco/Documents/ProjetoCarbonFlight/codigo

### Quer contribuir?

- Sugira novos modelos de avião1. 🟢 BAIXA GRU → JFK | Avianca | 2401.37 BRL | 1 escala(s) | 1.03 toneladas CO₂/pessoa

- Proponha melhorias

- Envie pull requests2. 🟡 MÉDIA GRU → JFK | Air Canada | 2184.59 BRL | 1 escala(s) | 1.09 toneladas CO₂/pessoamvn clean compile



---



## 📝 Licença========================================## ⚡ Funcionalidades Principais```



Projeto educacional. Consulte licenças das bibliotecas utilizadas.🛫 GRU → JFK 🛬



---========================================



**🌱 Voando com Consciência Ambiental**✈️  Companhia: Avianca



*Última atualização: 7 de Novembro de 2025*💰 Preço: 2401.37 BRL### 🌱 Busca Sustentável### 3. Executar


🔄 Escalas: 1

🌱 Emissão CO₂: 1.03 toneladas CO₂ por pessoa- Integração com API Amadeus

📊 Nível: 🟢 BAIXA

- Até 10 opções de voos reais```bash

Segmentos:

  1. GRU → BOG | Avianca AV 86- Ordenação por menor emissão de CO₂mvn exec:java -Dexec.mainClass="codigo.main"

     🛩️  Aeronave: Boeing 787-8 Dreamliner (788)

  2. BOG → JFK | Avianca AV 244```

     🛩️  Aeronave: Airbus A320neo (32N)

========================================### 🛩️ Cálculo Preciso (3 Níveis)

```

1. **✅ Dados Reais da API** - Máxima precisão## ✨ Funcionalidades

---

2. **🛩️ Cálculo por Modelo** - 45+ aviões cadastrados (NOVO!)

## 🆕 Novidades - Versão 2.0

3. **⚠️ Estimativa ICAO** - Fallback genérico### 🌍 Opção 10 - Buscar Voos Sustentáveis

### 🛩️ Sistema de Modelos de Aeronaves

- **45+ modelos cadastrados** com eficiência real

- **Cálculo específico por avião** (Boeing 787, Airbus A350, etc.)

- **Exibição do modelo** nos detalhes do voo### 📊 Análise InteligenteA funcionalidade principal do sistema:

- **Precisão aumentada** de 75% para 85-90%

- Algoritmo Dijkstra para rota ótima

### 📊 Comparativo de Modelos (GRU → JFK)

| Modelo | Emissão | Economia |- Estatísticas comparativas1. **Busca voos** entre dois aeroportos via API Amadeus

|--------|---------|----------|

| A350-1000 | 906 kg | -50% 🟢 |- Classificação visual (🟢🟡🟠🔴)2. **Calcula emissões de CO₂** para cada voo

| 787-9 | 922 kg | -49% 🟢 |

| 737-800 | 1.421 kg | -21% 🟠 |- Modelo específico de cada aeronave3. **Ordena** do menor ao maior emissor

| A340-300 | 1.805 kg | 0% 🔴 |

4. **Adiciona rotas ao grafo** com emissão como peso

---

---5. **Exibe estatísticas** detalhadas

## 🔗 Links Úteis



- 📖 **[Documentação Completa](DOCUMENTACAO.md)** ← Leia aqui!

- 🌐 **[API Amadeus](https://developers.amadeus.com/)**## 📁 Estrutura### 📊 Menu Completo

- 🌱 **[ICAO Carbon Calculator](https://www.icao.int/environmental-protection/Carbonoffset/)**



---

``````

## 📞 Suporte

codigo/╔════════════════════════════════════════════════════════╗

### Encontrou um problema?

- Consulte o **[FAQ na documentação](DOCUMENTACAO.md#-faq)**├── src/main/java/codigo/║        CARBON FLIGHT - Sistema de Voos Sustentáveis   ║

- Reporte via GitHub Issues

│   ├── main.java                    # Aplicação principal╚════════════════════════════════════════════════════════╝

### Quer contribuir?

- Sugira novos modelos de avião│   ├── model/                       # Modelos de dados1. Ler dados do arquivo grafo.txt

- Proponha melhorias

- Envie pull requests│   ├── service/                     # Lógica de negócio2. Gravar dados no arquivo grafo.txt



---│   └── util/                        # Utilitários3. Inserir vértice



## 📝 Licença├── DOCUMENTACAO.md                  # 📖 Documentação completa4. Inserir aresta



Projeto educacional. Consulte licenças das bibliotecas utilizadas.├── pom.xml                          # Maven config5. Remover vértice



---└── README.md                        # Este arquivo6. Remover aresta



**🌱 Voando com Consciência Ambiental**```7. Mostrar conteúdo do arquivo



*Última atualização: 7 de Novembro de 2025*8. Mostrar grafo


---9. Apresentar a conexidade do grafo e o reduzido

10. 🌱 BUSCAR VOOS POR EMISSÃO DE CARBONO 🌱

## 🎯 Exemplo de Resultado11. Testar API Amadeus

0. Encerrar a aplicação

``````

╔════════════════════════════════════════════════════════════════╗

║         VOOS ORDENADOS POR EMISSÃO DE CARBONO (MENOR → MAIOR) ║## 🎯 Exemplo de Uso

╚════════════════════════════════════════════════════════════════╝

```bash

1. 🟢 BAIXA GRU → JFK | Avianca | 2401.37 BRL | 1 escala(s) | 1.03 toneladas CO₂/pessoa# Escolha opção 10 no menu

2. 🟡 MÉDIA GRU → JFK | Air Canada | 2184.59 BRL | 1 escala(s) | 1.09 toneladas CO₂/pessoa# Informe:

Aeroporto de origem: GRU

========================================Aeroporto de destino: JFK

🛫 GRU → JFK 🛬Data de partida: 2025-12-15

========================================Número de adultos: 1

✈️  Companhia: Avianca

💰 Preço: 2401.37 BRL# O sistema retorna:

🔄 Escalas: 1╔════════════════════════════════════════════════════════════════╗

🌱 Emissão CO₂: 1.03 toneladas CO₂ por pessoa║         VOOS ORDENADOS POR EMISSÃO DE CARBONO (MENOR → MAIOR) ║

📊 Nível: 🟢 BAIXA╚════════════════════════════════════════════════════════════════╝



Segmentos:1. 🟡 MÉDIA GRU → JFK | 2150.15 BRL | 1 escala(s) | 8210 km | 1.34 toneladas CO₂

  1. GRU → BOG | Avianca AV 862. 🟡 MÉDIA GRU → JFK | 2184.59 BRL | 1 escala(s) | 8550 km | 1.40 toneladas CO₂

     🛩️  Aeronave: Boeing 787-8 Dreamliner (788)3. 🟠 ALTA  GRU → JFK | 2401.37 BRL | 1 escala(s) | 8250 km | 1.42 toneladas CO₂

  2. BOG → JFK | Avianca AV 244

     🛩️  Aeronave: Airbus A320neo (32N)Estatísticas:

========================================🌱 Melhor opção: 1.34 toneladas CO₂

```🔴 Pior opção: 1.42 toneladas CO₂

📊 Diferença: 80.00 kg CO₂ (6.0% mais carbono)

---```



## 🆕 Novidades - Versão 2.0## 🏗️ Arquitetura



### 🛩️ Sistema de Modelos de Aeronaves### Model (`codigo.model`)

- **45+ modelos cadastrados** com eficiência real- **TGrafo**: Estrutura de dados de grafo com matriz de adjacência

- **Cálculo específico por avião** (Boeing 787, Airbus A350, etc.)- **VooComEmissao**: Encapsula voo com cálculo de emissão

- **Exibição do modelo** nos detalhes do voo

- **Precisão aumentada** de 75% para 85-90%### Service (`codigo.service`)

- **FlightCarbonService**: Integra API Amadeus + cálculo + grafo

### 📊 Comparativo de Modelos (GRU → JFK)

| Modelo | Emissão | Economia |### Util (`codigo.util`)

|--------|---------|----------|- **CarbonEmissionCalculator**: Cálculos de emissão CO₂

| A350-1000 | 906 kg | -50% 🟢 |

| 787-9 | 922 kg | -49% 🟢 |### Test (`codigo.test`)

| 737-800 | 1.421 kg | -21% 🟠 |- **AmadeusFlightTest**: Testes da API

| A340-300 | 1.805 kg | 0% 🔴 |

## 🌍 Cálculo de Emissões

---

### Fatores de Emissão (kg CO₂ por km por passageiro)

## 🔗 Links Úteis

- **Curta distância** (< 1500 km): 0.255 kg/km

- 📖 **[Documentação Completa](DOCUMENTACAO.md)** ← Leia aqui!- **Média distância** (1500-4000 km): 0.195 kg/km

- 🌐 **[API Amadeus](https://developers.amadeus.com/)**- **Longa distância** (> 4000 km): 0.150 kg/km

- 🌱 **[ICAO Carbon Calculator](https://www.icao.int/environmental-protection/Carbonoffset/)**

### Penalidades

---

- **Cada escala**: +50 kg CO₂ (decolagem/pouso extra)

## 📞 Suporte

### Classificação

### Encontrou um problema?

- Consulte o **[FAQ na documentação](DOCUMENTACAO.md#-faq)**- 🟢 **BAIXA**: < 500 kg CO₂

- Reporte via GitHub Issues- 🟡 **MÉDIA**: 500-1000 kg CO₂

- 🟠 **ALTA**: 1000-2000 kg CO₂

### Quer contribuir?- 🔴 **MUITO ALTA**: > 2000 kg CO₂

- Sugira novos modelos de avião

- Proponha melhorias## 🔧 Configuração

- Envie pull requests

### Credenciais da API

---

Edite `src/main/java/codigo/main.java`:

## 📝 Licença

```java

Projeto educacional. Consulte licenças das bibliotecas utilizadas.private static final String API_KEY = "sua_api_key";

private static final String API_SECRET = "seu_api_secret";

---```



**🌱 Voando com Consciência Ambiental**## 📚 Documentação Adicional



*Última atualização: 7 de Novembro de 2025*- **[AMADEUS_SETUP.md](docs/AMADEUS_SETUP.md)** - Setup completo da API

- **[CARBON_FLIGHT_GUIDE.md](docs/CARBON_FLIGHT_GUIDE.md)** - Guia detalhado
- **[EXEMPLO_SAIDA.md](docs/EXEMPLO_SAIDA.md)** - Exemplos de saída

## 🛠️ Tecnologias

- **Java 11**
- **Maven 3.9+**
- **Amadeus SDK 8.1.0** - API de busca de voos
- **Gson 2.10.1** - Processamento JSON

## 📦 Dependências

```xml
<dependency>
    <groupId>com.amadeus</groupId>
    <artifactId>amadeus-java</artifactId>
    <version>8.1.0</version>
</dependency>

<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
```
## 📖 Códigos IATA Comuns

| Código | Aeroporto | Cidade |
|--------|-----------|--------|
| GRU | Guarulhos | São Paulo |
| GIG | Galeão | Rio de Janeiro |
| BSB | Juscelino Kubitschek | Brasília |
| JFK | John F. Kennedy | Nova York |
| LAX | Los Angeles | Los Angeles |
| MIA | Miami | Miami |
| BOG | El Dorado | Bogotá |
| YUL | Trudeau | Montreal |
| YYZ | Pearson | Toronto |

## 👨‍💻 Autor

**Francisco Losada**
- Universidade Presbiteriana Mackenzie
- Projeto: CarbonFlight

## 📝 Licença

Projeto acadêmico

---
