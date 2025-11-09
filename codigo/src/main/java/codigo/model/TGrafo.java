package codigo.model;

import java.io.BufferedReader;
import java.io.IOException;

// Classe que representa um grafo direcionado através de Matriz de Adjacência
// Otimizada para o projeto Carbon Flight
public class TGrafo {
    // Atributos Privados
    private int n; // quantidade de vértices
    private int m; // quantidade de arestas
    private float adj[][]; // matriz de adjacência

    // Construtor
    public TGrafo(int n) {
        this.n = n;
        this.m = 0;
        this.adj = new float[n][n];

        // Inicia a matriz com infinito (sem conexão)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.adj[i][j] = Float.POSITIVE_INFINITY;
            }
        }
    }

    // Insere uma aresta no Grafo tal que v é adjacente a w
    public void insereA(int v, int w, float peso) {
        if (adj[v][w] == Float.POSITIVE_INFINITY) {
            m++; // atualiza qtd arestas
        }
        adj[v][w] = peso;
    }

    // Remove uma aresta v->w do Grafo
    public void removeA(int v, int w) {
        if (adj[v][w] != Float.POSITIVE_INFINITY) {
            adj[v][w] = Float.POSITIVE_INFINITY;
            m--;
        }
    }

    // Insere um novo vértice no grafo
    public void insereV() {
        n++;
        float novaAdj[][] = new float[n][n];

        // Copia os valores antigos para a nova matriz
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                novaAdj[i][j] = adj[i][j];
            }
        }
        
        // Inicializa a nova linha e coluna com infinito
        for (int i = 0; i < n; i++) {
            novaAdj[i][n - 1] = Float.POSITIVE_INFINITY;
            novaAdj[n - 1][i] = Float.POSITIVE_INFINITY;
        }
        adj = novaAdj;
    }

    // Remove um vértice do grafo, removendo suas arestas também
    public void removeV(int v) {
        // Remove todas as arestas conectadas ao vértice
        for (int i = 0; i < n; i++) {
            if (adj[v][i] != Float.POSITIVE_INFINITY) {
                adj[v][i] = Float.POSITIVE_INFINITY;
                m--;
            }
            if (adj[i][v] != Float.POSITIVE_INFINITY) {
                adj[i][v] = Float.POSITIVE_INFINITY;
                m--;
            }
        }

        // Remove a linha e a coluna correspondente ao vértice v
        float[][] novaAdj = new float[n - 1][n - 1];
        int ni = 0, nj;
        for (int i = 0; i < n; i++) {
            if (i == v) continue;
            nj = 0;
            for (int j = 0; j < n; j++) {
                if (j == v) continue;
                novaAdj[ni][nj] = adj[i][j];
                nj++;
            }
            ni++;
        }
        adj = novaAdj;
        n--;

        // Recalcula o número de arestas
        m = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adj[i][j] != Float.POSITIVE_INFINITY) {
                    m++;
                }
            }
        }
    }

    // Getters
    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public float getAresta(int v, int w) {
        return adj[v][w];
    }

    // Exibe o grafo no console
    public void show() {
        System.out.println("n: " + n);
        System.out.println("m: " + m);
        int contador = 0;
        for (int i = 0; i < n; i++) {
            for (int w = 0; w < n; w++) {
                if (adj[i][w] != Float.POSITIVE_INFINITY && adj[i][w] != 0) {
                    System.out.println("Adj[" + (i + 1) + "," + (w + 1) + "] = [peso = " + adj[i][w] + "]");
                    contador++;
                }
            }
        }
        System.out.println("Total de arestas exibidas: " + contador);
        System.out.println("\nfim da impressao do grafo.");
    }

    // Lê grafo de um arquivo
    public static TGrafo fromArquivo(int arestas, int vertices, BufferedReader br, TGrafo g) throws IOException {
        // Ler os vértices
        for (int i = 0; i < vertices; i++) {
            br.readLine(); // Ignora a linha do vértice
        }

        // Ler a quantidade de arestas
        String linhaArestas = br.readLine();
        int qtdArestas = Integer.parseInt(linhaArestas.trim());

        // Ler as arestas
        for (int i = 0; i < qtdArestas; i++) {
            String linhaAresta = br.readLine();
            if (linhaAresta == null || linhaAresta.trim().isEmpty()) {
                continue;
            }
            String[] partesAresta = linhaAresta.trim().split("\\s+");
            int v = Integer.parseInt(partesAresta[0]);
            int w = Integer.parseInt(partesAresta[1]);
            float peso = partesAresta.length > 2 ? Float.parseFloat(partesAresta[2]) : 1;
            g.insereA(v - 1, w - 1, peso);
        }
        br.close();
        return g;
    }

    // ========== MÉTODOS DE CONEXIDADE ==========
    
    public int categoriaConexidade() {
        for (int v = 0; v < n; v++) {
            boolean[] visitado = new boolean[n];
            profundidadeDirigida(v, visitado);
            for (boolean vis : visitado) {
                if (!vis) {
                    if (isFracamenteConexo()) {
                        int numComponentes = countComponentesFortementeConexos();
                        if (numComponentes > 1) {
                            return 2; // C2 (Não direcionado e não conexo)
                        } else {
                            return 1; // C1 (Não direcionado e conexo)
                        }
                    } else {
                        return 0; // C0 (Não direcionado e não conexo)
                    }
                }
            }
        }
        return 3; // C3 (Fortemente Conexo)
    }

    private void profundidadeDirigida(int v, boolean[] visitado) {
        visitado[v] = true;
        for (int i = 0; i < n; i++) {
            if (adj[v][i] != Float.POSITIVE_INFINITY && !visitado[i]) {
                profundidadeDirigida(i, visitado);
            }
        }
    }

    private boolean isFracamenteConexo() {
        boolean[] visitado = new boolean[n];
        profundidadeNaoDirigido(0, visitado);
        for (int i = 0; i < n; i++) {
            if (!visitado[i]) {
                return false;
            }
        }
        return true;
    }

    private void profundidadeNaoDirigido(int v, boolean[] visitado) {
        visitado[v] = true;
        for (int i = 0; i < n; i++) {
            if ((adj[v][i] != Float.POSITIVE_INFINITY || adj[i][v] != Float.POSITIVE_INFINITY) && !visitado[i]) {
                profundidadeNaoDirigido(i, visitado);
            }
        }
    }

    private int countComponentesFortementeConexos() {
        boolean[] visitado = new boolean[n];
        int componentes = 0;

        for (int v = 0; v < n; v++) {
            if (!visitado[v]) {
                boolean[] tempVisitado = new boolean[n];
                profundidadeDirigida(v, tempVisitado);
                for (int i = 0; i < n; i++) {
                    if (tempVisitado[i]) {
                        visitado[i] = true;
                    }
                }
                componentes++;
            }
        }
        return componentes;
    }

    // ========== GRAFO REDUZIDO (TARJAN) ==========
    
    private int contadorCFC = 0;

    private void tarjanProfundo(int u, int[] disc, int[] low, boolean[] stackMember, 
                                java.util.Stack<Integer> stack, int[] componente, int[] tempo) {
        disc[u] = tempo[0];
        low[u] = tempo[0];
        tempo[0]++;
        stack.push(u);
        stackMember[u] = true;

        for (int v = 0; v < n; v++) {
            if (adj[u][v] != Float.POSITIVE_INFINITY) {
                if (disc[v] == -1) {
                    tarjanProfundo(v, disc, low, stackMember, stack, componente, tempo);
                    low[u] = Math.min(low[u], low[v]);
                } else if (stackMember[v]) {
                    low[u] = Math.min(low[u], disc[v]);
                }
            }
        }

        if (low[u] == disc[u]) {
            int w;
            do {
                w = stack.pop();
                stackMember[w] = false;
                componente[w] = contadorCFC;
            } while (w != u);
            contadorCFC++;
        }
    }

    private int[] encontraCFC() {
        int[] disc = new int[n];
        int[] low = new int[n];
        boolean[] stackMember = new boolean[n];
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        int[] componente = new int[n];
        int[] tempo = new int[]{0};

        contadorCFC = 0;

        for (int i = 0; i < n; i++) {
            disc[i] = -1;
            low[i] = -1;
            componente[i] = -1;
            stackMember[i] = false;
        }

        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                tarjanProfundo(i, disc, low, stackMember, stack, componente, tempo);
            }
        }

        return componente;
    }

    public TGrafo grafoReduzido() {
        int[] componente = encontraCFC();

        int quantidadeCFC = 0;
        for (int cfc : componente) {
            if (cfc + 1 > quantidadeCFC) {
                quantidadeCFC = cfc + 1;
            }
        }

        TGrafo reduzido = new TGrafo(quantidadeCFC);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adj[i][j] != Float.POSITIVE_INFINITY) {
                    int ci = componente[i];
                    int cj = componente[j];

                    if (ci != cj) {
                        if (reduzido.adj[ci][cj] == Float.POSITIVE_INFINITY || adj[i][j] < reduzido.adj[ci][cj]) {
                            reduzido.adj[ci][cj] = adj[i][j];
                            reduzido.m++;
                        }
                    }
                }
            }
        }
        return reduzido;
    }

    // ========== DIJKSTRA (ALGORITMO DE MENOR CAMINHO) ==========
    
    // Classe para retornar resultado do Dijkstra
    public static class ResultadoDijkstra {
        public float[] distancias;
        public int[] antecessores;

        public ResultadoDijkstra(float[] distancias, int[] antecessores) {
            this.distancias = distancias;
            this.antecessores = antecessores;
        }
    }

    // Dijkstra
    public ResultadoDijkstra dijkstraSilencioso(int origem) {
        float[] dist = new float[n];
        boolean[] visitado = new boolean[n];
        int[] antecessor = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Float.POSITIVE_INFINITY;
            antecessor[i] = -1;
            visitado[i] = false;
        }

        dist[origem] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = minDistancia(dist, visitado);
            if (u == -1) break;

            visitado[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visitado[v] && adj[u][v] != Float.POSITIVE_INFINITY
                        && dist[u] + adj[u][v] < dist[v]) {
                    dist[v] = dist[u] + adj[u][v];
                    antecessor[v] = u;
                }
            }
        }

        return new ResultadoDijkstra(dist, antecessor);
    }

    // Retorna o caminho completo do Dijkstra como lista de índices
    public java.util.List<Integer> obterCaminho(int[] antecessor, int destino) {
        java.util.List<Integer> caminho = new java.util.ArrayList<>();
        if (antecessor[destino] == -1 && destino != 0) {
            return caminho; // Sem caminho
        }

        int atual = destino;
        while (atual != -1) {
            caminho.add(0, atual);
            atual = antecessor[atual];
        }
        return caminho;
    }

    private int minDistancia(float[] dist, boolean[] visitado) {
        float min = Float.POSITIVE_INFINITY;
        int minIndex = -1;

        for (int v = 0; v < n; v++) {
            if (!visitado[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // ========== MÉTODOS DE ARQUIVO ==========
    
    public static void showArquivo(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public void toArquivo(String caminhoArquivo, TGrafo g) {
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(caminhoArquivo))) {
            bw.write("Grafo Direcionado\n");
            bw.write(g.n + "\n");
            
            for (int i = 0; i < g.n; i++) {
                bw.write((i + 1) + "\n");
            }
            
            bw.write(g.m + "\n");
            
            for (int i = 0; i < g.n; i++) {
                for (int j = 0; j < g.n; j++) {
                    if (g.adj[i][j] != Float.POSITIVE_INFINITY) {
                        bw.write((i + 1) + " " + (j + 1) + " " + g.adj[i][j] + "\n");
                    }
                }
            }
            System.out.println("Grafo gravado com sucesso em " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo: " + e.getMessage());
        }
    }
}
