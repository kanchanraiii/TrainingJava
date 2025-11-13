import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileReaderFunc {
    public static void main(String[] args) throws Exception {

        // functional programming approach
    	long count = Files.lines(Paths.get("/home/dreamdust/Code/Java-Chubb/FileReadingIndia-Assignment/src/file.txt"))   // read file as stream of lines
                .map(String::toLowerCase)                 // convert each line to lowercase
                .flatMap(line -> Arrays.stream(line.split("\\W+"))) // split into words
                .filter(word -> word.equals("india"))     // keep only "india"
                .count();                                 // count them

        System.out.println("Total: " + count);
    }
}
