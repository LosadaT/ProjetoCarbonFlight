// ========================================
// SISTEMA DE ACESSIBILIDADE - CarbonFlight
// ========================================

class AccessibilityManager {
    constructor() {
        this.settings = {
            highContrast: false,
            fontSize: 'medium' // small, medium, large, xlarge
        };
        
        // Carregar configurações salvas
        this.loadSettings();
        
        // Inicializar interface
        this.init();
        
        // Configurar atalhos de teclado
        this.setupKeyboardShortcuts();
    }
    
    init() {
        // Criar botão de toggle de acessibilidade
        this.createAccessibilityToggle();
        
        // Criar barra de acessibilidade
        this.createAccessibilityBar();
        
        // Criar link "Pular para conteúdo"
        this.createSkipLink();
        
        // Criar ajuda de atalhos (inicialmente oculta)
        this.createKeyboardHelp();
        
        // Aplicar configurações salvas
        this.applySettings();
    }
    
    createAccessibilityToggle() {
        const toggle = document.createElement('button');
        toggle.className = 'accessibility-toggle';
        toggle.innerHTML = '♿';
        toggle.setAttribute('aria-label', 'Abrir menu de acessibilidade');
        toggle.setAttribute('title', 'Menu de Acessibilidade (Alt+A)');
        toggle.onclick = () => this.toggleAccessibilityBar();
        document.body.appendChild(toggle);
    }
    
    createAccessibilityBar() {
        const bar = document.createElement('div');
        bar.className = 'accessibility-bar';
        bar.setAttribute('role', 'dialog');
        bar.setAttribute('aria-label', 'Painel de Acessibilidade');
        
        bar.innerHTML = `
            <button class="close-accessibility" aria-label="Fechar menu de acessibilidade">×</button>
            <h3>♿ Acessibilidade</h3>
            
            <div class="accessibility-section">
                <h4>🎨 Contraste</h4>
                <button class="accessibility-button" data-action="toggleContrast">
                    <span>⚫</span>
                    <span>Alto Contraste</span>
                </button>
            </div>
            
            <div class="accessibility-section">
                <h4>🔤 Tamanho da Fonte</h4>
                <button class="accessibility-button" data-action="fontSmall">
                    <span>A</span>
                    <span>Pequena</span>
                </button>
                <button class="accessibility-button active" data-action="fontMedium">
                    <span>A</span>
                    <span>Normal</span>
                </button>
                <button class="accessibility-button" data-action="fontLarge">
                    <span>A</span>
                    <span>Grande</span>
                </button>
                <button class="accessibility-button" data-action="fontXLarge">
                    <span>A</span>
                    <span>Muito Grande</span>
                </button>
            </div>
            
            <div class="accessibility-section">
                <h4>⌨️ Atalhos de Teclado</h4>
                <button class="accessibility-button" data-action="showShortcuts">
                    <span>⌨️</span>
                    <span>Ver Atalhos (Alt+K)</span>
                </button>
            </div>
            
            <div class="accessibility-section">
                <h4>🔄 Restaurar</h4>
                <button class="accessibility-button" data-action="resetSettings">
                    <span>🔄</span>
                    <span>Restaurar Padrões</span>
                </button>
            </div>
        `;
        
        document.body.appendChild(bar);
        
        // Event listeners
        bar.querySelector('.close-accessibility').onclick = () => this.toggleAccessibilityBar();
        
        bar.querySelectorAll('.accessibility-button').forEach(btn => {
            btn.onclick = () => this.handleAction(btn.getAttribute('data-action'));
        });
    }
    
    createSkipLink() {
        const skipLink = document.createElement('a');
        skipLink.href = '#main-content';
        skipLink.className = 'skip-to-content';
        skipLink.textContent = 'Pular para o conteúdo principal';
        document.body.insertBefore(skipLink, document.body.firstChild);
        
        // Adicionar ID ao main se não existir
        const main = document.querySelector('main');
        if (main && !main.id) {
            main.id = 'main-content';
            main.setAttribute('tabindex', '-1');
        }
    }
    
