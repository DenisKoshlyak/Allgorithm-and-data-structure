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
    private int adjMat[][];
    private int connectMat[][];
    private int nVerts;
    private StackX theStack;

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        connectMat = new int[MAX_VERTS][MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for(int j=0; j<MAX_VERTS; j++)
            for(int k=0; k<MAX_VERTS; k++) {
                adjMat[j][k] = 0;
                connectMat[j][k] = 0;
            }
        theStack = new StackX();
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
    }

    public void dfs(int num) {
        vertexList[num].wasVisited = true;
        theStack.push(num);
        while(!theStack.isEmpty()) {
            int v = getAdjUnvisitedVertex( theStack.peek() );
            if(v == -1)
                theStack.pop();
            else {
                vertexList[v].wasVisited = true;
                connectMat[num][v] = 1;
                theStack.push(v);
            }
        }
        for(int j=0; j<nVerts; j++)
            vertexList[j].wasVisited = false;
        if(num < nVerts - 1){
            System.out.println();
            num++;
            dfs(num);
        }
    }

    public void findWay(int start, int end){
        if(connectMat[start][end] == 1)
            System.out.println("Find way between " + vertexList[start].label
                    + " and "+ vertexList[end].label);
        else
            System.out.println("Dont't find way between " + vertexList[start].label
                    + " and "+ vertexList[end].label);
    }

    public int getAdjUnvisitedVertex(int v) {
        for(int j=0; j<nVerts; j++)
            if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
                return j;
        return -1;
    }
}

class WarshellsAlgorithm {
    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A');
        theGraph.addVertex('B'); // 1
        theGraph.addVertex('C'); // 2
        theGraph.addVertex('D'); // 3
        theGraph.addVertex('E');
        theGraph.addVertex('F');// 4
        theGraph.addEdge(0, 1); // AB
        theGraph.addEdge(1, 3);
        theGraph.addEdge(3, 4);
        theGraph.addEdge(3, 5);// DE
        theGraph.dfs(0);
        theGraph.findWay(0, 3);
    }
}