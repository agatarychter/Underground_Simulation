import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Agata Rychter on 02.06.2017.
 */
public class Test {

    public static void main(String [] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Graph graph = new Graph("S.Sebastiao","blue");
        graph.addVertex("Parque","blue","S.Sebastiao");
        graph.addVertex("Marques de Pombal", "blue","Parque");
        graph.addColour("Marques de Pombal", "yellow");
        graph.addVertex("Picoas", "yellow","Marques de Pombal");
        graph.addVertex("Rato", "yellow","Marques de Pombal");
        graph.addVertex("Saldanha", "yellow","Picoas");
        graph.addVertex("Campo Pequeno", "yellow","Saldanha");
        graph.addVertex("Entre Campos", "yellow","Campo Pequeno");
        graph.addVertex("Cidade Universitaria", "yellow","Entre Campos");
        graph.addVertex("Campo Grande", "yellow","Cidade Universitaria");
        graph.addColour("Campo Grande","green");
        graph.addVertex("Alvalade","green","Campo Grande");

        graph.addVertex("Telheiras", "green","Campo Grande");
        graph.addVertex("Quinta das Conchas", "yellow","Campo Grande");
        graph.addVertex("Lumiar", "yellow","Quinta das Conchas");
        graph.addVertex("Ameixoeria", "yellow","Lumiar");
        graph.addVertex("Senihor Roubardo", "yellow","Ameixoeria");
        graph.addVertex("Odlivelas", "yellow","Senihor Roubardo");
        graph.addVertex("Avenida","blue","Marques de Pombal");
        graph.addVertex("Restauradores","blue","Avenida");
        graph.addVertex("Baixa Chiado","blue","Restauradores");
        graph.addColour("Baixa Chiado","green");
        graph.addVertex("Rossio","green","Baixa Chiado");
        graph.addVertex("Martim Moniz","green","Rossio");
        graph.addVertex("Intendende","green","Martim Moniz");
        graph.addVertex("Anjos","green","Intendende");
        graph.addVertex("Arrojos","green","Anjos");

        graph.addVertex("Alameda","green","Arrojos");
        graph.addColour("Alameda","red");
        graph.addVertex("Areeiro","green","Alameda");
        graph.addVertex("Roma","green","Areeiro");

        graph.addEdge("Roma","Alvalade");
        graph.addVertex("Olailas","red","Alameda");
        graph.addVertex("Cais do Sodre","green","Baixa Chiado");
        graph.addVertex("Praca de Espanha","blue","S.Sebastiao");
        graph.addVertex("Bela Vista","red","Olailas");
        System.out.println("From: ");
        String from = bufferedReader.readLine();
        System.out.println("To: ");
        String to = bufferedReader.readLine();
        graph.enumerate(from,to);

    }
}
