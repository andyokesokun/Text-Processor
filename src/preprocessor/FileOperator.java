package preprocessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileOperator {

    private static final String BASE_INPUT_PATH = "input";
    private static final String BASE_OUTPUT_PATH = "output";
    private static final String DIRECTORY_SEPERATOR = "/";
    private List<String> Lines;

    public FileOperator() {
        Lines = new ArrayList<>();
    }

    public List<String> readFile(String fileName) {

        try (BufferedReader reader = new BufferedReader(
                new FileReader(BASE_INPUT_PATH + DIRECTORY_SEPERATOR + fileName))) {

            String line = "";
            while ((line = reader.readLine()) != null) {
                Lines.add(line);
            }

        } catch (Exception e) {
            System.out.println("Could not read file");
        }

        return Lines;

    }

    public void writeFile(String fileName, List<String> lines) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(BASE_OUTPUT_PATH + DIRECTORY_SEPERATOR + fileName))) {

            for (String line : lines) {
                writer.append(line + "\n");
            }

        } catch (Exception e) {
            System.out.println("Could not read file");
        }
    }

}
