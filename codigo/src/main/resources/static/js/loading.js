// Sistema de Loading Screen
class LoadingScreen {
    constructor() {
        this.overlay = null;
        this.tips = [
            "🌱 Priorizando voos com menor emissão de CO₂...",
            "✈️ Analisando rotas mais eficientes...",
            "🔍 Consultando a API da Amadeus...",
            "📊 Calculando emissões de carbono...",
            "🌍 Comparando opções sustentáveis...",
            "🛩️ Avaliando modelos de aeronaves...",
            "🗺️ Aplicando algoritmo de Dijkstra...",
            "💚 Buscando as melhores opções para o planeta..."
        ];
        this.currentTipIndex = 0;
        this.tipInterval = null;
        this.init();
    }
    
    init() {
        // Cria o overlay de loading
        this.createOverlay();
        
        // Intercepta o submit do formulário
        const form = document.querySelector('.flight-form');
        if (form) {
            form.addEventListener('submit', (e) => {
                this.show();
            });
        }
    }
    
    createOverlay() {
        this.overlay = document.createElement('div');
        this.overlay.className = 'loading-overlay';
        this.overlay.innerHTML = `
            <div class="loading-content">
                <div class="loading-plane">✈️</div>
                
                <div class="loading-spinner">
                    <div class="spinner-ring"></div>
                    <div class="spinner-ring"></div>
                    <div class="spinner-ring"></div>
                </div>
                
                <div class="loading-text">
                    Buscando voos sustentáveis<span class="loading-dots"></span>
                </div>
                
                <div class="loading-subtext">
                    Aguarde enquanto encontramos as melhores opções
                </div>
                
                <div class="loading-progress">
                    <div class="loading-progress-bar"></div>
                </div>
                
                <div class="loading-tips">
                    <span id="loading-tip">${this.tips[0]}</span>
                </div>
                
                <div class="loading-icons">
                    <span>🌍</span>
                    <span>♻️</span>
                    <span>💚</span>
                </div>
            </div>
        `;
        
        document.body.appendChild(this.overlay);
    }
    
    show() {
        if (this.overlay) {
            this.overlay.classList.add('active');
            this.startRotatingTips();
            
            // Previne scroll do body
            document.body.style.overflow = 'hidden';
        }
    }
    
    hide() {
        if (this.overlay) {
            this.overlay.classList.remove('active');
            this.stopRotatingTips();
            
            // Restaura scroll do body
            document.body.style.overflow = '';
        }
    }
    
    startRotatingTips() {
        const tipElement = document.getElementById('loading-tip');
        
        this.tipInterval = setInterval(() => {
            this.currentTipIndex = (this.currentTipIndex + 1) % this.tips.length;
            if (tipElement) {
                tipElement.style.animation = 'none';
                setTimeout(() => {
                    tipElement.textContent = this.tips[this.currentTipIndex];
                    tipElement.style.animation = 'fadeInOut 4s ease-in-out infinite';
                }, 50);
            }
        }, 4000);
    }
    
    stopRotatingTips() {
        if (this.tipInterval) {
            clearInterval(this.tipInterval);
            this.tipInterval = null;
        }
        this.currentTipIndex = 0;
    }
}

// Inicializa o loading screen quando o DOM carregar
document.addEventListener('DOMContentLoaded', function() {
    // Cria instância global do loading screen
    window.loadingScreen = new LoadingScreen();
    
    // Para debug: permite esconder o loading manualmente no console
    window.hideLoading = function() {
        if (window.loadingScreen) {
            window.loadingScreen.hide();
        }
    };
});

// Se a página carregar completamente (navegação), esconde o loading
window.addEventListener('load', function() {
    if (window.loadingScreen) {
        window.loadingScreen.hide();
    }
});

// Se houver erro de navegação, esconde o loading
window.addEventListener('pageshow', function() {
    if (window.loadingScreen) {
        window.loadingScreen.hide();
    }
});
