package components.NodeHandle;

import javafx.scene.paint.Color;

import java.util.*;

public class Vertices {
    private ArrayList<Vertex> verticesSet = new ArrayList<>();
    private Color[] colorSet;
    private double[][] positions;
    private int[][] graph;
    private ArrayList<Color> uniqueColor = new ArrayList<>();
    private ArrayList<Integer> orderPick = new ArrayList<>();
    private int randomPick;

    public Vertices() {
    }

    public Vertices(int[][] graph,int width, int height) {
        this.graph = graph;
        this.colorSet = new Color[graph.length];
        this.positions = new double[graph.length][2];
        for (int i=0;i<graph.length;i++){
            this.orderPick.add(i);
        }
    }


    public int getNumberVertices(){
        return this.verticesSet.size();
    }

    public void setGraphPosition(int width, int height){
        int numVertices = this.graph.length;
        double centerX = width/2; // ensure that the first position is in the center
        double centerY = height/2; // ensure that the first position is in the center
        double radius = Math.min(centerX, centerY);
        for (int i = 0; i < numVertices; i++) {
            double angle = 2 * Math.PI * i / numVertices;
            this.positions[i][0] = centerX + radius * Math.cos(angle);
            this.positions[i][1] = centerY + radius * Math.sin(angle);
        }
    }

    public void setColorIndex(int index,Color c){
        this.colorSet[index] = c;
    }

    public Color getColorIndex(int index){
        return this.colorSet[index];
    }

    public void checkUniqueColor(){
        Set<Integer> sameColor = new HashSet<>();
        for (int i=0; i < colorSet.length; i++){
            if (sameColor.contains(i)){
                continue;
            }
            Color c1 = colorSet[i];
            uniqueColor.add(c1);
            for (int j=0; j < colorSet.length; j++){
               if (i != j){
                   Color c2 = colorSet[j];
                   double red1 = c1.getRed();
                   double green1 = c1.getGreen();
                   double blue1 = c1.getBlue();
                   double red2 = c2.getRed();
                   double green2 = c2.getGreen();
                   double blue2 = c2.getBlue();
                   double distance = Math.sqrt(Math.pow(red2 - red1, 2) + Math.pow(green2-green1,2) + Math.pow(blue2 - blue1, 2));

                   if (distance < 0.3){
                       sameColor.add(j);
                   }
               }
            }
        }
    }

    public int getUniqueColors(){
        int numUniqueColors = this.uniqueColor.size();
        return numUniqueColors;
    }


    public boolean checkValidColor(int index, Color c){
        for (int i = 0; i < this.graph[index].length; i++) {
            if (graph[index][i] == 1 && colorSet[i] != null){
                double red1 = c.getRed();
                double green1 = c.getGreen();
                double blue1 = c.getBlue();
                double red2 = colorSet[i].getRed();
                double green2 = colorSet[i].getGreen();
                double blue2 = colorSet[i].getBlue();

                double distance = Math.sqrt(Math.pow(red2 - red1, 2) + Math.pow(green2-green1,2) + Math.pow(blue2 - blue1, 2));

                if (distance < 0.3){
                    return false;
                }
            }
        }
        return true;
    }


    public void addVertex(Vertex vertex){
        verticesSet.add(vertex);
    }

    public double[][] getPositions(){
        for (double[] position : this.positions){
            System.out.println(Arrays.toString(position));
        }
        System.out.println("end");
        return this.positions;
    }

    public void randomOrder(){
        Random rand = new Random();
        Collections.shuffle(this.orderPick);
        int randomIndex = rand.nextInt(this.orderPick.size());
        getVertex(this.orderPick.get(randomIndex)).getCircle().setStrokeWidth(5);
        setRandomPick(this.orderPick.get(randomIndex));
        this.orderPick.remove(randomIndex);
    }


    public void setLocationX(int i, double x){
        this.positions[i][0] = x;
    }

    public void setLocationY(int i, double y){
        this.positions[i][1] = y;
    }

    public double getLocationX(int i){
        return this.positions[i][0];
    }

    public double getLocationY(int i){
        return this.positions[i][1];
    }

    public Vertex getVertex(int index){
        return this.verticesSet.get(index);
    }

    public void setRandomPick(int index){
        this.randomPick = index;
    }

    public int getRandomPick(){
        return this.randomPick;
    }

    public int getOrderPick(){
        return this.orderPick.size();
    }

    public Vertex[] getAll(){
        return this.verticesSet.toArray(new Vertex[this.verticesSet.size()]);
    }
}
