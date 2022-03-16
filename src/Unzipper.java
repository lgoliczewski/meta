import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

public class Unzipper {

    public void unzip(String file) throws IOException{

        Path source = Paths.get("data/a280.tsp.gz");
        Path target = Paths.get("data/a280.tsp");

        if (Files.notExists(source)) {
            System.err.printf("The path %s doesn't exist!", source);
            return;
        }

        try (GZIPInputStream gis = new GZIPInputStream(new FileInputStream(source.toFile()));
             FileOutputStream fos = new FileOutputStream(target.toFile())){

                StringBuilder content = new StringBuilder();


             BufferedReader br = new BufferedReader(
                        new InputStreamReader(gis));

             String line;


                while ((line = br.readLine()) != null) {
                    // append string builder with line and with
                    // '/n' or '/r' or EOF
                    content.append(line
                            + System.lineSeparator());
                }

                // print string builder object i.e content
                System.out.println(content.toString());
                // copy GZIPInputStream to FileOutputStream
                byte[] buffer = new byte[1024];
                int len;
                while ((len = gis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

            //this.parse(target.toFile());
        } catch(Exception e){
            e.printStackTrace();
        }


    }

    /*public Instance parse(File file) {
        try {

            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder content = new StringBuilder();
            String line;


            while ((line = br.readLine()) != null) {
                // append string builder with line and with
                // '/n' or '/r' or EOF
                content.append(line
                        + System.lineSeparator());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }*/

}
