package apac;

/**
 * Created by FengSi on 2016/08/26 at 19:37.
 */
public class IRR {
    public static double irr(double[] income) {
        return irr(income, 0.1d);
    }

    public static double irr(double[] values, double guess) {
        int maxIterationCount = 20;
        double absoluteAccuracy = 1E-7;

        double x0 = guess;
        double x1;

        int i = 0;
        while (i < maxIterationCount) {

            // the value of the function (NPV) and its derivate can be calculated in the same loop
            double fValue = 0;
            double fDerivative = 0;
            for (int k = 0; k < values.length; k++) {
                fValue += values[k] / Math.pow(1.0 + x0, k);
                fDerivative += -k * values[k] / Math.pow(1.0 + x0, k + 1);
            }

            // the essense of the Newton-Raphson Method
            x1 = x0 - fValue/fDerivative;

            if (Math.abs(x1 - x0) <= absoluteAccuracy) {
                return x1;
            }

            x0 = x1;
            ++i;
        }
        // maximum number of iterations is exceeded
        return Double.NaN;
    }

    public static double getIRR(final double[] cashFlows) {
        final int MAX_ITER = 20;
        double EXCEL_EPSILON = 0.0000001;

        double x = 0.1;
        int iter = 0;
        while (iter++ < MAX_ITER) {

            final double x1 = 1.0 + x;
            double fx = 0.0;
            double dfx = 0.0;
            for (int i = 0; i < cashFlows.length; i++) {
                final double v = cashFlows[i];
                final double x1_i = Math.pow(x1, i);
                fx += v / x1_i;
                final double x1_i1 = x1_i * x1;
                dfx += -i * v / x1_i1;
            }
            final double new_x = x - fx / dfx;
            final double epsilon = Math.abs(new_x - x);

            if (epsilon <= EXCEL_EPSILON) {
                if (x == 0.0 && Math.abs(new_x) <= EXCEL_EPSILON) {
                    return 0.0; // OpenOffice calc does this
                } else {
                    return new_x * 100;
                }
            }
            x = new_x;
        }
        return x;
    }
}
