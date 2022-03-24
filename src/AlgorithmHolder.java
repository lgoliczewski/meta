import javax.swing.plaf.synth.SynthToolBarUI;
import java.io.*;
import java.util.ArrayList;

public class AlgorithmHolder {

    Solution solution;
    Solution twoOptSolution;
    Solution candidate;
    Solution holder;
    int distance;

    public Solution KRandomAlgorithm(Instance instance, int k) {
        int i = 0;
        solution = instance.getSolution();
        solution.randomOrder();
        Solution holder = null;
        byte[] byteData = new byte[0];

        while (i < k) {
            solution.randomOrder();
            if (i == 0) {
                distance = solution.totalDistance();
                //holder = solution;
                holder = solution.copy();
            } else if(solution.totalDistance() < distance) {
                distance = solution.totalDistance();

                holder = solution.copy();
                /*ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(solution);
                oos.flush();
                oos.close();
                bos.close();
                byteData = bos.toByteArray();*/
            }
            i++;
            //System.out.println("Aktualne: " + distance  + ", Wylosowane: " + solution.totalDistance());

        }

        //solution.printPoints();
        //solution.printMatrix();
        System.out.println(holder.order);
        //System.out.println(solution.order);
        /*ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
        Solution finalSolution = (Solution) new ObjectInputStream(bais).readObject();*/
        Solution finalSolution = holder.copy();
        finalSolution.frameTitle = "k-Random Solution";
        return finalSolution;
    }

    public Solution TwoOptAlgorithm(Instance instance) throws IOException {

        holder = instance.getSolution();

        boolean isImproved = true;
        byte[] byteData;
        while (isImproved) {
            isImproved = false;

            /*ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(holder);
            oos.flush();
            oos.close();
            bos.close();
            byteData = bos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
            holder = (Solution) new ObjectInputStream(bais).readObject();*/

            int i = 1;
            int j;
            while(i<=holder.size && !isImproved){
                j = 1;
                while(j<=holder.size && !isImproved){
                    /*ByteArrayInputStream bais2 = new ByteArrayInputStream(byteData);
                    candidate = (Solution) new ObjectInputStream(bais2).readObject();
                    candidate = invert(candidate,i,j);
                    if(candidate.totalDistance()<holder.totalDistance()){
                        holder = candidate;
                        isImproved = true;
                    }*/
                    candidate = holder.copy();
                    candidate = invert(candidate,i,j);
                    if(candidate.totalDistance()<holder.totalDistance()){
                        holder = candidate.copy();
                        isImproved = true;
                    }

                    System.out.println("i = " + i + ", j = " + j);
                    j++;
                }
                i++;
            }
        }

        holder.frameTitle = "2-OPT Solution";

        return holder;
    }

    public Solution invert(Solution solution, int i, int j) {
        System.out.println("Jestem in");
        if(i>j){
            int pomi = i;
            int pomj = j;
            j = pomi;
            i = pomj;
        }
        int[] pom =  new int[j-i+1];
        int it = 0;
        while(it<=j-i){
            pom[it] = solution.order.get(it+i-1);
            it++;
        }
        it = 0;
        while(it<=j-i){
            solution.order.set(it+i-1,pom[(j-i)-it]);
            it++;
        }
        return solution;
    }

    public Solution NearestNeighbor(Instance instance){
        holder = NearestNeighborWithStartIndex(instance, 1);
        holder.frameTitle = "Nearest Neighbor Solution";
        return holder;
    }

    public Solution ExNearestNeighbor(Instance instance){

        int currLowestDistance = Integer.MAX_VALUE;
        int distance;
        for(int i = 1; i <= instance.getDimension(); i++) {
            candidate = NearestNeighborWithStartIndex(instance, i);

            distance = candidate.totalDistance();
            if (distance < currLowestDistance) {
                currLowestDistance = distance;
                holder = candidate;
            }
        }
        holder.frameTitle = "Expanded Nearest Neighbor Solution";

        return holder;
    }

    private Solution NearestNeighborWithStartIndex(Instance instance, int start){
        solution = new Solution();
        solution.setFields(instance);
        ArrayList<Integer> notVisited = solution.order;
        solution.order = new ArrayList<>();

        int curr = start;
        solution.order.add(curr);
        notVisited.remove(Integer.valueOf(curr));
        int toCurrNearest;
        int currNearestIndex = 0;
        int distance;

        while (notVisited.size() > 0) {
            toCurrNearest = Integer.MAX_VALUE;

            for (int i = 0; i < notVisited.size(); i++) {
                distance = instance.edge_weight_matrix[curr-1][notVisited.get(i)-1];
                if(distance < toCurrNearest) {
                    toCurrNearest = distance;
                    currNearestIndex = notVisited.get(i);
                }
            }

            solution.order.add(currNearestIndex);
            notVisited.remove(Integer.valueOf(currNearestIndex));
            curr = currNearestIndex;

        }

        return solution;
    }


}


