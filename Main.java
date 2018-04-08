import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        System.out.println("请输入:...");
        Scanner sc = new Scanner(System.in);
        String formula = sc.next();
        double answer = counter.compute(formula);
        System.out.println("计算结果为:" + answer);
    }
}
