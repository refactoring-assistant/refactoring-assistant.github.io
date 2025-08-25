class DistributionCalculator {
    public double calculatePDF(double x, double mu, double sigma) {
        double exponent = -0.5 * Math.pow((x - mu) / sigma, 2);
        double normalizationConstant = 1.0 / (sigma * Math.sqrt(2 * Math.PI));
        return normalizationConstant * Math.exp(exponent);
    }

    public double calculateNormalDistributionPDF(double x) {
        double exponent = -0.5 * x * x;
        double normalizationConstant = 1.0 / (Math.sqrt(2 * Math.PI));
        return normalizationConstant * Math.exp(exponent);
    }
}

public class ILCBE1 {
    public static void main(String[] args) {
        DistributionCalculator dcalc = new DistributionCalculator();
        System.out.println(dcalc.calculatePDF(10, 4, 6));
        System.out.println(dcalc.calculateNormalDistributionPDF(10));
    }
}
