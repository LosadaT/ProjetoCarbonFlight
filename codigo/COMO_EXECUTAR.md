# 🚀 Guia Rápido - CarbonFlight

## 📋 Índice
1. [Como Criar uma Chave da API Amadeus](#1-como-criar-uma-chave-da-api-amadeus)
2. [Como Compilar o Código](#2-como-compilar-o-código)
3. [Como Rodar no Localhost (Interface Web)](#3-como-rodar-no-localhost-interface-web)
4. [Como Rodar no Console](#4-como-rodar-no-console)

---

## 1. 🔑 Como Criar uma Chave da API Amadeus

### Passo 1: Criar Conta
1. Acesse: https://developers.amadeus.com/register
2. Preencha seus dados (nome, email, senha)
3. Confirme seu email

### Passo 2: Criar Aplicativo
1. Faça login em: https://developers.amadeus.com/login
2. Clique em **"Create new app"**
3. Dê um nome (ex: "CarbonFlight")
4. Clique em **"Create"**

### Passo 3: Copiar as Chaves
Você verá duas chaves:
- **API Key** (começa com algo como `AbCd1234...`)
- **API Secret** (uma string longa)

### Passo 4: Configurar no Projeto
Crie o arquivo `.env` na raiz do projeto e coloque suas chaves:

```env
AMADEUS_API_KEY=sua_api_key_aqui
AMADEUS_API_SECRET=sua_api_secret_aqui
```
---

## 2. ⚙️ Como Compilar o Código

### Opção 1: Compilar apenas
```bash
mvn clean compile
```


---

## 3. 🌐 Como Rodar no Localhost (Interface Web)

### Método 1: Com Maven (Recomendado)
```bash
mvn spring-boot:run
```

### Acessar a Aplicação
1. Abra seu navegador
2. Acesse: **http://localhost:8080**
3. Clique em **"Buscar Voos"**
4. Preencha o formulário e busque voos!

### Como Parar a Aplicação
- Pressione `Ctrl + C` no terminal

### Problemas Comuns

**Porta 8080 ocupada?**
```bash
# Ver o que está usando a porta
lsof -i :8080

# Matar o processo (substitua PID pelo número mostrado)
kill -9 PID
```

**Cache do navegador?**
- Pressione `Ctrl + Shift + R` (Windows/Linux)
- Pressione `Cmd + Shift + R` (Mac)

---

## 4. 💻 Como Rodar no Console

### Opção 1: Com Maven
```bash
mvn exec:java -Dexec.mainClass="codigo.main"
```

### Opção 2: Compilar e Executar
```bash
# Compilar
mvn clean compile

# Executar
java -cp target/classes codigo.main
```

## 📝 Resumo dos Comandos

| Ação | Comando |
|------|---------|
| Compilar | `mvn compile` |
| Limpar e compilar | `mvn clean compile` |
| Rodar interface web | `mvn spring-boot:run` |
| Rodar no console | `mvn exec:java -Dexec.mainClass="codigo.main"` |
| Criar JAR | `mvn clean package` |
| Ver porta 8080 | `lsof -i :8080` |
| Parar aplicação | `Ctrl + C` |
