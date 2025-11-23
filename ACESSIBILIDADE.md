# ♿ Guia de Acessibilidade - CarbonFlight

## 📋 Visão Geral

O CarbonFlight inclui um **sistema completo de acessibilidade** para garantir que todos os usuários possam utilizar a plataforma de forma confortável e eficiente, independentemente de suas necessidades específicas.

---

## 🎯 Recursos de Acessibilidade

### 1. **♿ Botão de Acessibilidade**
- **Localização:** Canto inferior direito da tela (botão flutuante)
- **Ícone:** ♿ (símbolo universal de acessibilidade)
- **Função:** Abre o painel de acessibilidade
- **Atalho:** `Alt + A`

### 2. **🎨 Modo de Alto Contraste**
- **Função:** Muda para fundo preto com texto branco
- **Ideal para:** 
  - Usuários com baixa visão
  - Ambientes com muita luminosidade
  - Redução de cansaço visual
- **Como ativar:** 
  - Clique no botão ♿ → "Alto Contraste"
  - Atalho: `Alt + C`
- **Persistência:** A configuração é salva automaticamente

### 3. **🔤 Ajuste de Tamanho de Fonte**
Quatro opções disponíveis:
- **Pequena** (14px) - Para telas grandes
- **Normal** (16px) - Padrão do sistema
- **Grande** (18px) - Melhor legibilidade
- **Muito Grande** (22px) - Para usuários com baixa visão

**Como ajustar:**
- Painel de acessibilidade → Selecionar tamanho
- Atalhos:
  - `Alt + +` (aumentar)
  - `Alt + -` (diminuir)

### 4. **📖 Modo de Leitura Simplificado**
- **Função:** Otimiza a interface para leitura
- **Características:**
  - Fonte serifada (Georgia/Times)
  - Fundo bege claro
  - Espaçamento de linha aumentado
  - Elementos decorativos atenuados
- **Ideal para:** 
  - Leitura prolongada
  - Usuários com dislexia
  - Redução de distrações visuais
- **Atalho:** `Alt + R`

### 5. **⌨️ Atalhos de Teclado**
Navegação completa sem mouse:

| Atalho | Função |
|--------|--------|
| `Alt + A` | Abrir/Fechar menu de acessibilidade |
| `Alt + C` | Ativar/Desativar alto contraste |
| `Alt + +` | Aumentar tamanho da fonte |
| `Alt + -` | Diminuir tamanho da fonte |
| `Alt + R` | Ativar/Desativar modo de leitura |
| `Alt + K` | Mostrar/Ocultar lista de atalhos |
| `Esc` | Fechar menus abertos |
| `Tab` | Navegar entre elementos |
| `Enter` | Ativar botões/links |

**Ver atalhos:** Clique no botão ♿ → "Ver Atalhos" ou pressione `Alt + K`

### 6. **🔗 Pular para Conteúdo**
- **Função:** Link invisível que aparece ao pressionar `Tab`
- **Uso:** Permite pular navegação e ir direto ao conteúdo
- **Ideal para:** Usuários de leitores de tela e navegação por teclado

### 7. **🎯 Indicadores de Foco Melhorados**
- Contorno azul brilhante ao navegar com `Tab`
- Maior visibilidade de elementos focados
- Offset de 2px para não sobrepor conteúdo

### 8. **📢 Anúncios para Leitores de Tela**
- Sistema de anúncios ARIA
- Informa mudanças de estado
- Exemplos:
  - "Alto contraste ativado"
  - "Fonte grande selecionada"
  - "Modo de leitura ativado"

### 9. **💾 Persistência de Configurações**
- Todas as configurações são salvas automaticamente
- Utiliza LocalStorage do navegador
- Configurações mantidas entre sessões
- Restauração automática ao voltar ao site

### 10. **🔄 Restaurar Padrões**
- **Função:** Volta todas as configurações ao padrão
- **Como usar:** Painel de acessibilidade → "Restaurar Padrões"
- **Efeito:** Remove todas as personalizações

---

## 🚀 Como Usar

### Primeira Vez
1. Clique no botão **♿** no canto inferior direito
2. Explore as opções disponíveis
3. Teste diferentes configurações
4. Suas preferências serão salvas automaticamente

### Acesso Rápido
```
Alt + A → Abre menu
Alt + C → Alto contraste ON/OFF
Alt + + → Fonte maior
Alt + - → Fonte menor
Alt + R → Modo leitura ON/OFF
```

### Navegação por Teclado
1. Pressione `Tab` para navegar
2. Use `Enter` ou `Espaço` para ativar botões
3. Pressione `Esc` para fechar menus

---

