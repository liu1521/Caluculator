package Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("该计算器只能进行+-*/计算，而且不能加入（），以 “=”结束。");
        System.out.println("请输入您要计算的表达式：");
        Scanner in = new Scanner(System.in);
        Manage manage = new Manage();
        String s = in.nextLine();   //字符串接收输入的表达式
        String str = manage.Check(s);
        double result = manage.Ergo(str);
        System.out.println(result);
    }
}
