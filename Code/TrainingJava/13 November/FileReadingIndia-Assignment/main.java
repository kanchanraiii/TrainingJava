import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        String path = "/home/dreamdust/Code/Java-Chubb/FileReadingIndia-Assignment/src/file.txt";
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                
                line = line.toLowerCase();

               
                String[] words = line.split("\\W+");  

                
                for (String w : words) {
                    if (w.equals("india")) {
                        count++;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Total occurrences of 'india': " + count);
    }
}