## 🎨 Exemplos Visuais

### Modo Normal
- Fundo: Bege claro (#EBD7A5)
- Texto: Cinza escuro (#333)
- Navbar: Gradiente marrom

### Modo Alto Contraste
- Fundo: Preto (#000000)
- Texto: Branco (#FFFFFF)
- Bordas: Brancas
- Botões: Brancos com texto preto

### Modo de Leitura
- Fundo: Bege claro (#F5F5DC)
- Fonte: Georgia (serifada)
- Espaçamento: Aumentado (1.8)
- Elementos: Atenuados

---

## 🌐 Padrões de Acessibilidade

O sistema foi desenvolvido seguindo as diretrizes:

### WCAG 2.1 (Web Content Accessibility Guidelines)
- ✅ **Nível AA** - Contraste mínimo 4.5:1
- ✅ **Nível AAA** - Contraste alto 7:1 (modo alto contraste)
- ✅ Navegação por teclado completa
- ✅ Indicadores de foco visíveis
- ✅ Textos alternativos em elementos interativos
- ✅ ARIA labels e roles apropriados

### WAI-ARIA (Accessible Rich Internet Applications)
- ✅ `role="dialog"` no painel de acessibilidade
- ✅ `aria-label` em botões
- ✅ `aria-live` para anúncios dinâmicos
- ✅ `aria-expanded` em elementos expansíveis

### Semântica HTML5
- ✅ Tags semânticas (`<header>`, `<nav>`, `<main>`, `<footer>`)
- ✅ Estrutura hierárquica de títulos
- ✅ Labels associados a inputs
- ✅ Botões com descrições claras

---

## 🧪 Testado Com

### Leitores de Tela
- ✅ **NVDA** (Windows)
- ✅ **JAWS** (Windows)
- ✅ **VoiceOver** (macOS/iOS)
- ✅ **TalkBack** (Android)

### Navegadores
- ✅ Chrome/Edge (Chromium)
- ✅ Firefox
- ✅ Safari
- ✅ Opera

### Dispositivos
- ✅ Desktop (1920x1080 e superiores)
- ✅ Laptop (1366x768 e superiores)
- ✅ Tablet (768px e superiores)
- ✅ Mobile (320px e superiores)

---

## 💡 Dicas de Uso

### Para Usuários com Baixa Visão
1. Ative **Alto Contraste** (`Alt + C`)
2. Aumente a **fonte** para "Muito Grande" (`Alt + +`)
3. Use **zoom do navegador** (Ctrl + ou Cmd +)

### Para Usuários com Dislexia
1. Ative **Modo de Leitura** (`Alt + R`)
2. Use fonte **Grande** ou **Muito Grande**
3. Considere usar extensões de navegador específicas

### Para Navegação por Teclado
1. Use `Tab` para navegar entre elementos
2. Use `Shift + Tab` para voltar
3. Pressione `Alt + K` para ver todos os atalhos
4. Use `Enter` para ativar links/botões

### Para Ambientes Claros
1. Ative **Alto Contraste** para melhor visibilidade
2. Aumente o **brilho da tela**
3. Use **Modo de Leitura** se necessário

---

## 🔧 Solução de Problemas

### Configurações Não Salvam
- Verifique se o navegador permite cookies/LocalStorage
- Tente em modo anônimo para testar
- Limpe cache e tente novamente

### Alto Contraste Não Funciona
- Verifique se não há extensões conflitantes
- Desative "modo escuro" do navegador
- Recarregue a página (F5)

### Atalhos Não Funcionam
- Verifique se não há conflitos com atalhos do SO
- No Mac, use `Option` ao invés de `Alt`
- Certifique-se de que a página está em foco

### Botão de Acessibilidade Não Aparece
- Recarregue a página com `Ctrl + Shift + R`
- Verifique se JavaScript está habilitado
- Teste em outro navegador

---

## 📞 Feedback e Sugestões

Encontrou algum problema de acessibilidade? Tem sugestões de melhorias?

**Contribua para um CarbonFlight mais acessível!**

---

## 📚 Recursos Adicionais

### Links Úteis
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
- [WAI-ARIA Practices](https://www.w3.org/WAI/ARIA/apg/)
- [MDN Accessibility](https://developer.mozilla.org/en-US/docs/Web/Accessibility)

### Ferramentas de Teste
- **axe DevTools** (extensão Chrome/Firefox)
- **WAVE** (Web Accessibility Evaluation Tool)
- **Lighthouse** (Chrome DevTools)

---

**✅ Acessibilidade é direito de todos!**

*CarbonFlight - Voos Sustentáveis e Acessíveis* 🌍♿
