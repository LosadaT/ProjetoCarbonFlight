package codigo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class main {
    private void menu() {
        System.out.println("Menu de opções:");
        System.out.println("1. Ler dados do arquivo grafo.txt");
        System.out.println("2. Gravar dados no arquivo grafo.txt");
        System.out.println("3. Inserir vértice");
        System.out.println("4. Inserir aresta");
        System.out.println("5. Remover vértice");
        System.out.println("6. Remover aresta");
        System.out.println("7. Mostrar conteúdo do arquivo");
        System.out.println("8. Mostrar grafo");
        System.out.println("9. Apresentar a conexidade do grafo e o reduzido");
        System.out.println("10. Encerrar a aplicação"); 
    }
    public static void main(String[] args) throws IOException {
        main app = new main();
        Scanner s = new Scanner(System.in);
        int opcao = 0;
        int numVertices = 0;
        int numArestas = 0;
        TGrafo g = new TGrafo(numVertices);

        while (opcao != 10) {
            app.menu();
            System.out.print("Escolha uma opção: ");
            opcao = s.nextInt();
            switch(opcao) {
                //Ler dados do arquivo
                case 1:
                    BufferedReader br = new BufferedReader(new FileReader("/Users/francisco/Documents/ProjetoCarbonFlight/codigo/grafoTeste.txt"));
                    numVertices = Integer.parseInt(br.readLine().trim());
                    numArestas = Integer.parseInt(br.readLine().trim());
                    g = new TGrafo(numVertices);
                    TGrafo gArquivo = TGrafo.fromArquivo(numArestas, numVertices, br, g);
                    gArquivo.show();
                    break;
                    
                //gravar dados no arquivo
                case 2:

                    break;

                //inserir vertice
                case 3:
                    g.insereV();
                    System.out.println("Vertice Inserido");
                    break;

                //inserir aresta
                case 4:
                    System.out.print("Digite o vértice de origem: ");
                    int vOrigem = s.nextInt();
                    System.out.print("Digite o vértice de destino: ");
                    int vDestino = s.nextInt();
                    System.out.print("Digite o peso da aresta: ");
                    int peso = s.nextInt();
                    g.insereA(vOrigem, vDestino, peso);
                    System.out.println("Aresta Inserida");
                    break;

                //remove vertice
                case 5:
                    System.out.print("Digite o vértice a ser removido: ");
                    int vRemover = s.nextInt();
                    g.removeV(vRemover);
                    System.out.println("Vértice Removido");
                    break;

                //remover aresta
                case 6:
                    System.out.print("Digite o vértice de origem da aresta a ser removida: ");
                    int vOrigemRemover = s.nextInt();
                    System.out.print("Digite o vértice de destino da aresta a ser removida: ");
                    int vDestinoRemover = s.nextInt();
                    g.removeA(vOrigemRemover, vDestinoRemover);
                    System.out.println("Aresta Removida");
                    break;

                //mostrar conteudo arquivo
                case 7:

                    break;

                //mostrar grafo
                case 8:
                    g.show();
                    break;

                //Conexidade e Reduzido
                case 9:
                    TGrafo gReduzido = g.grafoReduzido();
                    System.out.println("Grafo Reduzido:");
                    gReduzido.show();
                    System.out.println("Categoria de Conexidade: " + g.categoriaConexidade());
                    break;
                //Sair
                case 10:
                    System.out.println("Encerrou");
                    break;
                default:

            }
        }
        s.close();

    }
    
}
