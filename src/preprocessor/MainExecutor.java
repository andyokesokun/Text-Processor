package preprocessor;

public class MainExecutor {

    public static void main(String[] args) {

        FilterProcessor filterProcessor = new FilterProcessor("alice29.txt", "stopwords.txt");
        filterProcessor.Process();
        filterProcessor.GenerateFilteredWordsFrequencyCount();

    }

}