class Calculator {
    private int a, b;

    public Calculator(int a, int b) {
        this.a = a;
        this.b = b;
    }
    public int add() {
        return a + b;
    }

    public int subtract() {
        return a - b;
    }

    public float divide() {
        return a / b;
    }

    public float multiply() {
        return a * b;
    }
}

class TrigonometricCalculator {
    private double a;

    public TrigonometricCalculator(double a) {
        this.a = a;
    }

    public double sine() {
        return Math.sin(a);
    }

    public double cosine() {
        return Math.cos(a);
    }

    public double tan() {
        return Math.tan(a);
    }

    public double inverseTan() {
        return Math.tanh(a);
    }
}

public class LCGE1 {
    public static void main(String[] args) {
        Calculator calc = new Calculator(2,5);
        TrigonometricCalculator tcalc = new TrigonometricCalculator(100);
        calc.add();
        calc.subtract();
        calc.multiply();
        calc.divide();
        tcalc.sine();
        tcalc.cosine();
        tcalc.tan();
        tcalc.inverseTan();
    }

}
