class Point{
    private int X;
    private int Y;
    private int Move;

    public Point(int x, int y, int move){
        X = x;
        Y = y;
        Move = move;
    }

    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }

    public int getMove(){
        return Move;
    }
}

class StackX {
    private final int SIZE = 100;
    private Point[] coord;
    private int top;

    public StackX() {
        coord = new Point[SIZE];
        top = -1;
    }

    public void push(Point start) {
        coord[++top] = start;
    }

    public Point pop() {
        return coord[top--];
    }

    public Point peek() {
        if(top == -1)
            return new Point(0, 0, 1);
        else
            return coord[top];
    }

    public boolean isEmpty(){
        return top == -1;
    }
}

class Graph {
    int x = 0, y =0;
    private final byte MAX_VERTS = 5;
    private byte adjMat[][];
    private int path[];
    private StackX theStack;
    private int count = 0;

    public Graph() {
        adjMat = new byte[MAX_VERTS][MAX_VERTS + 1];
        for (int j = 0; j < MAX_VERTS; j++)
            for (int k = 0; k < MAX_VERTS; k++) {
                adjMat[j][k] = 0;
            }
        theStack = new StackX();
    }

    public void startCalculation() {
        theStack.push(new Point(x, y, 1));
        nextStep();
        System.out.println(count);
    }

    private void nextStep() {
        Point end;
        int mark = 0;
        Point newStart;
        int num = 1;
        while(!theStack.isEmpty() && ++mark == 1){
             newStart = theStack.peek();
             end = choiseStep(newStart, num);
             if(newStart.getX() == end.getX() && num > 8){
                 newStart = theStack.peek();
                 num = newStart.getMove() + 1;
                 stepBack();
             }
             else if(newStart.getX() == end.getX()){
                 num++;
             }
             else{
                 num = 1;
                 theStack.push(end);
             }
             count++;

        }
        if(mark == 0 && x <= MAX_VERTS - 2 && y < MAX_VERTS - 1) {
            if(x == 3) {
                ++y;
                startCalculation();
            }
            else{
                ++x;
                startCalculation();
            }
        }
    }

    private Point choiseStep(Point start, int num){
        Point end = start;
        switch (num) {
            case 5:
                end = rightDown(start);
                break;
            case 2:
                end = downLeft(start);
                break;
            case 3:
                end = upRight(start);
                break;
            case 4:
                end = upLeft(start);
                break;
            case 1:
                end = downRight(start);
                break;
            case 6:
                end = rightUp(start);
                break;
            case 8:
                end = leftDown(start);
                break;
            case 7:
                end = leftUp(start);
                break;
        }
        return end;
    }

    private void stepBack() {
        System.out.println("StepBack");
        Point deleted = theStack.pop();
        Point start = theStack.peek();
        int move = deleted.getMove();
        switch (move) {
            case 1:
                adjMat[start.getX() + 2][start.getY() + 1] = 0;
                break;
            case 2:
                adjMat[start.getX() + 2][start.getY() - 1] = 0;
                break;
            case 3:
                adjMat[start.getX() - 2][start.getY() + 1] = 0;
                break;
            case 4:
                adjMat[start.getX() - 2][start.getY() - 1] = 0;
                break;
            case 5:
                adjMat[start.getX() + 1][start.getY() + 2] = 0;
                break;
            case 6:
                adjMat[start.getX() - 1][start.getY() + 2] = 0;
                break;
            case 7:
                adjMat[start.getX() - 1][start.getY() - 2] = 0;
                break;
            case 8:
                adjMat[start.getX() + 1][start.getY() - 2] = 0;
                break;
        }
    }

    private boolean checkMatrix(){
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS + 1; j++)
                if(adjMat[i][j] == 0)
                   return false;
        }
        return true;
    }

    private Point downRight(Point start) {
        int x = start.getX() + 2;
        int y = start.getY() + 1;
        if (!checkCell(x, y))
            return start;
        Point end = new Point(x, y, 1);
        adjMat[start.getX() + 2][start.getY() + 1] = 1;
        System.out.println("DownRight"+ "{" + x + "; " + y + "}");
        return end;
    }

    private Point downLeft(Point start) {
        int x = start.getX() + 2;
        int y = start.getY() - 1;
        if (!checkCell(x, y))
            return start;
        Point end = new Point(x, y, 2);
        adjMat[start.getX() + 2][start.getY() - 1] = 1;
        System.out.println("DownLeft"+ "{" + x + "; " + y + "}");
        return end;
    }

    private Point upRight(Point start) {
        int x = start.getX() - 2;
        int y = start.getY() + 1;
        if (!checkCell(x, y))
            return start;
        Point end = new Point(x, y, 3);
        adjMat[start.getX() - 2][start.getY() + 1] = 1;
        System.out.println("UpRight"+ "{" + x + "; " + y + "}");
        return end;
    }

    private Point upLeft(Point start) {
        int x = start.getX() - 2;
        int y = start.getY() - 1;
        if (!checkCell(x, y))
            return start;
        Point end = new Point(x, y, 4);
        adjMat[start.getX() - 2][start.getY() - 1] = 1;
        System.out.println("UpLeft"+ "{" + x + "; " + y + "}");
        return end;
    }

    private Point rightDown(Point start) {
        int x = start.getX() + 1;
        int y = start.getY() + 2;
        if (!checkCell(x, y))
            return start;
        Point end = new Point(x, y, 5);
        adjMat[start.getX() + 1][start.getY() + 2] = 1;
        System.out.println("RightDown"+ "{" + x + "; " + y + "}");
        return end;
    }

    private Point rightUp(Point start) {
        int x = start.getX() - 1;
        int y = start.getY() + 2;
        if (!checkCell(x, y))
            return start;
        Point end = new Point(x, y, 6);
        adjMat[start.getX() - 1][start.getY() + 2] = 1;
        System.out.println("RightUp"+ "{" + x + "; " + y + "}");
        return end;
    }

    private Point leftUp(Point start) {
        int x = start.getX() - 1;
        int y = start.getY() - 2;
        if (!checkCell(x, y))
            return start;
        Point end = new Point(x, y, 7);
        adjMat[start.getX() - 1][start.getY() - 2] = 1;
        System.out.println("LeftUp" + "{" + x + "; " + y + "}");
        return end;
    }

    private Point leftDown(Point start) {
        int x = start.getX() + 1;
        int y = start.getY() - 2;
        if (!checkCell(x, y))
            return start;
        Point end = new Point(x, y, 8);
        adjMat[start.getX() + 1][start.getY() - 2] = 1;
        System.out.println("LeftDown"+ "{" + x + "; " + y + "}");
        return end;
    }

    private boolean checkCell(int x, int y) {
        if (x > MAX_VERTS - 1 || y > MAX_VERTS - 1)
            return false;
        else if (x < 0 || y < 0)
            return false;
        else if (adjMat[x][y] == 0)
            return true;
        else
            return false;
    }

    public void displayMatrix() {
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++)
                System.out.print(adjMat[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}

class KnightMove {
    public static void main(String[] args)  throws NullPointerException {
        Graph theGraph = new Graph();
        try {
            theGraph.startCalculation();
            theGraph.displayMatrix();
        }
        catch(NullPointerException e){}
    }
}