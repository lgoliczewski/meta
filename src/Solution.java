
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    private int size;
    public ArrayList<Point2D.Double> node_coord_list; // na wypadek euklidesowego i wizualizacji
    public ArrayList<Integer> order;
    private int[][] edge_weight_matrix;

    public void randomOrder(){
        Collections.shuffle(order);
    }

    public Solution(int size, int[][] edge_weight_matrix){
        order = new ArrayList<>();
        this.edge_weight_matrix = edge_weight_matrix;
        this.size = size;
        int i = 0;
        while(i<size){
            order.add(i+1);
            i++;
        }
    }

    public void printOrder(){
        int i = 0;
        while(i<size){
            System.out.print(order.get(i) + " ");
            i++;
        }
        System.out.println(" ");
    }

    public int totalDistance() {
        int sum = 0;
        int j1, j2;
        for (int i = 0; i < size; i++) {
            j1 = order.get(i) - 1;
            if(i < size - 1) j2 = order.get(i+1) -1;
            else j2 = order.get(0) - 1;
            sum += edge_weight_matrix[j1][j2];
        }

        return sum;
    }

}