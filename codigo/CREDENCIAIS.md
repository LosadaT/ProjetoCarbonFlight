# 🔑 Configuração das Credenciais da API Amadeus

## Como Obter suas Credenciais

1. Acesse: https://developers.amadeus.com
2. Crie uma conta gratuita
3. Crie um novo aplicativo (Self-Service)
4. Copie o **API Key** e **API Secret**

---

## 📋 Como Configurar (3 Opções)

### ⭐ Opção 1: Arquivo .env (RECOMENDADO - Mais Fácil!)

#### Passo 1: Criar o arquivo
```bash
cp .env.example .env
```

#### Passo 2: Editar com suas credenciais
Abra o arquivo `.env` e substitua:
```bash
AMADEUS_API_KEY=sua_chave_aqui
AMADEUS_API_SECRET=seu_secret_aqui
```

**Exemplo:**
```bash
AMADEUS_API_KEY=GGBihUcgJWnEk27bGShD1TU6spnzLwLr
AMADEUS_API_SECRET=pICVKyhE6mkMA5Rs
```

#### Passo 3: Executar o projeto
```bash
mvn exec:java -Dexec.mainClass="codigo.main"
```

✅ **Pronto!** O arquivo `.env` já está no `.gitignore` e não será commitado.

---

### Opção 2: Variáveis de Ambiente Temporárias

#### macOS/Linux:
```bash
export AMADEUS_API_KEY="sua_chave_aqui"
export AMADEUS_API_SECRET="seu_secret_aqui"
```
**Nota:** Válido apenas para a sessão atual do terminal.

---

### Opção 3: Variáveis de Ambiente Permanentes

#### Para zsh (macOS Catalina+):
```bash
echo 'export AMADEUS_API_KEY="sua_chave_aqui"' >> ~/.zshrc
echo 'export AMADEUS_API_SECRET="seu_secret_aqui"' >> ~/.zshrc
source ~/.zshrc
```

#### Para bash (Linux/macOS antigo):
```bash
echo 'export AMADEUS_API_KEY="sua_chave_aqui"' >> ~/.bashrc
echo 'export AMADEUS_API_SECRET="seu_secret_aqui"' >> ~/.bashrc
source ~/.bashrc
```

---

## Como Configurar (Windows)

### Opção 1: PowerShell Temporário
```powershell
$env:AMADEUS_API_KEY="sua_chave_aqui"
$env:AMADEUS_API_SECRET="seu_secret_aqui"
```

### Opção 2: Permanente
1. Abra **Painel de Controle** → **Sistema** → **Configurações avançadas do sistema**
2. Clique em **Variáveis de Ambiente**
3. Em **Variáveis do usuário**, clique em **Novo**
4. Adicione:
   - Nome: `AMADEUS_API_KEY`
   - Valor: `sua_chave_aqui`
5. Repita para `AMADEUS_API_SECRET`
6. Reinicie o terminal/IDE

---

## Verificar se Está Configurado

### macOS/Linux:
```bash
echo $AMADEUS_API_KEY
echo $AMADEUS_API_SECRET
```

### Windows (PowerShell):
```powershell
echo $env:AMADEUS_API_KEY
echo $env:AMADEUS_API_SECRET
```

Se aparecer suas chaves, está configurado corretamente! ✅

---

## ⚠️ IMPORTANTE - Segurança

- ❌ **NUNCA** commite suas chaves no Git
- ❌ **NUNCA** compartilhe suas credenciais
- ✅ Use variáveis de ambiente
- ✅ O arquivo `.gitignore` já está configurado para proteger suas credenciais

---

## Executar o Projeto

Após configurar as variáveis de ambiente:

```bash
mvn exec:java -Dexec.mainClass="codigo.main"
```

Escolha a opção **10** para buscar voos.

---

## Solução de Problemas

### Erro: "Credenciais da API não configuradas"
- Verifique se as variáveis de ambiente estão definidas (veja comando acima)
- Reinicie o terminal após adicionar ao ~/.zshrc ou ~/.bashrc
- No Windows, reinicie o terminal/IDE após configurar

### Erro: "Invalid credentials" da API
- Verifique se copiou as credenciais corretamente
- Confirme que está usando API Key e API Secret (não Client ID)
- Verifique se o aplicativo está em modo "Test" no portal Amadeus

---

**Última atualização:** 7 de Novembro de 2025
