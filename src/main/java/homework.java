public class homework {
    public static void main(String[] args) {
        String rawShop     = "  kurs market  ";
        String rawCustomer = "  anna   nowak  ";
        String rawEmail    = " Anna.Nowak@Example.COM ";
        String rawCode1    = "  spoz-0599  ";
        String rawCode2    = "spoz-0429";
        String rawCode3    = "  kawa-3999";
        char   register    = 'A';
        int    receiptNumber = 0;
          int itemCount = 0;

          String name1 = "Chleb razowy";
          String name2 = "Mleko 3,2%";
          String name3 = "Kawa ziarnista";
          int quantity1 = 2;
          int quantity2 = 3;
          int quantity3 = 1;



        System.out.println(rawShop.toUpperCase().strip() + " · " + "kasa " + register + " · " + "paragon " +  ++receiptNumber);
        String[] words = rawCustomer.strip().split("\\s+");
        String firstName = words[0].substring(0, 1).toUpperCase() + words[0].substring(1).toLowerCase();
        String lastName = words[1].substring(0, 1).toUpperCase() + words[1].substring(1).toLowerCase();
        String customerName = firstName + " " + lastName;

        String code1 = rawCode1.strip().toUpperCase();
        int dashIndex1 = code1.indexOf('-');
        String number1 = code1.substring(dashIndex1 + 1);
        int price1 = Integer.parseInt(number1);
        String code2 = rawCode2.strip().toUpperCase();
        int dashIndex2 = code2.indexOf('-');
        String number2 = code2.substring(dashIndex2 + 1);
        int price2 = Integer.parseInt(number2);
        String code3 = rawCode3.strip().toUpperCase();
        int dashIndex3 = code3.indexOf('-');
        String number3 = code3.substring(dashIndex3 + 1);
        int price3 = Integer.parseInt(number3);


        char firstInitial = firstName.charAt(0);
        char lastInitial = lastName.charAt(0);
        String loyaltyCard = "" + firstInitial + lastInitial + "-" + number1;
        String email = rawEmail.strip().toLowerCase();
        int atIndex = email.indexOf('@');
        String start = email.substring(0, 2);
        String end = email.substring(atIndex);
        String maskedEmail = start + "***" + end;
        System.out.println(customerName + " · " + "karta " + loyaltyCard + " · " + maskedEmail);
        System.out.println();
        int value1 = price1 * quantity1;
        String priceText1 = String.format("%d,%02d", price1 / 100, price1 % 100);
        String valueText1 = String.format("%d,%02d", value1 /100, value1 % 100);
        itemCount++;
        System.out.printf("%-17s%-9s%4d x %7s =%10s zł%n", name1, code1, quantity1,priceText1, valueText1);
        int value2 = price2 * quantity2;
        String priceText2 = String.format("%d,%02d", price2 / 100, price2 % 100);
        String valueText2 = String.format("%d,%02d", value2 /100, value2 % 100);
        itemCount++;
        System.out.printf("%-17s%-9s%4d x %7s =%10s zł%n", name2, code2, quantity2,priceText2, valueText2);
        int value3 = price3 * quantity3;
        String priceText3 = String.format("%d,%02d", price3 / 100, price3 % 100);
        String valueText3 = String.format("%d,%02d", value3 /100, value3 % 100);
        itemCount++;
        System.out.printf("%-17s%-9s%4d x %7s =%10s zł%n", name3, code3, quantity3, priceText3, valueText3);

        int netto = value1 + value2 + value3;
        String nettoText = String.format("%d,%02d", netto / 100, netto % 100);
        System.out.printf("%-42s%10s zł%n", "RAZEM NETTO", nettoText);

        int vat = (int) Math.round(netto * 0.23);
        String vatText = String.format("%d,%02d", vat / 100, vat % 100);
        System.out.printf("%-42s%10s zł%n", "VAT 23%", vatText);

        int brutto = netto + vat;
        String bruttoText = String.format("%d,%02d", brutto / 100, brutto % 100);
        System.out.printf("%-42s%10s zł%n", "RAZEM BRUTTO", bruttoText);

        int discount = (int) Math.round(brutto * 0.07);
        String discountText = String.format("%d,%02d", discount / 100, discount % 100);
        System.out.printf("%-42s%10s zł%n", "RABAT 7%", discountText);

        int payment = brutto - discount;
        String paymentText = String.format("%d,%02d", payment / 100, payment % 100);
        System.out.printf("%-42s%10s zł%n", "DO ZAPŁATY", paymentText);

        int paidInCents = 10000;
        String paidInCentsText = String.format("%d,%02d", paidInCents / 100, paidInCents % 100);
        int rest = paidInCents - payment;
        String restText = String.format("%d,%02d", rest / 100, rest % 100);
        System.out.printf("%-42s%10s zł%n", "GOTÓWKA", paidInCentsText);
        System.out.printf("%-42s%10s zł%n", "RESZTA", restText);

        int coins = rest / 500;
        int coinsRest = rest % 500;
        System.out.println("monet 5 zł: " + coins + ", " + "drobne poza nimi: " + coinsRest +" gr");
        System.out.println();
        String shopName = rawShop.strip().toUpperCase();
        String footer = String.format("%s · %d pozycji · do zapłaty %s zł", shopName, itemCount, paymentText);
        System.out.println(footer);

        char next = (char) (register + 1);
        System.out.println("Dziękujemy," + " " + firstName + " · " + "następna kasa " + next);
    }
}
