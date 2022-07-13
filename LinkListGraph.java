class Link {
    public int iData;
    public Link next;

    public Link(int id) {
        iData = id;
    }

    public void displayLink() {
        System.out.print("{" + iData + "} ");
    }

    public void setID(int id){
        iData = id;
    }
}

class LinkList {
    private Link first;
    private Link current;

    public LinkList() {
        first = null;
    }

    private boolean isEmpty() {
        return (first == null);
    }

    public void insert(int id) {
        Link previous = null;
        Link newLink = new Link(id);
        current = first;
        while(current != null){
            previous = current;
            current = current.next;
        }
        if(previous == null)
            first = newLink;
        else
            previous.next = newLink;
        current = first;
    }

    public void insertInPosition(int pos){
        current = first;
        while(pos > 0){
            current = current.next;
            pos--;
        }
        current.setID(1);
        current = first;
    }

    public Link deleteFirst() {
        Link temp = first;
        first = first.next;
        current = first;
        return temp;
    }

    public int nextLink(){
        Link temp = current;
        current = current.next;
        return temp.iData;
    }

    public void setCurrent(){
        current = first;
    }
}
class StackX {
    private final int SIZE = 20;
    private int[] st;
    private int top;

    public StackX() {
        st = new int[SIZE];
        top = -1;
    }

    public void push(int j) {
        st[++top] = j;
    }

    public int pop() {
        return st[top--];
    }

    public int peek() {
        return st[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }
}

class Vertex {
    public char label;
    public boolean wasVisited;

    public Vertex(char lab) {
        label = lab;
        wasVisited = false;
    }
}

class Graph {
    private final int MAX_VERTS = 20;
    private Vertex vertexList[];
    private LinkList[] ll;
    private int nVerts;
    private StackX theStack;

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        ll = new LinkList[MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++) {
            ll[j] = new LinkList();
            for (int k = 0; k < MAX_VERTS; k++)
                ll[j].insert(0);
            theStack = new StackX();
        }
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        ll[start].insertInPosition(end);
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    public void dfs() {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        theStack.push(0);
        while(!theStack.isEmpty()) {
            int v = getAdjUnvisitedVertex(theStack.peek());
            if(v == -1)
                theStack.pop();
            else {
                vertexList[v].wasVisited = true;
                ll[v].setCurrent();
                displayVertex(v);
                theStack.push(v);
            }
        }
        for(int j=0; j<nVerts; j++)
            vertexList[j].wasVisited = false;
    }

    public int getAdjUnvisitedVertex(int v) {
        for(int j = 0; j<nVerts; j++)
            if(ll[v].nextLink() == 1 && vertexList[j].wasVisited == false) {
                ll[v].setCurrent();
                return j;
            }
        return -1;
    }
}

class LinkListGraph {
    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A');
        theGraph.addVertex('B'); // 1
        theGraph.addVertex('C'); // 2
        theGraph.addVertex('D'); // 3
        theGraph.addVertex('E');
        theGraph.addVertex('F');// 4
        theGraph.addEdge(0, 1); // AB
        theGraph.addEdge(1, 2); // BC
        theGraph.addEdge(0, 3); // AD
        theGraph.addEdge(3, 4);
        theGraph.addEdge(3, 5);// DE
        System.out.print("Visits: ");
        theGraph.dfs();
        System.out.println();
    }
}