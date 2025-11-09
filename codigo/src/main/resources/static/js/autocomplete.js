// Autocomplete para campos de aeroporto
// v2.0 - Exibe "Cidade (IATA)" ao selecionar
class AirportAutocomplete {
    constructor(inputElement, onSelect) {
        this.input = inputElement;
        this.onSelect = onSelect;
        this.suggestionsContainer = null;
        this.currentFocus = -1;
        this.init();
    }
    
    init() {
        // Cria o container de sugestões
        this.suggestionsContainer = document.createElement('div');
        this.suggestionsContainer.className = 'autocomplete-suggestions';
        this.input.parentNode.style.position = 'relative';
        this.input.parentNode.appendChild(this.suggestionsContainer);
        
        // Event listeners
        this.input.addEventListener('input', () => this.handleInput());
        this.input.addEventListener('keydown', (e) => this.handleKeydown(e));
        this.input.addEventListener('blur', () => {
            setTimeout(() => this.hideSuggestions(), 200);
        });
    }
    
    handleInput() {
        const query = this.input.value;
        
        if (query.length < 2) {
            this.hideSuggestions();
            return;
        }
        
        const results = searchAirports(query);
        this.showSuggestions(results);
    }
    
    showSuggestions(airports) {
        if (airports.length === 0) {
            this.hideSuggestions();
            return;
        }
        
        this.suggestionsContainer.innerHTML = '';
        this.currentFocus = -1;
        
        airports.forEach((airport, index) => {
            const div = document.createElement('div');
            div.className = 'autocomplete-item';
            div.innerHTML = `
                <div class="airport-code">${airport.iata}</div>
                <div class="airport-info">
                    <div class="airport-city">${airport.city}</div>
                    <div class="airport-name">${airport.name}</div>
                    <div class="airport-country">${airport.country}</div>
                </div>
            `;
            
            div.addEventListener('click', () => {
                this.selectAirport(airport);
            });
            
            this.suggestionsContainer.appendChild(div);
        });
        
        this.suggestionsContainer.style.display = 'block';
    }
    
    hideSuggestions() {
        this.suggestionsContainer.style.display = 'none';
        this.currentFocus = -1;
    }
    
    selectAirport(airport) {
        this.input.value = `${airport.city} (${airport.iata})`;
        this.hideSuggestions();
        
        if (this.onSelect) {
            this.onSelect(airport);
        }
        
        // Dispara evento de change
        this.input.dispatchEvent(new Event('change'));
    }
    
    handleKeydown(e) {
        const items = this.suggestionsContainer.getElementsByClassName('autocomplete-item');
        
        if (items.length === 0) return;
        
        if (e.key === 'ArrowDown') {
            e.preventDefault();
            this.currentFocus++;
            if (this.currentFocus >= items.length) this.currentFocus = 0;
            this.setActive(items);
        } else if (e.key === 'ArrowUp') {
            e.preventDefault();
            this.currentFocus--;
            if (this.currentFocus < 0) this.currentFocus = items.length - 1;
            this.setActive(items);
        } else if (e.key === 'Enter') {
            e.preventDefault();
            if (this.currentFocus > -1 && items[this.currentFocus]) {
                items[this.currentFocus].click();
            }
        } else if (e.key === 'Escape') {
            this.hideSuggestions();
        }
    }
    
    setActive(items) {
        for (let i = 0; i < items.length; i++) {
            items[i].classList.remove('active');
        }
        
        if (this.currentFocus >= 0 && this.currentFocus < items.length) {
            items[this.currentFocus].classList.add('active');
            items[this.currentFocus].scrollIntoView({ block: 'nearest' });
        }
    }
}

// Inicializa os autocompletes quando o DOM carregar
document.addEventListener('DOMContentLoaded', function() {
    const origemInput = document.getElementById('origem');
    const destinoInput = document.getElementById('destino');
    
    if (origemInput) {
        new AirportAutocomplete(origemInput, (airport) => {
            console.log('Origem selecionada:', airport);
        });
    }
    
    if (destinoInput) {
        new AirportAutocomplete(destinoInput, (airport) => {
            console.log('Destino selecionado:', airport);
        });
    }
});
