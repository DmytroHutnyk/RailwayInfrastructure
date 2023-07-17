import java.util.stream.Stream;
import java.io.File;
public class Streams {
    public static void main(String []args){
        Stream.of("Alice", "Bella", "Cecilia", "Dorothy")
                .peek(e -> System.out.println("peek: " + e +"; "))
                .map(s -> s + ".txt")
                .map(File::new)
                .limit(2)
                .forEach(f -> System.out.println(f + " exists? " + (f.exists()? "Yes" : "No")));

    }
}
