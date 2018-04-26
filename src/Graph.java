import java.util.*;
/**
 * Created by Agata Rychter on 02.06.2017.
 */
public class Graph {
    private final Set<Vertex> vertices;
    private Stack<Vertex> path = new Stack<>();
    private Set<Vertex> onPath = new HashSet<>();
    private Stack<String> pathNames = new Stack<>();
    private Set<String> colours;
    private static final String RED = "red";
    private static final String YELLOW = "yellow";
    private static final  String BLUE = "blue";
    private static final String GREEN = "green";


    public Graph(String name,String colour){
        vertices = new HashSet<>();
        vertices.add(new Vertex(name,colour));
        colours = new HashSet<>();
        colours.add(RED);
        colours.add(GREEN);
        colours.add(BLUE);
        colours.add(YELLOW);
    }


    public boolean addVertex(String name,String colour, String vertexName){
        if(!vertices.contains(new Vertex(name,colour))) {
            if(!colours.contains(colour)) {
                System.out.println("Wrong line colour");
                return false;
            }
            Vertex vertex = getVertex(vertexName);
            Vertex toAdd = new Vertex(name,colour);
            vertices.add(toAdd);
            vertex.getAdjacencyList().add(toAdd);
            toAdd.getAdjacencyList().add(vertex);
            return true;

        }
        return  false;

    }

    public boolean shareColours(Vertex first, Vertex second){
        for(String string: first.getColourList())
            if(second.getColourList().contains(string))
                return true;
        return false;
    }

    public boolean addSeperate(String name, String colour){
        if(!colours.contains(colour)) {
            System.out.println("Wrong line colour");
            return false;
        }
        if(!vertices.contains(new Vertex(name,colour)))
       return vertices.add(new Vertex(name,colour));
        return false;
    }

    public void addColour(String name, String colour){
        if(!colours.contains(colour)) {
            System.out.println("Wrong line colour");
        }
        else
        getVertex(name).addColour(colour);

    }

    public void clear(){
        for(Vertex verticle: vertices){
            verticle.unvisit();
        }
    }

    public void addEdge(String first, String second){
        getVertex(first).getAdjacencyList().add(getVertex(second));
        getVertex(second).getAdjacencyList().add(getVertex(first));
    }

    public Vertex getVertex(String name){
        for(Vertex vertex: vertices){
            if(vertex.getName().equalsIgnoreCase(name))
                return vertex;
        }
        return null;
    }

    public Vertex findLastInterchange(Queue<Vertex> queue){
        Vertex toReturn = null;
        for(Vertex verticle:queue){
            if(verticle.getIsInterchange())
                toReturn = verticle;
        }
        return  toReturn;
    }

    public void findWay(String from, String to){
        clear();
        Vertex fromV = getVertex(from);
        Vertex toV = getVertex(to);
        if(fromV!=null &&toV!=null){
            Queue<Vertex> toPrint = new LinkedList<>();
            StringBuffer stringBuffer = new StringBuffer();
            Stack<Vertex> stack = new Stack<>();
            stack.push(fromV);
            toPrint.offer(fromV);
            fromV.visit();
            String lastColour = fromV.getColourList().peek();
            while (!stack.isEmpty()){
                Vertex temporary = stack.pop();
                System.out.println(temporary.getName());

                temporary.visit();

                if(temporary.getName().equalsIgnoreCase(to)) {
                    stringBuffer.append(to);
                    toPrint.offer(temporary);
                    System.out.print(from +  " => " + stringBuffer.toString()+ "(" +lastColour + ")" + "\n");
                    stringBuffer = new StringBuffer();
                    temporary.unvisit();
                }
                else {

                    if (temporary.getIsInterchange()) {
                        stringBuffer.append(temporary.getName() + " (line " + "?" + ")" + " => ");
                        stringBuffer.append(temporary.getName() + " => ");
                        toPrint.offer(temporary);
                    } else {
                        if (!temporary.getColourList().peek().equalsIgnoreCase(fromV.getColourList().peek())) {
                            if (findLastInterchange(toPrint) != null)
                                findLastInterchange(toPrint).changeLine();
                        } else if (findLastInterchange(toPrint) != null)
                            findLastInterchange(toPrint).setChangeLine(false);
                    }


                    for (Vertex verticle : temporary.getAdjacencyList())
                        if (!verticle.getVisited())
                            stack.push(verticle);
                }
                }

            }
        else
             System.out.println("Wrong station entered");
    }

