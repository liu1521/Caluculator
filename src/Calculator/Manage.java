package Calculator;

import java.util.Stack;

public class Manage {
    public String Check(String str) //剔除符号,字母
    {
        StringBuffer expstr = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (i < str.length() && ch >= '(' && ch <= '9' || ch == '=')
                expstr.append(ch);
        }
        return expstr.toString();
    }

    public int lp(char op)//左操作符优先级
    {
        switch (op) {
            case '+': return 2;
            case '-': return 2;
            case '*': return 4;
            case '/': return 4;
            default: return -1;
        }
    }

    public int rp(char op)//右操作符优先级
    {
        switch (op) {
            case '+': return 3;
            case '-': return 3;
            case '*': return 5;
            case '/': return 5;
            default: return -1;
        }
    }

    public double Ergo(String str)
    {
        Stack<Double> Num = new Stack<>();//操作数栈,double型;
        Stack<Character> Op = new Stack<>();//操作符栈,char型;
        Stack<Character> Tem = new Stack<>();//临时栈,char型
        Stack<Character> tem = new Stack<>();//两个临时栈，转化为数字用
        StringBuffer numstr = new StringBuffer();
        int i = 0 ;
        while (str.charAt(i) != '=') {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9' || str.charAt(i) == '.' && !Tem.isEmpty()) {//指向数字字符
                Tem.push(str.charAt(i));//str顺序入临时栈
                i++;
            } else if (Op.isEmpty() || lp(Op.peek()) < rp(str.charAt(i))) {
                while (!Tem.empty()) {
                    tem.push(Tem.pop());
                }
                while (!tem.empty()) {
                    numstr.append(tem.pop());
                }
                Num.push(Double.parseDouble(numstr.toString()));
                numstr = new StringBuffer(); //转换为浮点数并重置numstr

                Op.push(str.charAt(i));
                i++;
            } else if (lp(Op.peek()) > rp(str.charAt(i))) {
                char op = Op.pop();

                while (!Tem.empty()) {
                    tem.push(Tem.pop());
                }
                while (!tem.empty()) {
                    numstr.append(tem.pop());
                }
                Num.push(Double.parseDouble(numstr.toString()));
                numstr = new StringBuffer();   //转换为浮点数并重置numstr

                double y = Num.pop();
                double x = Num.pop();
                double r = Value(x, y, op);
                Num.push(r);
                while (lp(Op.pop()) > rp(str.charAt(i)) || !Op.isEmpty()) {
                    char op2 = Op.pop();
                    double y2 = Num.pop();
                    double x2 = Num.pop();
                    double r2 = Value(x2, y2, op2);
                    Num.push(r2);
                }
                i++;
            } else i++;
        }
        while (!Tem.empty()) {
            tem.push(Tem.pop());
        }
        while (!tem.empty()) {
            numstr.append(tem.pop());
        }
        Num.push(Double.parseDouble(numstr.toString()));
        numstr = new StringBuffer();//转换为浮点数并重置numstr

        while (!Op.isEmpty()) {
            char op = Op.pop();
            double y = Num.pop();
            double x = Num.pop();
            double r = Value(x, y, op);
            Num.push(r);
        }
        return Num.pop();
    }

    public double Value(double x, double y, char op) {
        double result = 0;
        switch (op) {
            case '+':
                result = x + y;
                break;
            case '-':
                result = x - y;
                break;
            case '*':
                result = x * y;
                break;
            case '/':
                try {
                    result = x / y;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        return result;
    }
}
