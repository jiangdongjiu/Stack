import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Maze {
    private Point startingPoint;
    private int rowLength;
    private int columnLength;
    private char[][] charMaze;
    private boolean isSearched = false;
    private List<Point> pointSteps = new ArrayList<>();

    public Maze(String fileName){
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);

            List<Integer> rowsAndColumns = Arrays.stream(lines.get(0).split(" +"))
                    .map(Integer::parseInt).collect(Collectors.toList());

            rowLength = rowsAndColumns.get(0);
            columnLength = rowsAndColumns.get(1);

            List<Integer> startingPointRowColumn = Arrays.stream(lines.get(1).split(" +"))
                    .map(Integer::parseInt).collect(Collectors.toList());
            startingPoint = new Point(startingPointRowColumn.get(0), startingPointRowColumn.get(1));

            lines = lines.subList(2, lines.size());

            charMaze = new char[rowLength][];
            for (int i = 0; i < rowLength; i++){
                charMaze[i] = lines.get(i).toCharArray();
            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public Maze(int startingRow, int startingColumn, char[][] existingMaze) {
        char startingChar = existingMaze[startingRow][startingColumn];
        if (startingChar == 'E' || startingChar == 'W') {
            throw new UnsupportedOperationException();
        }
        startingPoint = new Point(startingRow, startingColumn);
        charMaze = existingMaze;
        rowLength = existingMaze.length;
        columnLength = existingMaze[0].length;
    }

    public int getRowLength() {
        return this.rowLength;
    }

    public int getColumnLength() {
        return this.columnLength;
    }

    public Point getStartingPoint() {
        return this.startingPoint;
    }

    public char[][] getMaze() {
        return this.charMaze;
    }

    public String printMaze() {
        StringBuilder maze = new StringBuilder();
        for (char[] line : this.charMaze) {
            String lineStr = new String(line);
            maze.append(lineStr).append("\n");
        }
        return maze.toString().trim();
    }

    public String depthFirstSearch() {
        this.isSearched = true;

        Stack<Point> stack = new Stack<>();
        stack.push(this.startingPoint);
        String returnedValue;

        while (true) {
            Point p = stack.top();
            if (charMaze[p.getRow()][p.getColumn()]  != 'E'){
                charMaze[p.getRow()][p.getColumn()] = 'V';
            }

            if (charMaze[p.getRow()][p.getColumn()]  == 'E') {
                int stackSize = stack.getSize();

                List<Point> steps = new ArrayList<>();
                steps.add(stack.pop());

                while (!stack.isEmpty()) {
                    charMaze[stack.top().getRow()][stack.top().getColumn()] = '.';
                    steps.add(stack.pop());
                }

                Collections.reverse(steps);
                this.pointSteps = steps;
                StringBuilder stringSteps = new StringBuilder();

                for (Point s : steps) {
                    stringSteps.append(s);
                    stringSteps.append("\n");
                }

                returnedValue = "Path to follow from Start " + this.startingPoint +
                        " to Exit " + p + " - " + stackSize + " steps:\n" +
                        stringSteps + printMaze();

                break;
            }
            else if (charMaze[p.getRow()+1][p.getColumn()] == ' ' ||
                    charMaze[p.getRow()+1][p.getColumn()] == 'E') {
                stack.push(new Point(p.getRow()+1, p.getColumn()));
            }
            else if (charMaze[p.getRow()][p.getColumn()+1] == ' ' ||
                    charMaze[p.getRow()][p.getColumn()+1] == 'E') {
                stack.push(new Point(p.getRow(), p.getColumn()+1));
            }
            else if (charMaze[p.getRow()][p.getColumn()-1] == ' ' ||
                    charMaze[p.getRow()][p.getColumn()-1] == 'E') {
                stack.push(new Point(p.getRow(), p.getColumn()-1));
            }
            else if (charMaze[p.getRow()-1][p.getColumn()] == ' ' ||
                    charMaze[p.getRow()-1][p.getColumn()] == 'E') {
                stack.push(new Point(p.getRow()-1, p.getColumn()));
            }
            else {
                stack.pop();
                if (stack.isEmpty()) {
                    returnedValue = "No exit found in maze!\n\n" + printMaze();
                    break;
                }
            }
        }

        return returnedValue;
    }

    public Stack<Point> getPathToFollow() {
        if (!this.isSearched) {
            throw new UnsupportedOperationException();
        }
        Stack<Point> returnedStack = new Stack<>();
        Collections.reverse(pointSteps);
        for (Point step : pointSteps) {
            returnedStack.push(step);
        }
        Collections.reverse(pointSteps);
        return returnedStack;

    }
}