    createKeyboardHelp() {
        const help = document.createElement('div');
        help.className = 'keyboard-help';
        help.innerHTML = `
            <h4>⌨️ Atalhos de Teclado</h4>
            <ul>
                <li><kbd>Alt</kbd> + <kbd>A</kbd> - Abrir Acessibilidade</li>
                <li><kbd>Alt</kbd> + <kbd>C</kbd> - Alto Contraste</li>
                <li><kbd>Alt</kbd> + <kbd>+</kbd> - Aumentar Fonte</li>
                <li><kbd>Alt</kbd> + <kbd>-</kbd> - Diminuir Fonte</li>
                <li><kbd>Alt</kbd> + <kbd>K</kbd> - Mostrar/Ocultar Atalhos</li>
                <li><kbd>Esc</kbd> - Fechar Menus</li>
            </ul>
        `;
        document.body.appendChild(help);
    }
    
    toggleAccessibilityBar() {
        const bar = document.querySelector('.accessibility-bar');
        bar.classList.toggle('open');
        
        // Focar no primeiro botão quando abrir
        if (bar.classList.contains('open')) {
            setTimeout(() => {
                bar.querySelector('.accessibility-button').focus();
            }, 300);
        }
    }
    
    handleAction(action) {
        switch(action) {
            case 'toggleContrast':
                this.toggleHighContrast();
                break;
            case 'fontSmall':
                this.setFontSize('small');
                break;
            case 'fontMedium':
                this.setFontSize('medium');
                break;
            case 'fontLarge':
                this.setFontSize('large');
                break;
            case 'fontXLarge':
                this.setFontSize('xlarge');
                break;
            case 'showShortcuts':
                this.toggleKeyboardHelp();
                break;
            case 'resetSettings':
                this.resetSettings();
                break;
        }
        
        // Atualizar botões ativos
        this.updateActiveButtons();
    }
    
    toggleHighContrast() {
        this.settings.highContrast = !this.settings.highContrast;
        document.body.classList.toggle('high-contrast', this.settings.highContrast);
        this.saveSettings();
        this.announce(this.settings.highContrast ? 'Alto contraste ativado' : 'Alto contraste desativado');
    }
    
    setFontSize(size) {
        // Remover classes anteriores
        document.body.classList.remove('font-small', 'font-medium', 'font-large', 'font-xlarge');
        
        // Adicionar nova classe
        document.body.classList.add(`font-${size}`);
        this.settings.fontSize = size;
        this.saveSettings();
        
        const sizeNames = {
            small: 'pequena',
            medium: 'normal',
            large: 'grande',
            xlarge: 'muito grande'
        };
        this.announce(`Fonte ${sizeNames[size]}`);
    }
    
    increaseFontSize() {
        const sizes = ['small', 'medium', 'large', 'xlarge'];
        const currentIndex = sizes.indexOf(this.settings.fontSize);
        if (currentIndex < sizes.length - 1) {
            this.setFontSize(sizes[currentIndex + 1]);
        }
    }
    
    decreaseFontSize() {
        const sizes = ['small', 'medium', 'large', 'xlarge'];
        const currentIndex = sizes.indexOf(this.settings.fontSize);
        if (currentIndex > 0) {
            this.setFontSize(sizes[currentIndex - 1]);
        }
    }
    
    toggleKeyboardHelp() {
        const help = document.querySelector('.keyboard-help');
        help.classList.toggle('show');
        
        // Auto-ocultar após 10 segundos
        if (help.classList.contains('show')) {
            setTimeout(() => help.classList.remove('show'), 10000);
        }
    }
    
    resetSettings() {
        this.settings = {
            highContrast: false,
            fontSize: 'medium'
        };
        this.applySettings();
        this.saveSettings();
        this.announce('Configurações de acessibilidade restauradas');
    }
    
