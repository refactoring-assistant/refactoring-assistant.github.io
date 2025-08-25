class SimpleCalculator {
    int a;
    int b;

    public SimpleCalculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int Add() {
        return a + b;
    }

    public int Subtract() {
        return a - b;
    }

    public int Multiply() {
        return a * b;
    }

    public int Divide() {
        return a / b;
    }
}

public class DUPCGE1 {
    public static void main(String[] args) {
        SimpleCalculator sc = new SimpleCalculator(10, 10);
        sc.Add();
        sc.Add();
    }
}
