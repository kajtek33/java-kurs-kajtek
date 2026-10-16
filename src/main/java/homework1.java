import java.util.Scanner;

public class homework1 {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
        System.out.println("Witamy w liniach lotniczych, proszę podać dane:");
        System.out.print("NUMER LOTU: ");
        String lotNumber = sc.nextLine().toUpperCase().replace(" ", "");
        System.out.print("PODAJ NAZWISKO: ");
        String lastName = sc.nextLine().toUpperCase();
        System.out.print("PODAJ KLASE: ");
        String planeClass = sc.nextLine().toUpperCase();
        System.out.print("PODAJ WAGĘ BAGAŻU: ");
        String weight = sc.nextLine();
        System.out.print("PODAJ WIEK: ");
        String age = sc.nextLine();

        if (lotNumber.isBlank()) {
            System.out.println("ODRZUCONO - BRAK NUMERU LOTU: '" + lotNumber + "'");
            return;
        }
        if (lastName.isBlank()) {
            System.out.println("ODRZUCONO - BRAK NAZWISKA: '" + lastName + "'");
            return;
        }
        if (lotNumber.length() < 4 || lotNumber.length() > 6) {
            System.out.println("ODRZUCONO - ZŁA DŁUGOŚĆ NUMERU LOTU: '" + lotNumber + "'");
            return;
        }
        if (weight.indexOf(',') == -1) {
            System.out.println("ODRZUCONO - BRAK PRZECINKA W WADZE: '" + weight + "'");
            return;
        }
        int position = weight.indexOf(',');
        if (weight.length() - position - 1 !=2) {
            System.out.println("ODRZUCONO - ZŁA WAGA: '" + weight + "'");
            return;
        }
        String[] parts = weight.split(",");
        int kilograms = Integer.parseInt(parts[0]);
        int hundredths = Integer.parseInt(parts[1]);
        int weightGrams = kilograms * 1000 + hundredths * 10;
        if (weightGrams < 0 || weightGrams > 60000) {
            System.out.println("ODRZUCONO - WAGA POZA ZAKRESEM 0-60KG: '" + weight + "'");
            return;
        }

        int limitKg = switch (planeClass) {
            case "ECONOMY" -> 23;
            case "PREMIUM" -> 28;
            case "BUSINESS" -> 32;
            case "FIRST" -> 40;
            default -> {
                System.out.println("UWAGA: NIEZNANA KLASA: " + planeClass + ", PRZYJMUJĘ LIMIT ECONOMY");
                yield 23;
            }
        };
        int limitGrams = limitKg * 1000;
        int overWeight = weightGrams - limitGrams;

        int fee = 0;
        String cargoNote = "";

        if (overWeight <= 0) {
           fee = 0;
        } else if (overWeight <= 5000) {
            fee = 50;
        } else if (overWeight <= 15000) {
            fee = 150;
        } else {
            fee = 300;
            cargoNote = "BAGAŻ DO NADANIA JAKO CARGO";
        }

        String boarding = planeClass.equals("BUSINESS") || planeClass.equals("FIRST") ? "PRIORITY" : "NORMALNE";

        int ageNumber = Integer.parseInt(age);
        String guardianNote = ageNumber < 16 ? "PASAŻER MAŁOLETNI — WYMAGANA OPIEKA" : "";

        System.out.printf("%-20s%s%n", "LOT", lotNumber);
        System.out.printf("%-20s%s%n", "PASAŻER", lastName);
        System.out.printf("%-20s%s%n", "KLASA", planeClass);

        int kg = weightGrams / 1000;
        int weightHundredths = (weightGrams % 1000) / 10;
        System.out.printf("%-20s%d,%02d kg%n", "WAGA", kg, weightHundredths);

        int limitWhole = limitGrams / 1000;
        int limitHundredths = (limitGrams % 1000) / 10;
        System.out.printf("%-20s%d,%02d kg%n", "LIMIT", limitWhole, limitHundredths);

        int shownOverweight = Math.max(0, overWeight);
        int overWhole = shownOverweight / 1000;
        int overHundredths = (shownOverweight % 1000) / 10;
        System.out.printf("%-20s%d,%02d kg%n", "NADWAGA", overWhole, overHundredths);

        System.out.printf("%-20s%d zł%n", "OPŁATA", fee);
        System.out.printf("%-20s%s%n", "BOARDING", boarding);

        if (!cargoNote.isEmpty()) {
            System.out.println(cargoNote);
        }
        if (!guardianNote.isEmpty()) {
            System.out.println(guardianNote);
        }
        System.out.println("DZIĘKUJEMY ZA KORZYSTANIE Z NASZYCH LINII LOTNICZYCH - MIŁEGO LOTU :)");
    }
}

