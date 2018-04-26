import javafx.util.Pair;
import java.util.*;
/**
 * Created by Agata Rychter on 03.06.2017.
 */
public class AdjacencyMatrix<E> {
    private final int size;
    private Integer [][] matrix;
    private ArrayList<E> vertices;

    public AdjacencyMatrix(int size){
        this.size = size;
        this.matrix = new Integer[size][size];
        vertices = new ArrayList<>();
    }

    public void createMatrix(){
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++)
                matrix[i][j] = scanner.nextInt();
    }

    public void addVertex(E vertex){
        vertices.add(vertex);
    }

    public void generateMatrix(){
        Random random = new Random();
        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++)
                matrix[i][j]= random.nextInt(2);
    }

    private Integer[][] multiply(int x){
        Integer[][] local = matrix;
        Integer result[][]= new Integer[size][size];
        int index = 0;
        while(index!=x) {
            result = new Integer[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    result[i][j] = 0;
                    for (int k = 0; k < size; k++)
                        result[i][j] += local[i][k] * matrix[k][j];
                }
            }
                local = result;
                index++;
        }
        return result;
    }

    public void print(Integer[][]matrix){
        for(int i=0;i<matrix.length;i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%6s", matrix[i][j]);
            }
            System.out.println("\n");
        }
        System.out.println("\n");

    }

    public void printVerticles(int k){
        Set<Pair<Integer, Integer>> indexes = new HashSet<>();
        if(k>0) {
            int index = k;
            if(k==1){
                for(int i=0;i<size;i++)
                    for(int j=0;j<size;j++)
                        if(i==j)
                            indexes.add(new Pair<>(i,j));

            }
            else if (index == 2) {
                for (int i = 0; i < size; i++)
                    for (int j = 0; j < size; j++)
                        if (matrix[i][j] > 0)
                            indexes.add(new Pair<>(i, j));
            }
            else
                while (--index != 1) {
                Integer[][] result = multiply(index);
                for (int i = 0; i < size; i++)
                    for (int j = 0; j < size; j++)
                        if (result[i][j] > 0)
                            indexes.add(new Pair<>(i, j));


            }
            for (Pair pair : indexes) {
                System.out.printf("%1s%-2d%1s%2d%1s%n", "(", pair.getKey(), ",", pair.getValue(), ")");
            }
        }
        else
            System.out.println("Input error");
    }

    public static void main(String [] args){
        AdjacencyMatrix<String> adjacencyMatrix = new AdjacencyMatrix(9);
        adjacencyMatrix.generateMatrix();
        adjacencyMatrix.print(adjacencyMatrix.matrix);
        adjacencyMatrix.printVerticles(3);
    }






}
