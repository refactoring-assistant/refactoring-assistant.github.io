class PolynomialSolver {
    private int a;
    private int b;
    private int c;
    private int d;

    public PolynomialSolver(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public PolynomialSolver(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public PolynomialSolver(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public void solveLinear() {
        if (this.a == 0) {
            if (this.b == 0) {
                System.out.println("Infinite Solutions");
            } else {
                System.out.println("No solution");
            }
        } else {
            System.out.println(-this.b / this.a);
        }
    }

    public void solveQuadratic() {
        // check if c exists

        if (this.a == 0) {
            solveLinear();
        }

        double discriminant = Math.pow(this.b, 2) - 4 * this.a * this.c;

        if (discriminant > 0) {
            double x1 = (-this.b + Math.sqrt(discriminant) / (2 * this.a));
            double x2 = (-this.b - Math.sqrt(discriminant) / (2 * this.a));
            System.out.println("Solutions: " + x1 + ", " + x2);
        } else if (discriminant == 0) {
            double x = (-this.b) / (2 * this.a);
            System.out.println("One real solution: " + x);
        } else {
            double real = -this.b / (2 * this.a);
            double imaginary = Math.sqrt(Math.abs(discriminant)) / (2 * this.a);
            System.out.println("Complex solution: " + real + ", " + imaginary);
        }
    }

    public void solveCubic() {
        //check if c, d exists

        if (this.a == 0) {
            System.out.println("Coefficient 'a' is zero, solving as a quadratic equation:");
            solveQuadratic();
            return;
        }
        double B = this.b / this.a;
        double C = this.c / this.a;
        double D = this.d / this.a;
        double p = C - B * B / 3;
        double q = D - (B * C) / 3 + 2 * (B * B * B) / 27;
        double delta = (q / 2) * (q / 2) + (p / 3) * (p / 3) * (p / 3);
        if (delta >= 0) {
            double u = Math.cbrt(-q / 2 + Math.sqrt(delta));
            double v = Math.cbrt(-q / 2 - Math.sqrt(delta));
            double y1 = u + v;
            System.out.println("One real solution for cubic equation (from Cardano's method): x = " + (y1 - B / 3));
            System.out.println(
                    "Note: Finding the other two roots involves complex numbers or more intricate calculations.");
        } else {
            double phi = Math.acos((-q / 2) / Math.sqrt(-((p / 3) * (p / 3) * (p / 3))));
            double y1 = 2 * Math.sqrt(-p / 3) * Math.cos(phi / 3);
            double y2 = 2 * Math.sqrt(-p / 3) * Math.cos((phi + 2 * Math.PI) / 3);
            double y3 = 2 * Math.sqrt(-p / 3) * Math.cos((phi + 4 * Math.PI) / 3);
            System.out.println("Three real solutions for cubic equation (trigonometric method):");
            System.out.println("x1 = " + (y1 - B / 3));
            System.out.println("x2 = " + (y2 - B / 3));
            System.out.println("x3 = " + (y3 - B / 3));
        }
    }
}

public class TFBE1 {
    public static void main(String[] args) {
        PolynomialSolver ps = new PolynomialSolver(10, 12);
        ps.solveLinear();
        PolynomialSolver ps2 = new PolynomialSolver(10, 12, 14);
        ps2.solveQuadratic();
        PolynomialSolver ps3 = new PolynomialSolver(10, 11, 12, 13);
        ps3.solveCubic();
    }
}
