import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class script {
    public static void loadData(ArrayList<ArrayList<Integer>> numberLists) {
        File f = new File("data.txt");
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(" ");
                ArrayList<Integer> lineNumbers = new ArrayList<>();
                for (String number : data) {
                    lineNumbers.add(Integer.parseInt(number.strip()));
                }

                numberLists.add(lineNumbers);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    // --- Part One ---
    public static int safeReports(ArrayList<ArrayList<Integer>> numberLists) {
        int count = 0;

        for (ArrayList<Integer> numberList : numberLists) {
            if (isSafeReport(numberList)) {
                count++;
            }
        }

        return count;
    }

    public static boolean isSafeReport(ArrayList<Integer> numberList) {
        boolean isGrowing = false;
        int firstNumber = numberList.get(0);
        int secondNumber = numberList.get(1);

        if (firstNumber - secondNumber < 0) {
            isGrowing = true;
        }

        for (int i = 0; i < numberList.size() - 1; i++) {
            int currentNumber = numberList.get(i);
            int nextNumber = numberList.get(i + 1);

            boolean currentNumbersAreGrowing = false;

            if ((currentNumber - nextNumber) < 0) {
                currentNumbersAreGrowing = true;
            }

            if (currentNumbersAreGrowing != isGrowing)
                return false;

            int difference = Math.abs(currentNumber - nextNumber);

            if (difference < 1 || difference > 3)
                return false;
        }

        return true;
    }

    // --- Part Two ---
    public static int safeReportsWithTolerance(ArrayList<ArrayList<Integer>> numberLists) {
        int count = 0;
        for (ArrayList<Integer> numberList : numberLists) {
            if (isSafeReportWithTolerance(numberList)) {
                count++;
            }
        }

        return count;
    }

    public static boolean isSafeReportWithTolerance(ArrayList<Integer> numberList) {
        if (isSafeReport(numberList)) {
            return true;
        }

        for (int i = 0; i < numberList.size(); i++) {
            ArrayList<Integer> sublist = new ArrayList<>();

            sublist.addAll(numberList.subList(0, i));
            sublist.addAll(numberList.subList(i + 1, numberList.size()));

            if (isSafeReport(sublist)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> numberLists = new ArrayList<>();
        loadData(numberLists);

        System.out.println("Safe reports: " + safeReports(numberLists)); // 369
        System.out.println("Safe reports with tolerance: " +
                safeReportsWithTolerance(numberLists)); // 428
    }
}