    private void addToArray(Stack<Vertex> []array, Stack<Vertex> toAdd){
        boolean added = false;
        for(int i=0;i<array.length;i++){
            if(array[i]==null){
                array[i] = toAdd;
                added = true;
                return;
            }
        }
        if(!added){
            array = (Stack<Vertex>[])new Object[array.length+1];
            addToArray(array,toAdd);
        }
    }

    private void enumerate(String from, String to,Stack<Vertex>[] paths){

        Vertex temp = getVertex(from);

        pathNames.push(from);
        path.push(temp);
        onPath.add(temp);
        if(from.equalsIgnoreCase(to)){
           addToArray(paths,(Stack<Vertex>)path.clone());
        }
        else {
            for (Vertex w : temp.getAdjacencyList()) {
                if (!onPath.contains(w))
                    enumerate( w.getName(), to,paths);
            }
        }
        path.pop();
        pathNames.pop();
        onPath.remove(temp);
    }

    private String sharedColour(Vertex first, Vertex second){
        for(String string: first.getColourList())
            if(second.getColourList().contains(string))
                return string;
        return null;
    }

    public void enumerate(String from, String to){
         StringBuffer stringBuffer = new StringBuffer();
         Vertex fromVertex = getVertex(from);
         Vertex toVertex = getVertex(to);
         if(fromVertex!=null &&toVertex!=null) {
             Stack<Vertex>[] pathsArray = (Stack<Vertex>[]) new Stack[colours.size()];
             enumerate(from, to, pathsArray);
             int j = 0;
             while (pathsArray[j] != null) {
                 Stack<Vertex> temp = pathsArray[j];
                 ArrayList<Vertex> toPrint = new ArrayList<>(temp.size());

                 while (!temp.isEmpty()) {
                     Vertex vertex = temp.pop();
                     toPrint.add(vertex);
                 }
                 toPrint.get(0).setPrint(true);
                 for (int i = 1; i < toPrint.size() - 1; i++) {
                     if (toPrint.get(i).getIsInterchange() && !shareColours(toPrint.get(i + 1), toPrint.get(i - 1)))
                         toPrint.get(i).setPrint(true);
                 }
                 toPrint.get(toPrint.size() - 1).setPrint(true);
                 StringBuffer stringBuffer1 = new StringBuffer();
                 for (int k = toPrint.size() - 1; k >= 0; k--) {
                     Vertex vertex = toPrint.get(k);
                     if (vertex.getPrint()) {
                         if (k == toPrint.size() - 1)
                             stringBuffer1.append(vertex);
                         else if (vertex.getIsInterchange() && k == 0) {
                             stringBuffer1.append("=>");
                             stringBuffer1.append(vertex + "");
                             stringBuffer1.append("(" + sharedColour(vertex, toPrint.get(k + 1)) + ")" + " ");
                         } else if (vertex.getIsInterchange()) {
                             stringBuffer1.append("=>");
                             stringBuffer1.append(vertex + "");
                             stringBuffer1.append("(" + sharedColour(vertex, toPrint.get(k + 1)) + ")" + " ");
                             stringBuffer1.append(vertex + " ");
                         } else if (k == 0)
                             stringBuffer1.append("=>" + vertex + "(" + sharedColour(vertex, toPrint.get(k + 1)) + ")");
                     }


                 }
                 System.out.println(stringBuffer1.toString() + "\n");
                 clearToPrint(toPrint);
                 j++;
             }
             path = new Stack<>();
             onPath = new HashSet<>();
             pathNames = new Stack<>();
         }
         else
             System.out.println("Wrong station entered");
    }

    private void clearToPrint(ArrayList<Vertex> arrayList){
        for(Vertex vertex: arrayList){
            vertex.setPrint(false);
        }

    }

}