    applySettings() {
        // Aplicar alto contraste
        // Aplicar alto contraste
        document.body.classList.toggle('high-contrast', this.settings.highContrast);
        
        // Aplicar tamanho de fonte
        document.body.classList.remove('font-small', 'font-medium', 'font-large', 'font-xlarge');
        document.body.classList.add(`font-${this.settings.fontSize}`);
        
        // Atualizar botões
        this.updateActiveButtons();
    }
    
    updateActiveButtons() {
        // Atualizar botão de contraste
        const contrastBtn = document.querySelector('[data-action="toggleContrast"]');
        if (contrastBtn) {
            contrastBtn.classList.toggle('active', this.settings.highContrast);
        }
        
        // Atualizar botões de fonte
        document.querySelectorAll('[data-action^="font"]').forEach(btn => {
            btn.classList.remove('active');
        });
        const activeFontBtn = document.querySelector(`[data-action="font${this.settings.fontSize.charAt(0).toUpperCase() + this.settings.fontSize.slice(1)}"]`);
        if (activeFontBtn) {
            activeFontBtn.classList.add('active');
        }
    }
    
    setupKeyboardShortcuts() {
        document.addEventListener('keydown', (e) => {
            // Alt + A: Abrir menu de acessibilidade
            if (e.altKey && e.key === 'a') {
                e.preventDefault();
                this.toggleAccessibilityBar();
            }
            
            // Alt + C: Alto contraste
            if (e.altKey && e.key === 'c') {
                e.preventDefault();
                this.toggleHighContrast();
            }
            
            // Alt + +: Aumentar fonte
            if (e.altKey && (e.key === '+' || e.key === '=')) {
                e.preventDefault();
                this.increaseFontSize();
            }
            
            // Alt + -: Diminuir fonte
            if (e.altKey && e.key === '-') {
                e.preventDefault();
                this.decreaseFontSize();
            }
            
            // Alt + K: Mostrar atalhos
            if (e.altKey && e.key === 'k') {
                e.preventDefault();
                this.toggleKeyboardHelp();
            }
            
            // Esc: Fechar menus
            if (e.key === 'Escape') {
                const bar = document.querySelector('.accessibility-bar');
                if (bar.classList.contains('open')) {
                    this.toggleAccessibilityBar();
                }
                const help = document.querySelector('.keyboard-help');
                if (help.classList.contains('show')) {
                    help.classList.remove('show');
                }
            }
        });
    }
    
    saveSettings() {
        localStorage.setItem('carbonflightAccessibility', JSON.stringify(this.settings));
    }
    
    loadSettings() {
        const saved = localStorage.getItem('carbonflightAccessibility');
        if (saved) {
            try {
                this.settings = JSON.parse(saved);
            } catch (e) {
                console.error('Erro ao carregar configurações de acessibilidade:', e);
            }
        }
    }
    
    // Anunciar mudanças para leitores de tela
    announce(message) {
        const announcer = document.getElementById('a11y-announcer') || this.createAnnouncer();
        announcer.textContent = message;
    }
    
    createAnnouncer() {
        const announcer = document.createElement('div');
        announcer.id = 'a11y-announcer';
        announcer.setAttribute('role', 'status');
        announcer.setAttribute('aria-live', 'polite');
        announcer.setAttribute('aria-atomic', 'true');
        announcer.style.position = 'absolute';
        announcer.style.left = '-10000px';
        announcer.style.width = '1px';
        announcer.style.height = '1px';
        announcer.style.overflow = 'hidden';
        document.body.appendChild(announcer);
        return announcer;
    }
}

// Inicializar quando o DOM estiver pronto
document.addEventListener('DOMContentLoaded', () => {
    window.accessibilityManager = new AccessibilityManager();
    console.log('✅ Sistema de Acessibilidade CarbonFlight iniciado');
});
