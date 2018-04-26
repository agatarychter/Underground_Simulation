import java.util.LinkedList;
/**
 * Created by Agata Rychter on 02.06.2017.
 */
public class Vertex implements Comparable {
    private String name;
    private boolean isInterchange;
    private boolean visited;
    private final LinkedList<Vertex> adjacencyList;
    private final LinkedList<String> colourList;
    private boolean changeLine;
    private boolean print;

    public Vertex(String name, String colour){
    this.name = name;
    this.adjacencyList = new LinkedList<>();
    this.colourList = new LinkedList<>();
    colourList.add(colour);
    visited = false;
    changeLine = false;
    print = false;
    }

    public String toString(){
        return this.name;
    }

    public boolean getPrint(){
        return print;
    }

    public void setPrint(boolean print){
        this.print = print;
    }

    public String getName(){
        return name;
    }
    public boolean getChangeLine(){
        return changeLine;
    }

    public void changeLine(){
        changeLine = true;
    }

    public void unvisit(){
        visited = false;
    }

    public void visit(){
        visited = true;
    }

    public boolean getVisited(){
        return visited;
    }

    public boolean getIsInterchange(){
        return checkColourList();
    }

    public boolean checkColourList(){
        if(colourList.size()==1)
            return false;
        return true;
    }

    public void setName(String name){
        this.name =name;
    }


    public void setInterchange(boolean isInterchange){
        this.isInterchange = isInterchange;
    }

    public LinkedList<Vertex> getAdjacencyList(){
        return adjacencyList;
    }

    public boolean equals(Vertex verticle){
        if(this.name.equals(verticle.name))
            return true;
        return false;
    }

    public void addColour(String colour){
        colourList.add(colour);
        isInterchange = checkColourList();
    }

    public LinkedList<String> getColourList(){
        return  colourList;
    }

    public void setChangeLine(boolean set){
        changeLine = set;
    }

    @Override
    public int compareTo(Object o) {
        return this.getName().compareTo(((Vertex)o).getName());
    }

}