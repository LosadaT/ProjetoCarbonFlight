// Lista de aeroportos principais do mundo
// Formato: { iata: "GRU", name: "São Paulo", city: "São Paulo", country: "Brasil" }
const airports = [
    // Brasil
    { iata: "GRU", name: "Aeroporto Internacional de São Paulo/Guarulhos", city: "São Paulo", country: "Brasil" },
    { iata: "CGH", name: "Aeroporto de Congonhas", city: "São Paulo", country: "Brasil" },
    { iata: "GIG", name: "Aeroporto Internacional do Rio de Janeiro/Galeão", city: "Rio de Janeiro", country: "Brasil" },
    { iata: "SDU", name: "Aeroporto Santos Dumont", city: "Rio de Janeiro", country: "Brasil" },
    { iata: "BSB", name: "Aeroporto Internacional de Brasília", city: "Brasília", country: "Brasil" },
    { iata: "SSA", name: "Aeroporto Internacional de Salvador", city: "Salvador", country: "Brasil" },
    { iata: "FOR", name: "Aeroporto Internacional de Fortaleza", city: "Fortaleza", country: "Brasil" },
    { iata: "REC", name: "Aeroporto Internacional do Recife", city: "Recife", country: "Brasil" },
    { iata: "CWB", name: "Aeroporto Internacional de Curitiba", city: "Curitiba", country: "Brasil" },
    { iata: "POA", name: "Aeroporto Internacional de Porto Alegre", city: "Porto Alegre", country: "Brasil" },
    { iata: "MAO", name: "Aeroporto Internacional de Manaus", city: "Manaus", country: "Brasil" },
    { iata: "BEL", name: "Aeroporto Internacional de Belém", city: "Belém", country: "Brasil" },
    { iata: "VCP", name: "Aeroporto Internacional de Viracopos", city: "Campinas", country: "Brasil" },
    { iata: "CNF", name: "Aeroporto Internacional de Belo Horizonte", city: "Belo Horizonte", country: "Brasil" },
    
    // América do Norte
    { iata: "JFK", name: "John F. Kennedy International Airport", city: "Nova York", country: "Estados Unidos" },
    { iata: "LAX", name: "Los Angeles International Airport", city: "Los Angeles", country: "Estados Unidos" },
    { iata: "MIA", name: "Miami International Airport", city: "Miami", country: "Estados Unidos" },
    { iata: "ORD", name: "O'Hare International Airport", city: "Chicago", country: "Estados Unidos" },
    { iata: "ATL", name: "Hartsfield-Jackson Atlanta International Airport", city: "Atlanta", country: "Estados Unidos" },
    { iata: "DFW", name: "Dallas/Fort Worth International Airport", city: "Dallas", country: "Estados Unidos" },
    { iata: "SFO", name: "San Francisco International Airport", city: "São Francisco", country: "Estados Unidos" },
    { iata: "IAH", name: "George Bush Intercontinental Airport", city: "Houston", country: "Estados Unidos" },
    { iata: "EWR", name: "Newark Liberty International Airport", city: "Newark", country: "Estados Unidos" },
    { iata: "SEA", name: "Seattle-Tacoma International Airport", city: "Seattle", country: "Estados Unidos" },
    { iata: "LAS", name: "Harry Reid International Airport", city: "Las Vegas", country: "Estados Unidos" },
    { iata: "BOS", name: "Boston Logan International Airport", city: "Boston", country: "Estados Unidos" },
    { iata: "MCO", name: "Orlando International Airport", city: "Orlando", country: "Estados Unidos" },
    { iata: "DEN", name: "Denver International Airport", city: "Denver", country: "Estados Unidos" },
    { iata: "MEX", name: "Aeropuerto Internacional de la Ciudad de México", city: "Cidade do México", country: "México" },
    { iata: "CUN", name: "Aeropuerto Internacional de Cancún", city: "Cancún", country: "México" },
    { iata: "YYZ", name: "Toronto Pearson International Airport", city: "Toronto", country: "Canadá" },
    { iata: "YVR", name: "Vancouver International Airport", city: "Vancouver", country: "Canadá" },
    { iata: "YUL", name: "Montréal-Pierre Elliott Trudeau International Airport", city: "Montreal", country: "Canadá" },
    
    // América do Sul
    { iata: "EZE", name: "Aeropuerto Internacional de Ezeiza", city: "Buenos Aires", country: "Argentina" },
    { iata: "AEP", name: "Aeroparque Jorge Newbery", city: "Buenos Aires", country: "Argentina" },
    { iata: "SCL", name: "Aeropuerto Internacional Arturo Merino Benítez", city: "Santiago", country: "Chile" },
    { iata: "LIM", name: "Aeropuerto Internacional Jorge Chávez", city: "Lima", country: "Peru" },
    { iata: "BOG", name: "Aeropuerto Internacional El Dorado", city: "Bogotá", country: "Colômbia" },
    { iata: "UIO", name: "Aeropuerto Internacional Mariscal Sucre", city: "Quito", country: "Equador" },
    { iata: "MVD", name: "Aeropuerto Internacional de Carrasco", city: "Montevidéu", country: "Uruguai" },
    { iata: "ASU", name: "Aeropuerto Internacional Silvio Pettirossi", city: "Assunção", country: "Paraguai" },
    
    // Europa
    { iata: "LHR", name: "London Heathrow Airport", city: "Londres", country: "Reino Unido" },
    { iata: "CDG", name: "Aéroport Paris-Charles de Gaulle", city: "Paris", country: "França" },
    { iata: "ORY", name: "Aéroport de Paris-Orly", city: "Paris", country: "França" },
    { iata: "MAD", name: "Aeropuerto Adolfo Suárez Madrid-Barajas", city: "Madrid", country: "Espanha" },
    { iata: "BCN", name: "Aeropuerto de Barcelona-El Prat", city: "Barcelona", country: "Espanha" },
    { iata: "FCO", name: "Aeroporto di Roma-Fiumicino", city: "Roma", country: "Itália" },
    { iata: "MXP", name: "Aeroporto di Milano-Malpensa", city: "Milão", country: "Itália" },
    { iata: "FRA", name: "Flughafen Frankfurt am Main", city: "Frankfurt", country: "Alemanha" },
    { iata: "MUC", name: "Flughafen München", city: "Munique", country: "Alemanha" },
    { iata: "AMS", name: "Amsterdam Airport Schiphol", city: "Amsterdã", country: "Holanda" },
    { iata: "LIS", name: "Aeroporto de Lisboa", city: "Lisboa", country: "Portugal" },
    { iata: "OPO", name: "Aeroporto Francisco Sá Carneiro", city: "Porto", country: "Portugal" },
    { iata: "ZRH", name: "Flughafen Zürich", city: "Zurique", country: "Suíça" },
    { iata: "VIE", name: "Flughafen Wien-Schwechat", city: "Viena", country: "Áustria" },
    { iata: "BRU", name: "Brussels Airport", city: "Bruxelas", country: "Bélgica" },
    { iata: "CPH", name: "Copenhagen Airport", city: "Copenhague", country: "Dinamarca" },
    { iata: "ARN", name: "Stockholm Arlanda Airport", city: "Estocolmo", country: "Suécia" },
    { iata: "OSL", name: "Oslo Airport", city: "Oslo", country: "Noruega" },
    { iata: "HEL", name: "Helsinki-Vantaa Airport", city: "Helsinque", country: "Finlândia" },
    { iata: "ATH", name: "Athens International Airport", city: "Atenas", country: "Grécia" },
    { iata: "IST", name: "Istanbul Airport", city: "Istambul", country: "Turquia" },
    { iata: "DME", name: "Moscow Domodedovo Airport", city: "Moscou", country: "Rússia" },
    
    // Ásia
    { iata: "DXB", name: "Dubai International Airport", city: "Dubai", country: "Emirados Árabes" },
    { iata: "DOH", name: "Hamad International Airport", city: "Doha", country: "Catar" },
    { iata: "HKG", name: "Hong Kong International Airport", city: "Hong Kong", country: "China" },
    { iata: "NRT", name: "Narita International Airport", city: "Tóquio", country: "Japão" },
    { iata: "HND", name: "Tokyo Haneda Airport", city: "Tóquio", country: "Japão" },
    { iata: "ICN", name: "Incheon International Airport", city: "Seul", country: "Coreia do Sul" },
    { iata: "SIN", name: "Singapore Changi Airport", city: "Singapura", country: "Singapura" },
    { iata: "BKK", name: "Suvarnabhumi Airport", city: "Bangkok", country: "Tailândia" },
    { iata: "DEL", name: "Indira Gandhi International Airport", city: "Nova Délhi", country: "Índia" },
    { iata: "BOM", name: "Chhatrapati Shivaji Maharaj International Airport", city: "Mumbai", country: "Índia" },
    { iata: "PEK", name: "Beijing Capital International Airport", city: "Pequim", country: "China" },
    { iata: "PVG", name: "Shanghai Pudong International Airport", city: "Xangai", country: "China" },
    { iata: "KUL", name: "Kuala Lumpur International Airport", city: "Kuala Lumpur", country: "Malásia" },
    { iata: "MNL", name: "Ninoy Aquino International Airport", city: "Manila", country: "Filipinas" },
    
    // Oceania
    { iata: "SYD", name: "Sydney Kingsford Smith Airport", city: "Sydney", country: "Austrália" },
    { iata: "MEL", name: "Melbourne Airport", city: "Melbourne", country: "Austrália" },
    { iata: "AKL", name: "Auckland Airport", city: "Auckland", country: "Nova Zelândia" },
    
    // África
    { iata: "JNB", name: "O.R. Tambo International Airport", city: "Joanesburgo", country: "África do Sul" },
    { iata: "CPT", name: "Cape Town International Airport", city: "Cidade do Cabo", country: "África do Sul" },
    { iata: "CAI", name: "Cairo International Airport", city: "Cairo", country: "Egito" },
    { iata: "ADD", name: "Addis Ababa Bole International Airport", city: "Adis Abeba", country: "Etiópia" },
    { iata: "NBO", name: "Jomo Kenyatta International Airport", city: "Nairobi", country: "Quênia" },
    { iata: "LOS", name: "Murtala Muhammed International Airport", city: "Lagos", country: "Nigéria" },
];

// Função para buscar aeroportos que correspondem à query
function searchAirports(query) {
    if (!query || query.length < 2) {
        return [];
    }
    
    const searchTerm = query.toLowerCase();
    
    return airports.filter(airport => {
        return airport.iata.toLowerCase().includes(searchTerm) ||
               airport.city.toLowerCase().includes(searchTerm) ||
               airport.name.toLowerCase().includes(searchTerm) ||
               airport.country.toLowerCase().includes(searchTerm);
    }).slice(0, 10); // Limita a 10 resultados
}

// Função para formatar o resultado do autocomplete
function formatAirport(airport) {
    return `${airport.iata} - ${airport.city}, ${airport.country}`;
}
