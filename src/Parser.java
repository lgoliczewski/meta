
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.*;


public class Parser {

    public void setParameters(File file, Instance instance) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder content = new StringBuilder();
        String line = "";

        List<String> tokens;
        StringTokenizer tokenizer;
        int i = 0;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("NODE_COORD_SECTION") || line.startsWith("EDGE_WEIGHT_SECTION")) break;

            tokens = new ArrayList<>();
            tokenizer = new StringTokenizer(line, " ");
            while (tokenizer.hasMoreElements()) {
                tokens.add(tokenizer.nextToken());
            }

            if(tokens.get(0).equals("NAME:")){
                instance.setName(tokens.get(1));
            }
            else if(tokens.get(0).equals("TYPE:")){
                instance.setType(Instance.type_enum.valueOf(tokens.get(1)));
            }
            else if(tokens.get(0).startsWith("DIMENSION:")){
                instance.setDimension(Integer.parseInt(tokens.get(1)));
            }
            else if(tokens.get(0).equals("EDGE_WEIGHT_TYPE:")){
                instance.setEdge_weight_type(Instance.edge_weight_type_enum.valueOf(tokens.get(1)));
            }
            else if(tokens.get(0).equals("EDGE_WEIGHT_FORMAT:")){
                instance.setEdge_weight_format(Instance.edge_weight_format_enum.valueOf(tokens.get(1)));
            }
            else if(tokens.get(0).equals("DISPLAY_DATA_TYPE")){
                instance.setDisplay_data_type(Instance.display_data_type_enum.valueOf(tokens.get(1)));
            }
            i++;
        }

    }

    public void parseEUC_2D(File file, Instance instance) throws IOException {

        instance.node_coord_list = new ArrayList<>();

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder content = new StringBuilder();
        String line = br.readLine();

        List<String> tokens = null;
        StringTokenizer tokenizer;

        while (!(line.startsWith("NODE_COORD_SECTION"))) {
            line = br.readLine();
        }
        int i = 0;
        while(!(line = br.readLine()).equals("EOF")){
            tokens = new ArrayList<>();
            tokenizer = new StringTokenizer(line, " ");
            while (tokenizer.hasMoreElements()) {
                tokens.add(tokenizer.nextToken());
            }
            Point2D.Double point = new Point2D.Double();
            point.setLocation(Double.parseDouble(tokens.get(1)),Double.parseDouble(tokens.get(2)));
            instance.node_coord_list.add(i,point);
            i++;
        }
        EUC2D_toMatrix(instance);



    }

    public void EUC2D_toMatrix(Instance instance){

        int a = 0;
        int b = 0;
        int matrix[][] = new int[instance.node_coord_list.size()][instance.node_coord_list.size()];
        while(a<instance.node_coord_list.size()){
            b = 0;
            while(b<instance.node_coord_list.size()){
                matrix[a][b] = instance.getDistance(a,b);
                b++;
            }
            a++;
        }
        instance.edge_weight_matrix = matrix;

    }

    public void parseFULL_MATRIX(File file, Instance instance) throws IOException {

        instance.edge_weight_matrix = new int[instance.getDimension()][instance.getDimension()];

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder content = new StringBuilder();
        String line = br.readLine();
        List<String> tokens = null;
        StringTokenizer tokenizer;

        while (!(line.startsWith("EDGE_WEIGHT_SECTION"))) {
            line = br.readLine();
        }
        int i = 0;
        while(!(line = br.readLine()).equals("EOF")){
            tokens = new ArrayList<>();
            tokenizer = new StringTokenizer(line, " ");
            while (tokenizer.hasMoreElements()) {
                tokens.add(tokenizer.nextToken());
            }
            for (int j = 0; j < instance.getDimension(); j++) {
                instance.edge_weight_matrix[i][j] = Integer.parseInt(tokens.get(j));
            }
            i++;
        }
        //instance.printMatrix(i-1);
    }
}
