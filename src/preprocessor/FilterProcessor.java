package preprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FilterProcessor {

    private FileOperator fileOperator;
    private String fileName;
    private String fileNameStopWords;
    private List<String> filteredWords;
    private List<String> lines;

    public FilterProcessor(String fileName, String fileNameStopWords) {
        fileOperator = new FileOperator();
        this.fileName = fileName;
        this.fileNameStopWords = fileNameStopWords;
        filteredWords = new ArrayList<String>();

    }

    public void Process() {

        lines = fileOperator.readFile(fileName);

        Set<String> stopWords = fileOperator.readFile(fileNameStopWords)
                .stream().collect(Collectors.toSet());

        // add Puctuations
        stopWords.add(".");
        stopWords.add(",");
        stopWords.add("?");
        stopWords.add("`");
        stopWords.add(":");
        stopWords.add("'");
        stopWords.add("!");
        stopWords.add(";");
        stopWords.add("(");
        stopWords.add(")");
        stopWords.add("'");

        Pattern pattern = Pattern.compile("\\d");

        for (String line : lines) {
            StringTokenizer tokenizer = new StringTokenizer(line, "-.,:?`';!)( \" ");

            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();

                // Add word if not present in stopwords
                if (!stopWords.contains(word) && !pattern.matcher(word).matches()) {
                    filteredWords.add(word);
                }

            }

        }

        fileOperator.writeFile("filterWords.txt", filteredWords);

    }

    public void GenerateFilteredWordsFrequencyCount() {

        HashMap<String, Integer> wordCount = new HashMap<>();

        for (String word : filteredWords) {

            if (wordCount.containsKey(word)){
                wordCount.put(word, wordCount.get(word) + 1);
            }else{
                wordCount.put(word, 1);
            }   

        }

        List<String> wordFrequence = new ArrayList<>();

        wordCount.forEach((word, count) -> {

            wordFrequence.add(word + " - " + count);

        });

        fileOperator.writeFile("wordFrequence.txt", wordFrequence);

    }
}
