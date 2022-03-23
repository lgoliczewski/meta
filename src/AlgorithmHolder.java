import javax.swing.plaf.synth.SynthToolBarUI;
import java.io.*;

public class AlgorithmHolder {

    Solution solution;
    Solution twoOptSolution;
    Solution candidate;
    Solution holder;
    int distance;

    public Solution KRandomAlgorithm(Instance instance, int k) throws IOException, ClassNotFoundException {
        int i = 0;
        solution = instance.getSolution();
        solution.randomOrder();
        Solution holder = null;
        byte[] byteData = new byte[0];

        while (i < k) {
            solution.randomOrder();
            if (i == 0) {
                distance = solution.totalDistance();
                holder = solution;
            } else if(solution.totalDistance() < distance) {
                distance = solution.totalDistance();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(solution);
                oos.flush();
                oos.close();
                bos.close();
                byteData = bos.toByteArray();
            }
            i++;
            //System.out.println("Aktualne: " + distance  + ", Wylosowane: " + solution.totalDistance());

        }

        solution.printPoints();
        solution.printMatrix();
        System.out.println(holder.order);
        System.out.println(solution.order);
        ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
        Solution finalSolution = (Solution) new ObjectInputStream(bais).readObject();
        return finalSolution;
    }

    public Solution TwoOptAlgorithm(Instance instance) throws IOException, ClassNotFoundException {

        holder = instance.getSolution();
        Solution copy;

        boolean isImproved = true;
        byte[] byteData;
        while (isImproved) {
            isImproved = false;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(holder);
            oos.flush();
            oos.close();
            bos.close();
            byteData = bos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
            holder = (Solution) new ObjectInputStream(bais).readObject();
            int i = 1;
            int j;
            while(i<=holder.size && !isImproved){
                j = 1;
                while(j<=holder.size && !isImproved){
                    ByteArrayInputStream bais2 = new ByteArrayInputStream(byteData);
                    candidate = (Solution) new ObjectInputStream(bais2).readObject();
                    candidate = invert(candidate,i,j);
                    if(candidate.totalDistance()<holder.totalDistance()){
                        holder = candidate;
                        isImproved = true;
                    }
                    System.out.println("i = " + i + ", j = " + j);
                    j++;
                }
                i++;
            }
        }

        return holder;
    }

    public Solution invert(Solution solution, int i, int j) throws IOException {
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
        return null;
    }

}


