package interview;

import java.math.BigInteger;

/**
 * Created by FengSi on 2017/05/26 at 19:12.
 */
public class JailBreak {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }

    private static int quickPowerMod(int a, int b) {
        long ans = 1;
        long aa = a % 100003;
        while(b>0) {
            if(b % 2 == 1)  ans = ((ans%100003) * (aa%100003)) % 100003;
            b /= 2;
            aa = ((aa%100003) * (aa%100003)) % 100003;
        }
        return (int) ans;
    }

    public static int solve(int m, int n) {
        int mod = 100003;
        long a = ((long)(m % mod)) * ((long)(quickPowerMod(m - 1, n - 1)));
        a %= mod;
        int c = (int) a;
        return (mod + quickPowerMod(m, n) - c) % mod;
    }

    public static int big(int m, int n) {
        BigInteger mb = new BigInteger(String.valueOf(m));
        BigInteger m1 = new BigInteger(String.valueOf(m - 1));
        BigInteger nb = new BigInteger(String.valueOf(n));
        BigInteger mod = new BigInteger("100003");
        BigInteger result = mb.pow(n).subtract(m1.pow(n-1).multiply(mb)).mod(mod);
        return Integer.parseInt(result.toString());
    }
}
