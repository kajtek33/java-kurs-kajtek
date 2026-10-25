public class Homework4 {
    public static void main(String[] args) {
        String[] cards = {"LC-123456", "LD-123456", "LC-1234", "LC-987654"};
        int[] amounts = {20000, 10000, 12000, 15000};
        for (int i = 0; i < cards.length; i++) {
            if (!hasValidFormat(cards[i])) {
                System.out.println("FORMAT ODRZUCONY");
                continue;
            }
            System.out.println("SUMA CYFR: " + digitSum(cards[i]));
            System.out.println("KWOTA: " + formatMoney(amounts[i]));
            int points2 = pointsFor(amounts[i]);
            System.out.println("PUNKTY: " + points2);
            int points = pointsFor(amounts[i]);
            System.out.println("PASEK: " + drawBar(points));
        }
        String chosenCard = cards[0];
        String digits = chosenCard.substring(3);
        int number = Integer.parseInt(digits);
        System.out.println("WYNIKI: " + "PĘTLA: " + digitSum(chosenCard) + ", REKURENCJA: " + digitSumRecursive(number));
    }
  public static boolean hasValidFormat(String card) {
     return card.length() == 9 && card.startsWith("LC-");
  }
  public static int digitSum(String card) {
     int sum = 0;
      for (int i = 3; i < card.length() ; i++) {
          char c = card.charAt(i);
          sum += c - '0';
      }
      return sum;
  }
  public static int digitSumRecursive(int number) {
        if (number < 10) {
            return number;
        }
        return (number % 10) + digitSumRecursive(number / 10);
  }
  public static int pointsFor(int cents) {
        return   cents / 10000;
  }
  public static String drawBar(int points) {
        String bar = "";
      for (int i = 0; i < points ; i++) {
       bar += "#";
      }
      return bar;
  }
  public static String formatMoney(int cents, String currency) {
    int wholePart = cents / 100;
    int fractionPart = cents % 100;
    String fractionText = "" + fractionPart;
    if (fractionPart < 10) {
        fractionText = "0" + fractionPart;
    }
    return wholePart + "," + fractionText + " " + currency;
  }
  public static String formatMoney(int cents) {
        return formatMoney(cents, "zł");
  }
}
