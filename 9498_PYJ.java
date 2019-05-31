public class 9498_PYJ {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int score = sc.nextInt();
    sc.close();

    if(score >= 90) {
      System.out.println('A');
    } else if(score >= 80) {
      System.out.println('B');
    } else if(score >= 70) {
      System.out.println('C');
    } else if(socre >= 60) {
      System.out.println('D');
    } else {
      System.out.println('F');
    }
  }
} 
