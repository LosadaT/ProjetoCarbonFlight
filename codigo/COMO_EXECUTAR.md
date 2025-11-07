# 🚀 Como Executar# 🚀 Como Executar o Carbon Flight



> **📖 Documentação Completa:** [DOCUMENTACAO.md](DOCUMENTACAO.md)## Opção 1: Executar Diretamente (Recomendado)



## Início Rápido```bash

cd /Users/francisco/Documents/ProjetoCarbonFlight/codigo

```bashmvn exec:java -Dexec.mainClass="codigo.main"

mvn exec:java -Dexec.mainClass="codigo.main"```

```

Depois escolha a opção desejada no menu interativo.

Escolha a opção **10** para buscar voos por emissão de carbono.

---

---

## Opção 2: Usar Script de Teste

Para instruções detalhadas, exemplos, FAQ e documentação completa:

```bash

## 👉 [DOCUMENTACAO.md](DOCUMENTACAO.md)./testar.sh

```

---

Este script oferece:

*Última atualização: 7 de Novembro de 2025*- 1️⃣ Teste completo automático (GRU → JFK)

- 2️⃣ Modo interativo completo
- 3️⃣ Teste da API Amadeus
- 0️⃣ Cancelar

---

## Opção 3: Compilar e Executar Manualmente

### Compilar
```bash
mvn clean compile
```

### Executar
```bash
mvn exec:java -Dexec.mainClass="codigo.main"
```

---

## 🌱 Buscar Voos por Emissão de Carbono

No menu principal, escolha a opção **10**:

```
Escolha uma opção: 10
```

Depois forneça:
1. **Origem**: Código IATA (ex: GRU, JFK, LHR, CDG)
2. **Destino**: Código IATA
3. **Data**: Formato YYYY-MM-DD (ex: 2025-12-15)
4. **Adultos**: Número de passageiros (ex: 1)

### Exemplo Completo:
```
Escolha uma opção: 10

Aeroporto de origem: GRU
Aeroporto de destino: JFK
Data de partida: 2025-12-15
Número de adultos: 1
```

O sistema irá:
1. ✅ Buscar voos na API Amadeus
2. ✅ Calcular emissões de CO₂ (reais ou estimadas)
3. ✅ Ordenar por menor emissão
4. ✅ Mostrar ranking com estatísticas
5. ✅ Adicionar rotas ao grafo

---

## 🧪 Testar API Amadeus

No menu principal, escolha a opção **11**:

```
Escolha uma opção: 11
```

Isso fará uma busca de teste GRU → JFK.

---

## 📋 Códigos IATA Comuns

### Brasil
- **GRU** - Guarulhos, São Paulo
- **GIG** - Galeão, Rio de Janeiro
- **BSB** - Brasília
- **SSA** - Salvador

### Estados Unidos
- **JFK** - John F. Kennedy, Nova York
- **LAX** - Los Angeles
- **MIA** - Miami
- **ORD** - Chicago

### Europa
- **LHR** - Londres Heathrow
- **CDG** - Paris Charles de Gaulle
- **MAD** - Madrid
- **BCN** - Barcelona
- **FCO** - Roma Fiumicino

---

## ⚠️ Notas Importantes

### Sobre Dados de CO₂

O sistema usa uma abordagem híbrida de **3 níveis** para máxima precisão:

1. **✅ Dados Reais da API**: Quando API Amadeus fornece (máxima precisão)
2. **🛩️ Cálculo por Modelo de Aeronave**: Usa eficiência específica de cada avião (45+ modelos cadastrados)
3. **⚠️ Estimativa Genérica**: Baseado em metodologia ICAO quando modelo é desconhecido

**Novo!** O sistema agora mostra o modelo específico de cada aeronave nos detalhes do voo, permitindo que você escolha voos com aviões mais eficientes (ex: Boeing 787 Dreamliner, Airbus A350).

Atualmente, a API não está retornando dados reais de CO₂, então o sistema usa cálculos por modelo de aeronave com precisão de ~85-90%.

### Sobre Erros de Input

Se você receber `NoSuchElementException`:
- **Causa**: Scanner tentou ler mas não havia entrada disponível
- **Solução**: Use o modo interativo ou forneça todas as entradas necessárias

---

## 🐛 Solução de Problemas

### Erro: "BUILD FAILURE"
```bash
# Limpar e recompilar
mvn clean compile
```

### Erro: "NoSuchElementException"
- Isso ocorre quando entrada automatizada termina antes do esperado
- **Solução**: Use modo interativo normal

### Erro: "API Error"
- Verifique se as credenciais estão corretas em `main.java`
- Verifique conexão com internet
- A API Amadeus pode estar temporariamente indisponível

### Programa não inicia
```bash
# Verificar Java
java -version

# Verificar Maven
mvn -version

# Recompilar
mvn clean compile
```

---

## 📊 Exemplo de Saída

### Lista de Voos
```
╔════════════════════════════════════════════════════════════════╗
║         VOOS ORDENADOS POR EMISSÃO DE CARBONO (MENOR → MAIOR) ║
╚════════════════════════════════════════════════════════════════╝

1. � BAIXA GRU → JFK | Avianca | 2401.37 BRL | 1 escala(s) | 1.03 toneladas CO₂/pessoa
2. � MÉDIA GRU → JFK | Air Canada | 2184.59 BRL | 1 escala(s) | 1.09 toneladas CO₂/pessoa
3. 🟠 ALTA GRU → JFK | Copa Airlines | 2150.15 BRL | 1 escala(s) | 1.19 toneladas CO₂/pessoa

╔════════════════════════════════════════════════════════════════╗
║                  ESTATÍSTICAS (POR PESSOA)                     ║
╚════════════════════════════════════════════════════════════════╝

🌱 Melhor opção: 1.03 toneladas CO₂ por pessoa
🔴 Pior opção: 1.19 toneladas CO₂ por pessoa
📊 Média: 1.10 toneladas CO₂ por pessoa
💡 Diferença: 164.00 kg CO₂ (15.9% mais carbono)
```

### Detalhes do Voo
```
========================================
🛫 GRU → JFK 🛬
========================================
✈️  Companhia: Avianca
💰 Preço: 2401.37 BRL
🔄 Escalas: 1
🌱 Emissão CO₂: 1.03 toneladas CO₂ por pessoa
📊 Nível: 🟢 BAIXA

Segmentos:
  1. GRU → BOG | Avianca AV 86
     🛩️  Aeronave: Boeing 787-8 Dreamliner (788)
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
