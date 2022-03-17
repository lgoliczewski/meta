
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        /*Unzipper unzipper = new Unzipper();
        unzipper.unzip(Path.of("data/ft70.atsp.gz"), Path.of("data/ft70.atsp"));
        unzipper.unzip(Path.of("data/ftv33.atsp.gz"), Path.of("data/ftv33.atsp"));
        */
        ToFileWriter tfw = new ToFileWriter();
        Parser parser = new Parser();
        Instance instance = new Instance();
        instance.generateRandomInstanceEUC_2D(10,40); //generowanie 10 losowych punktow o wspolrzednych z przedzialu [0,40]
        //Solution solution = instance.getSolution(); // tworzenie rozwiazania

        File file = new File("data/aaaaaaaa_1.tour");
        Solution solution = new Solution();
        parser.parseSolution(file, solution);

        solution.printOrder(); // poczatkowa kolejnosc punktow
        solution.randomOrder(); // permutacja
        solution.printOrder(); // koncowa wartosc punktow
        System.out.println("");
        solution.instance.printMatrix(10);
        System.out.println("\ntotal distance: " + solution.totalDistance()+ "\n");

        solution.instance.setName("abc");
        tfw.saveToFile(solution);


        //instance.setName("aaaaaaaa");
        //tfw.saveToFile(solution);

        //File file = new File("data/ch150.tsp");
        //Instance i = new Instance();
        //parser.setParameters(file,i);
        //i.printMatrix(10);

        //File file1 = new File("data/hk48.tsp");
        //Instance i1 = new Instance();
        //parser.setParameters(file1,i1);
        //i1.printMatrix(10);

        //i1.setName("test1");
        //ToFileWriter tfw = new ToFileWriter();
        //tfw.saveToFile(i1);

        //File file2 = new File("data/st70.opt.tour");
        //Solution s = new Solution();
        //parser.parseSolution(file2, s);
        //System.out.println("dist " + s.totalDistance());

        //ToFileWriter tfw1 = new ToFileWriter();
        //tfw1.saveToFile(s);

    }
}
