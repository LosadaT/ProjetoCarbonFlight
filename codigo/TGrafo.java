package codigo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//definição de uma estrutura Matriz de Adjacência para armezanar um grafo
public class TGrafo {
    // Atributos Privados
    private int n; // quantidade de vértices
    private int m; // quantidade de arestas
    private float adj[][]; // matriz de adjacência

    // Métodos Públicos
    public TGrafo(int n) { // construtor
        this.n = n;
        // No início dos tempos não há arestas
        this.m = 0;
        // alocação da matriz do TGrafo
        this.adj = new float[n][n];

        // Inicia a matriz com zeros
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.adj[i][j] = Float.POSITIVE_INFINITY; // 10/18 Float.POSITIVE_INFINITY -> Representação de Infinito para Float

            }
        }
    }

    // Construtor de Cópia do Grafo (Utilizado para excluir vertice)
    public TGrafo(TGrafo outro) {
        this.n = outro.n;
        this.m = outro.m;
        this.adj = new float[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.adj[i][j] = outro.adj[i][j];
            }
        }
    }

    // Insere uma aresta no Grafo tal que v é adjacente a w
    public void insereA(int v, int w, float peso) {
        // testa se nao temos a aresta
        if (adj[v][w] == Float.POSITIVE_INFINITY) {
            m++; // atualiza qtd arestas
        }
        adj[v][w] = peso;
    }

    // remove uma aresta v->w do Grafo
    public void removeA(int v, int w) {
        // testa se temos a aresta
        if (adj[v][w] != Float.POSITIVE_INFINITY) {
            adj[v][w] = Float.POSITIVE_INFINITY;
            m--; // atualiza qtd arestas
        }
    }

    public void insereV() {
        n++;
        float novaAdj [][] = new float[n][n];

        // Copia os valores antigos para a nova matriz
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                novaAdj[i][j] = adj[i][j];
            }
        }
        // Inicializa a nova linha e coluna com infinito
        for (int i = 0; i < n; i++) {
            novaAdj[i][n - 1] = Float.POSITIVE_INFINITY; // Nova coluna
            novaAdj[n - 1][i] = Float.POSITIVE_INFINITY; // Nova linha
        }
        adj = novaAdj;
    }

    public void show() {
        System.out.println("n: " + n);
        System.out.println("m: " + m);
        int contador = 0;
        for (int i = 0; i < n; i++) {
            for (int w = 0; w < n; w++) {
                if (adj[i][w] != Float.POSITIVE_INFINITY && adj[i][w] != 0) {
                    System.out.println("Adj[" + (i+1) + "," + (w+1) + "] = [peso = " + adj[i][w] + "]");
                    contador++;
                }
            }
        }
        System.out.println("Total de arestas exibidas: " + contador);
        System.out.println("\nfim da impressao do grafo.");
    }


    public int inDegree(int v) {
        int count = 0;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (j == v) {
                    if (adj[i][j] != Float.POSITIVE_INFINITY) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int outDegree(int v) {
        int count = 0;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (i == v) {
                    if (adj[i][j] != Float.POSITIVE_INFINITY) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int degree(int v) {
        return inDegree(v) + outDegree(v);
    }

    public int isFonte(int v) {
        if (outDegree(v) > 0 && inDegree(v) == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public int sorvedouro(int v) {
        if (inDegree(v) > 0 && outDegree(v) == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public int isSimetrico() {
        int count = 0;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (adj[i][j] != Float.POSITIVE_INFINITY && adj[j][i] != Float.POSITIVE_INFINITY) {
                    count++;
                }
            }
        }
        if (this.m == count) {
            return 1;
        } else {
            return 0;
        }
    }

    public static TGrafo fromArquivo(int arestas, int vertices, BufferedReader br, TGrafo g) throws IOException {
        // Ignora a primeira linha (já lida fora)
        // Segunda linha: quantidade de vértices (já lida fora)

        // Ler os vértices
        for (int i = 0; i < vertices; i++) {
            String linhaVertice = br.readLine();
            // Apenas ignora a linha do vértice
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
            g.insereA(v-1, w-1, peso);
        }
        br.close();
        return g;
    }

    // remove um vértice do grafo, removendo suas arestas também
    public void removeV(int v) {
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

        // removendo a linha e a coluna correspondente ao vértice v
        float[][] novaAdj = new float[n - 1][n - 1];
        int ni = 0, nj;
        for (int i = 0; i < n; i++) {
            if (i == v) {
                continue;
            }
            nj = 0;
            for (int j = 0; j < n; j++) {
                if (j == v) {
                    continue;
                }
                novaAdj[ni][nj] = adj[i][j];
                nj++;
            }
            ni++;
        }
        adj = novaAdj;
        n--; // atualizando o número total dos vértices

        m = 0; // recalcula o número de arestas, após a remoção/atualização
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adj[i][j] != Float.POSITIVE_INFINITY) {
                    m++;
                }
            }
        }
    }

    public boolean isCompleto() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && adj[i][j] == Float.POSITIVE_INFINITY) {
                    return false; // caso não for completo
                }
            }
        }
        return true; // caso for completo
    }

    public TGrafo complemento() {
        TGrafo complemento = new TGrafo(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    complemento.adj[i][j] = Float.POSITIVE_INFINITY;
                } else if (this.adj[i][j] == Float.POSITIVE_INFINITY) {
                    complemento.adj[i][j] = 1; // ou qualquer peso que represente a aresta
                    complemento.m++;
                } else {
                    complemento.adj[i][j] = Float.POSITIVE_INFINITY;
                }
            }

        }
        return complemento;
    }

    public int categoriaConexidade() {
        for (int v = 0; v < n; v++) {
            boolean[] visitado = new boolean[n];
            profundidadeDirigida(v, visitado);
            for (boolean vis : visitado) {
                if (!vis) {
                    // caso não seja fortemente conexo, testa se é fracamente conexo
                    if (isFracamenteConexo()) {
                        int numComponentes = countComponentesFortementeConexos();
                        if (numComponentes > 1) {
                            return 2; // entra no caso C2 (Não direcionado e não conexo)
                        } else {
                            return 1; // entra no caso C1 (Não direcionado e conexo)
                        }

                    } else {
                        return 0; // entra no caso C0 (Não direcionado e não conexo)
                    }
                }
            }
        }
        return 3; // entrar no caso C3 (Fortemente Conexo)
    }

    // busca em profundidade no grafo dirigido
    private void profundidadeDirigida(int v, boolean[] visitado) {
        visitado[v] = true;
        for (int i = 0; i < n; i++) {
            if (adj[v][i] != Float.POSITIVE_INFINITY && !visitado[i]) {
                profundidadeDirigida(i, visitado);
            }
        }
    }

    // verifica se o grafo subjacente não direcionado é conexo (C1)
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

    // busca em profundidade no grafo não direcionado
    private void profundidadeNaoDirigido(int v, boolean[] visitado) {
        visitado[v] = true;
        for (int i = 0; i < n; i++) {
            if ((adj[v][i] != Float.POSITIVE_INFINITY || adj[i][v] != Float.POSITIVE_INFINITY) && !visitado[i]) {
                profundidadeNaoDirigido(i, visitado);
            }
        }
    }

    // irá contar o número de componentes fortemente conexos no grafo (C2)
    private int countComponentesFortementeConexos() {
        boolean[] visitado = new boolean[n];
        int componentes = 0;

        for (int v = 0; v < n; v++) {
            if (!visitado[v]) {
                boolean[] tempVisitado = new boolean[n];
                profundidadeDirigida(v, tempVisitado);

                // marca todos os vértices alcançáveis a partir de v como visitados
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

    // variável 'contadorCFC', utilizada para contar os componentes fortemente conexas (CFC) durante o buscaProfunda
    private int contadorCFC = 0;

    // auxilia para a busca profunda de Tarjan
    private void tarjanProfundo(int u, int[] disc, int[] low, boolean[] stackMember, java.util.Stack<Integer> stack, int[] componente, int[] tempo) {
        disc[u] = tempo[0];
        low[u] = tempo[0];
        tempo[0]++;
        stack.push(u);
        stackMember[u] = true;

        for (int v = 0; v < n; v++) {
            if (adj[u][v] != Float.POSITIVE_INFINITY) { // existe aresta de u para v
                if (disc[v] == -1) {
                    tarjanProfundo(v, disc, low, stackMember, stack, componente, tempo);
                    low[u] = Math.min(low[u], low[v]);
                } else if (stackMember[v]) {
                    low[u] = Math.min(low[u], disc[v]);
                }
            }
        }

        // se u é raiz da componente fortemente conexa
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

    // irá retornar o array de componentes fortemente conexas de cada um dos vértices
    private int[] encontraCFC() {
        int[] disc = new int[n];
        int[] low = new int[n];
        boolean[] stackMember = new boolean[n];
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        int[] componente = new int[n];
        int[] tempo = new int[]{0}; // para passar tempo por referência

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

    // gera o grafo reduzido a partir das componentos fortemente conexas
    public TGrafo grafoReduzido() {
        int[] componente = encontraCFC();

        // conta a quantidade de componentes fortemente conexas (CFC) difetentes
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

    // efetua o percurso em profundidade, retornando um array de inteiros, da ordem em que os elementos foram visitados,
    // baseado no nesse estilo de percurso/busca (Depth-First Search - DFS) 
    public ArrayList<Integer> percusoProfundidade(int inicio) {
        boolean [] visitado = new boolean[n];
        Stack<Integer> pilha = new Stack<>();
        ArrayList<Integer> ordemVisitados = new ArrayList<>();

        int vAtual = inicio;
        visitado[vAtual] = true;
        ordemVisitados.add(vAtual);
        pilha.push(vAtual);

        while(!pilha.isEmpty()){
            vAtual = pilha.pop();

            for(int m = 0; m < n; m++) {
                if(adj[vAtual][m] != Float.POSITIVE_INFINITY && !visitado[m]) {
                    ordemVisitados.add(m);
                    pilha.push(vAtual);
                    visitado[m] = true;
                    vAtual = m;
                    m = m-1;
                }
            }
        }
        return ordemVisitados; // Retorna o Array de inteiros com os elementos visitados baseado na ordem do percruso de profundidade
    }

    // Percurso em largura (Breadth-First Traversal - BFS)
    public ArrayList<Integer> percursoLargura(int vInicio) {
        int nroNosMarcados = 0;
        int[] nosMarcados = new int[n];
        ArrayList<Integer> ordemVisitados = new ArrayList<>();
        Queue<Integer> fila = new LinkedList<>();

        visitarNo(vInicio, ordemVisitados);
        marcarNo(nosMarcados, nroNosMarcados++, vInicio);
        fila.add(vInicio);

        while (!fila.isEmpty()) {
            int nAtual = fila.poll();
            int m;
            while ((m = noAdjacente(nAtual, nosMarcados, nroNosMarcados)) != -1) {
                visitarNo(m, ordemVisitados);
                fila.add(m);
                marcarNo(nosMarcados, nroNosMarcados++, m);
            }
        }
        return ordemVisitados;
    }

    private void marcarNo(int[] nosMarcados, int nroNosMarcados, int m) {
        nosMarcados[nroNosMarcados] = m;
    }

    private int noAdjacente(int n, int[] nosMarcados, int nroNosMarcados) {
        for (int m = 0; m < this.n; m++) {
            if (adj[n][m] != Float.POSITIVE_INFINITY && !estaMarcado(m, nosMarcados, nroNosMarcados)) {
                return m;
            }
        }
        return -1;
    }

    private boolean estaMarcado(int m, int[] nosMarcados, int nroNosMarcados) {
        for (int i = 0; i < nroNosMarcados; i++) {
            if (nosMarcados[i] == m) {
                return true;
            }
        }
        return false;
    }

    private void visitarNo(int m, ArrayList<Integer> ordemVisitados) {
        ordemVisitados.add(m);
    }

    //Dijkistra
    public float[] dijkstra(int origem) {
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
        System.out.println("Distâncias a partir do vértice " + origem + ":");
        for (int i = 0; i < n; i++) {
            System.out.print(" -> " + i + " = " + dist[i]);
            if (antecessor[i] != -1) {
                System.out.print(" (caminho: ");
                imprimeCaminho(antecessor, i);
                System.out.print(")");
            }
            System.out.println();
        }
        return dist;
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

    private void imprimeCaminho(int[] antecessor, int v) {
        if (antecessor[v] == -1) {
            System.out.print(v);
            return;
        }
        imprimeCaminho(antecessor, antecessor[v]);
        System.out.print(" -> " + v);
    }

    // Mostra o conteúdo do arquivo exatamente como está
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
            // Escreve os vértices (aqui apenas escrevemos números sequenciais como exemplo)
            for (int i = 0; i < g.n; i++) {
                bw.write((i + 1) + "\n"); // Vértices numerados de 1 a n
            }
            // Escreve a quantidade de arestas
            bw.write(g.m + "\n");
            // Escreve as arestas
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
