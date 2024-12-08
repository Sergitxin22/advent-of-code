import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class script {
    public static void loadData(ArrayList<Integer> leftArray, ArrayList<Integer> rightArray) {
        File f = new File("data.txt");
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split("   ");
                leftArray.add(Integer.parseInt(data[0]));
                rightArray.add(Integer.parseInt(data[1]));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        leftArray.sort((n1, n2) -> n1 - n2);
        rightArray.sort((n1, n2) -> n1 - n2);

    }

    // --- Part One ---
    public static int calculateTotalDistance(ArrayList<Integer> leftArray, ArrayList<Integer> rightArray) {
        int totalDistance = 0;

        for (int i = 0; i < leftArray.size(); i++) {
            // System.out.println("########### " + (i + 1) + " ##########");
            int distance = Math.abs(leftArray.get(i) - rightArray.get(i));
            // System.out.println(distance);
            totalDistance += distance;
        }

        return totalDistance;
    }

    // --- Part Two ---
    public static int calculate_similarity(ArrayList<Integer> leftArray, ArrayList<Integer> rightArray) {
        int similarityScore = 0;
        for (int i = 0; i < leftArray.size(); i++) {
            int searchedValue = leftArray.get(i);
            int counterAppearances = 0;

            for (Integer number : rightArray) {
                if (searchedValue == number)
                    counterAppearances++;
            }

            similarityScore += (counterAppearances * searchedValue);
            // System.out.println("Value " + searchedValue + " -> appears " +
            // counterAppearances + " times");
        }

        return similarityScore;
    }

    public static void main(String[] args) {
        ArrayList<Integer> leftArray = new ArrayList<>();
        ArrayList<Integer> rightArray = new ArrayList<>();
        loadData(leftArray, rightArray);

        System.out.println("Total distance between lists: " + calculateTotalDistance(leftArray, rightArray));
        System.out.println("Similarity score: " + calculate_similarity(leftArray, rightArray));
    }
}