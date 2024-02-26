import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PA2Search {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Error: Make sure to have 2 arguments.");
            return;
        }

        Scanner scnr = new Scanner(System.in);
        List<String> search_phrases = new ArrayList<>();
        int search_terms, i;
        String search_file;


        search_terms = Integer.parseInt(args[0]);
        search_file = args[1];
        for (i = 0; i < search_terms; i++) {
            if (scnr.hasNextLine()) {
                search_phrases.add(scnr.nextLine());
            }
        }
        scnr.close();

        System.out.println("Searching for terms: " + search_phrases);

        long start = System.currentTimeMillis();
        searcher(search_file, search_phrases);
        long elapsed = System.currentTimeMillis() - start;

        System.out.println("Elapsed time: " + elapsed + " Milliseconds");
    }

    private static void searcher(String search_file, List<String> search_phrases) {

        try (BufferedReader reader = new BufferedReader(new FileReader(search_file))) {
            String line;
            int line_number = 1;

            System.out.printf("%-10s %-10s %s%n", "Line", "Position", "Name");
            while ((line = reader.readLine()) != null) {
                for (String phrase : search_phrases) {
                    int index = line.indexOf(phrase);
                    while (index >= 0) {
                        System.out.printf("%-10d %-10d %s%n", line_number, index + 1, phrase);
                        index = line.indexOf(phrase, index + 1);
                    }
                }
                line_number++;
            }
        }
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    }
}