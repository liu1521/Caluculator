import java.util.Stack;

public class Counter {

    private Stack<Character> op = new Stack<>();
    private Stack<String> num = new Stack<>();

    public double compute(String f) {
        return getAnswer(f, op);
    }

    private double getAnswer(String f, Stack<Character> op) {
        StringBuilder temp_num = new StringBuilder();
        for (int i = 0; i < f.length(); i++) {
            if (f.charAt(i) >= '0' && f.charAt(i) <= '9' || f.charAt(i) == '.' || (f.charAt(i) == '-' && temp_num.toString().equals(""))) {
                temp_num.append(f.charAt(i));
                if (i == f.length() - 1) {
                    num.push(temp_num.toString());
                    temp_num = new StringBuilder();
                }
            } else {
                if (!temp_num.toString().equals("")) {
                    num.push(temp_num.toString());
                    temp_num = new StringBuilder();
                }
                if (f.charAt(i) == '(') {
                    StringBuilder temp = new StringBuilder();
                    i++;
                    while (f.charAt(i) != ')') {
                        temp.append(f.charAt(i));
                        i++;
                    }
                    Stack<Character> op1 = new Stack<Character>();
                    num.push(String.valueOf(getAnswer(temp.toString(), op1)));
                } else {
                    if (op.empty()) {
                        op.push(f.charAt(i));
                    } else {
                        if (rpiority(f.charAt(i)) <= rpiority(op.peek())) {
                            while (!op.empty() && rpiority(f.charAt(i)) <= rpiority(op.peek())) {
                                clear_op_stack_top(op);
                            }
                        }
                        op.push(f.charAt(i));
                    }
                }
            }
        }
        while (!op.empty())
            clear_op_stack_top(op);
        return Double.parseDouble(num.pop());
    }

    private void clear_op_stack_top(Stack<Character> op) {
        double right = Double.parseDouble(num.pop());
        double left = Double.parseDouble(num.pop());
        num.push(String.valueOf(computation(left, right, op.pop())));
    }

    private int rpiority(char op) {
        switch (op) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private double computation(double left, double right, char op) {
        switch (op) {
            case '+':
                return (left + right);
            case '-':
                return (left - right);
            case '*':
                return (left * right);
            case '/':
                return (left / right);
        }
        return 0;
    }
}