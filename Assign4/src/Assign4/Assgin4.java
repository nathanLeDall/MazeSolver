package Assign4;
/*
Name: Nathan Le Dall
ID: 7241250
 */
import BasicIO.*;
import Media.*;
import static java.lang.Math.PI;

public class Assgin4 {
    TurtleDisplayer a;
    Turtle t;
    ASCIIDataFile f;
    int[] start;
    char[][] matrix;
    public Assgin4()
    {
        //drawing the koch curve
        kochCurve();
        //solving the maze
        mazeSolve();
    }

    /**
     * solves a maze entered by the user
     */
    private void mazeSolve()
    {
        f = new ASCIIDataFile();
        load();
        start = startPoint();
        findPath(start[0],start[1]);
    }

    /**
     * startPoint the start point onto the maze after its been solved
     * @param x the x coordinate where the start point should be (0-based)
     * @param y the y coordinate where the start point should be (0-based)
     */
    private void startPoint(int x, int y)
    {
        matrix[y][x]='S';
    }

    /**
     * startPoint the "start point" on the maze before its been solved
     */
    private int[] startPoint()
    {
        while (true)
        {
            int randX = (int) (Math.random() *(matrix[0].length));
            int randY = (int) (Math.random() *(matrix.length));
            if (matrix[randY][randX] == ' ')
            {
                matrix[randY][randX] = 'S';
                return new int[]{randX,randY};
            }
        }
    }

    /**
     * recursively solves the maze
     * @param x the current x coordinate(0=based)
     * @param y the current x coordinate(0=based)
     */
    private void findPath(int x,int y)
    {
        if (matrix[y][x] == '#' || matrix[y][x]=='.') {return;}
        else if (matrix[y][x]=='E') {
            startPoint(start[0],start[1]);
            output();
            System.exit(0);
        }
        else{
            if (matrix[y][x+1]==' ' && matrix[y][x+1]!='#' || matrix[y][x+1]=='E') {
                matrix[y][x] = '>';
                findPath(x + 1, y);
            }
            if (matrix[y+1][x]==' ' && matrix[y+1][x]!='#' || matrix[y+1][x]=='E') {
                matrix[y][x] = 'v';
                findPath(x, y + 1);
            }
            if (matrix[y-1][x]==' ' && matrix[y-1][x]!='#'|| matrix[y-1][x]=='E'){
                matrix[y][x] = '^';
                findPath(x, y - 1);
            }
            if (matrix[y][x-1]==' ' && matrix[y][x-1]!='#' || matrix [y][x-1]=='E') {
                matrix[y][x] = '<';
                findPath(x - 1, y);
            }
            matrix[y][x] = '.';
        }
    }

    /**
     * outputs the final result to a text file
     */
    private void output()
    {
        ASCIIOutputFile file = new ASCIIOutputFile();
        StringBuilder tmp;
        for (char[] chars : matrix) {
            tmp = new StringBuilder();
            for (char aChar : chars) {
                tmp.append(aChar);
            }
            file.writeLine(tmp.toString());
        }
    }

    /**
     * reads the maze file and writes to a matrix
     */
    private void load()
    {
        matrix = new char[f.readInt()][f.readInt()];
        for (int i=0;i<matrix.length;i++)
        {
            String tmp = f.readLine();
            for (int j=0;j<matrix[i].length;j++)
            {
                matrix[i][j] = tmp.charAt(j);
            }
        }
    }

    /**
     * draws the koch curve by calling the recursive method koch
     * in a loop
     */
    private void kochCurve()
    {
        t = new Turtle(0);
        a = new TurtleDisplayer(t,400,400);
        t.left(Math.PI/2);
        t.forward(30);
        t.left(Math.PI/2);
        t.forward(40);
        t.right(Math.PI);
        t.penDown();
        /*
        call the koch method 10 times and rotates the turtle each time so it will go full circle drawing another curve every iteration of the loop
         */
        for (int i=0;i<10;i++)
        {
            koch(4,120);
            t.right(2* PI/10);
        }
    }

    /**
     * draws a koch curve recursively
     * @param order the number of times the curve should be broken down
     * @param len the length of the line to be drawn
     */
    private void koch ( int order, double len ) {

        if ( order == 0 ) {
            t.forward(len);
        }
        else {
            koch(order-1, len/3);
            t.right(PI/4);
            koch(order-1,len/3);
            t.left(2*PI/4);
            koch(order-1,len/3);
            t.right(PI/4);
        }

    }
    public static void main(String[] args) {Assgin4 a  = new Assgin4();}
}
