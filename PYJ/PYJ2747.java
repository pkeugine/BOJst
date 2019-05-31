import java.util.Scanner;

public class PYJ2747 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int count = sc.nextInt();
    int ffibo = 0;
    int sfibo = 1;
    for(int i=0; i<count; i++) {
      sfibo += ffibo;
      ffibo = sfibo - ffibo;
    }
    System.out.println(ffibo);
  }
}
