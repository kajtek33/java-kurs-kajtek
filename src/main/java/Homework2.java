import java.util.Scanner;

public class Homework2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
          int accepted = 0;
          int rejected = 0;
          int totalWeight = 0;
          int totalFee = 0;
          int maxWeight = 0;
          String maxCode = "";
          String prevZone = "";
          int currentStreak = 0;
          int bestStreak = 0;
          String bestZone = "";

        // wybralem petle while, poniewaz nie znam z gory liczby paczek,
        // a o koncu dowiaduje sie dopiero po wczytaniu kodu, wiec petla konczy sie po break
        // przy remisie wag, paczka ktora zostala wpisana pierwsza zostaje wybrana
        // odrzucone paczki pomijam i nie przerywam serii
        // pierwsze wczytanie kodu, licze jako probe nr. 1

           while (true) {
               System.out.print("PODAJ KOD PACZKI: ");
               String code = sc.nextLine();
               if (code.equalsIgnoreCase("KONIEC")) {
                   break;
               }
               int trials = 0;
               boolean ok = false;
               while (trials < 3 && !ok) {
                   trials++;
                   if ((code.length() >= 5) && code.contains("-")) {
                       ok = true;
                   } else if (trials < 3) {
                       System.out.println("ZŁY KOD PACZKI - SPRÓBUJ PONOWNIE");
                       System.out.print("PODAJ KOD PACZKI: ");
                       code = sc.nextLine();
                   }
               }
               if (!ok) {
                   rejected++;
                   System.out.println("PACZKA ODRZUCONA");
                   continue;
               }

               String weightText = "";
               int weight = 0;

               while (true) {
                   System.out.print("PODAJ WAGĘ W GRAMACH: ");
                   weightText = sc.nextLine();
                   boolean weightOK = false;
                   if (weightText.length() >= 1 && weightText.length() <= 5) {
                       boolean onlyDigits = true;
                       for (int i = 0; i < weightText.length(); i++) {
                           char c = weightText.charAt(i);
                           if (c < '0' || c > '9') {
                               onlyDigits = false;
                               break;
                           }
                       }
                       if (onlyDigits) {
                           weight = Integer.parseInt(weightText);
                           if (weight >= 1 && weight <= 30000) {
                               weightOK = true;
                           }
                       }
                   }
                   if (weightOK) {
                       break;
                   } else {
                       System.out.println("ZŁA WAGA - SPRÓBUJ PONOWNIE");
                   }
               }
               String zone = "";
               while (true) {
                   System.out.print("PODAJ STREFĘ (PL, EU, WORLD): ");
                   zone = sc.nextLine();
                   zone = zone.toUpperCase();
                   if (zone.equals("PL") || zone.equals("EU") || zone.equals("WORLD")) {
                       break;
                   } else {
                       System.out.println("ZŁA STREFA - SPRÓBUJ PONOWNIE");
                   }
               }
               int kilograms = (weight + 999) / 1000;
               int fee = switch (zone) {
                   case "PL" -> 1200 + 150 * kilograms;
                   case "EU" -> 2500 + 400 * kilograms;
                   case "WORLD" -> 4900 + 900 * kilograms;
                   default -> 0;
               };
               int zlotys = fee / 100;
               int pennies = fee % 100;
               System.out.printf("OPŁATA %d,%02d zł%n", zlotys, pennies);

               accepted++;
               totalWeight += weight;
               totalFee += fee;

               if (zone.equals(prevZone)) {
                   currentStreak++;
               } else {
                  currentStreak = 1;
                  prevZone = zone;
               }
               if (currentStreak > bestStreak) {
                   bestStreak = currentStreak;
                   bestZone = zone;
               }
               if (weight > maxWeight) {
                   maxWeight = weight;
                   maxCode = code;
               }
           }
        System.out.println("PODSUMOWANIE: ");
        System.out.println("PRZYJĘTE " + accepted);
        System.out.println("ODRZUCONE " + rejected);
        System.out.println("ŁĄCZNA WAGA " + totalWeight);

        int totalZlotys = totalFee / 100;
        int totalPennies = totalFee % 100;
        System.out.printf("ŁĄCZNY KOSZT: %d,%02d zł%n", totalZlotys, totalPennies);

        if (accepted > 0) {
            double average = (double) totalWeight / accepted;
            System.out.printf("ŚREDNIA WAGA: %.2f g%n", average);
            System.out.println("NAJCIĘŻSZA PACZKA - KOD: " + maxCode);
            System.out.println("NAJCIĘŻSZA PACZKA - WAGA: " + maxWeight + " g");
            System.out.println("NAJDŁUŻSZA SERIA: " + bestStreak + " x " + bestZone);
        } else {
            System.out.println("BRAK PRZYJĘTYCH PACZEK");
        }
    }
}
