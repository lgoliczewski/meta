import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Parser {
    public Instance parse(File file) {

        Instance instance = new Instance();
        try {

            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder content = new StringBuilder();
            String line;


            /*while ((line = br.readLine()) != null) {
                // append string builder with line and with
                // '/n' or '/r' or EOF
                content.append(line
                        + System.lineSeparator());
            }*/
            List<String> tokens;
            StringTokenizer tokenizer;
            while ((line = br.readLine()) != null) {
                tokens = new ArrayList<>();
                tokenizer = new StringTokenizer(line, " ");
                while (tokenizer.hasMoreElements()) {
                    tokens.add(tokenizer.nextToken());
                }

                switch (tokens.get(0)) {
                    case "NAME:":
                        instance.setName(tokens.get(1));
                    case "TYPE:":
                        instance.setType(Instance.type_enum.valueOf(tokens.get(1)));
                    case "DIMENSION:":
                        instance.setDimension(Integer.parseInt(tokens.get(1)));
                    case "EDGE_WEIGHT_TYPE:":
                        instance.setEdge_weight_type(Instance.edge_weight_type_enum.valueOf(tokens.get(1)));
                    case "EDGE_WEIGHT_FORMAT:":
                        instance.setEdge_weight_format(Instance.edge_weight_format_enum.valueOf(tokens.get(1)));
                    case "DISPLAY_DATA_TYPE:":
                        instance.setDisplay_data_type(Instance.display_data_type_enum.valueOf(tokens.get(1)));
                }
            }


        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }
}
