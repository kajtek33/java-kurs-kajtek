import java.util.Arrays;
import java.util.Scanner;

public class Homework3 {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);

        System.out.println("ILE ODCZYTÓW CHCESZ WPISAĆ?");
        int count = sc.nextInt();
        if (count <= 0){
            System.out.println("PODAJ LICZBĘ WIĘKSZĄ OD ZERA.");
            return;
        }
        int[] readings = new int[count];

        for (int i = 0; i < count; i++) {
            System.out.println("PODAJ ODCZYT NR. " + (i + 1));
            readings[i] = sc.nextInt();
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < readings.length ; i++) {
            sum = sum + readings[i];
            if (readings[i] < min) {
                min = readings[i];
            }
            if (readings[i] > max) {
                max = readings[i];
            }
        }
        double average = (double) sum / count;
        int bigger = 0;
        for (int reading : readings) {
            if (reading > average) {
                bigger++;
            }
        }
       int[] sortedReadings = Arrays.copyOf(readings, count);
        Arrays.sort(sortedReadings);
       double median;
       if (count % 2 == 1) {
           median = sortedReadings[count / 2];
       } else {
           median = (sortedReadings[count / 2] + sortedReadings[count / 2 - 1]) / 2.0;
       }
        System.out.println("DANE KOŃCOWE:");
        System.out.println("SUMA: " + sum);
        System.out.println("MINIMUM: " + min);
        System.out.println("MAXIMUM: " + max);
        System.out.printf("ŚREDNIA LICZB: %.2f%n", average);
        System.out.println("LICZBA ODCZYTÓW POWYŻEJ ŚREDNIEJ: " + bigger);
        System.out.println("ORYGINAŁ: " + Arrays.toString(readings) + " KOPIA: " + Arrays.toString(sortedReadings));
        System.out.println("MEDIANA: " + median);
    }
}
