import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class FileReader {

    // TODO: 15.02.2025 Необходимо использовать как можно более высокий уровень абстрации
    //List, Set, Map, а не ArrayList<>, HashSet<>, HashMap<>
    // TODO: 15.02.2025 Исправить везде
    ArrayList<String> readFileContests(String fileName) {
        String path = "./resources/" + fileName;
        try {
            return new ArrayList<>(Files.readAllLines(Path.of(path)));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл. Возможно файл отсутствует");
            return new ArrayList<>();
        }
    }
}
