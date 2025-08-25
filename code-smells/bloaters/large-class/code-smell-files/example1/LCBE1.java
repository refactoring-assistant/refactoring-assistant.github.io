class Calculator {
    private int a;

    public Calculator(int a) {
        this.a = a;
    }

    public int add(int b) {
        return a + b;
    }

    public int subtract(int b) {
        return a - b;
    }

    public float divide(int b) {
        return a / b;
    }

    public float multiply(int b) {
        return a * b;
    }

    public double sine() {
        return Math.sin(this.a);
    }

    public double cosine() {
        return Math.cos(this.a);
    }

    public double tan() {
        return Math.tan(this.a);
    }

    public double inverseTan() {
        return Math.tanh(this.a);
    }

}

public class LCBE1 {

    public static void main(String[] args) {
        Calculator calc = new Calculator(2);
        calc.add(5);
        calc.subtract(5);
        calc.multiply(2);
        calc.divide(5);
        calc.sine();
        calc.cosine();
        calc.tan();
        calc.inverseTan();
    }

}
