package com.epi;

import java.util.*;

// These numbers have very interesting property, and people called it ugly
// numbers. It is also called Quadratic integer rings.
public class GeneratingABSqrt2 {
    // @include
    public static class ABSqrt2 implements Comparable<ABSqrt2> {
        public int a, b;
        public double val;

        public ABSqrt2(int a, int b) {
            this.a = a;
            this.b = b;
            val = a + b * Math.sqrt(2);
        }

        // clang-format off
        @Override
        public int compareTo(ABSqrt2 o) {
            return Double.compare(val, o.val);
        }
        // clang-format on
    }

    public static List<ABSqrt2> generateFirstKABSqrt2(int k) {
        SortedSet<ABSqrt2> candidates = new TreeSet<>();
        // Initial for 0 + 0 * sqrt(2).
        candidates.add(new ABSqrt2(0, 0));

        List<ABSqrt2> result = new ArrayList<>();
        while (result.size() < k) {
            ABSqrt2 nextSmallest = candidates.first();
            result.add(nextSmallest);

            // Add the next two numbers derived from nextSmallest.
            candidates.add(new ABSqrt2(nextSmallest.a + 1, nextSmallest.b));
            candidates.add(new ABSqrt2(nextSmallest.a, nextSmallest.b + 1));
            candidates.remove(nextSmallest);
        }
        return result;
    }
    // @exclude

    private static void simpleTest(List<ABSqrt2> ans) {
        assert (0.0 == ans.get(0).val);
        assert (1.0 == ans.get(1).val);
        assert (Math.sqrt(2.0) == ans.get(2).val);
        assert (2.0 == ans.get(3).val);
        assert (1.0 + Math.sqrt(2.0) == ans.get(4).val);
        assert (2.0 * Math.sqrt(2.0) == ans.get(5).val);
        assert (3.0 == ans.get(6).val);
        assert (2.0 + Math.sqrt(2.0) == ans.get(7).val);
    }

    private static void check(List<ABSqrt2> ans, int k) {
        assert ans.size() == k;
        for (int i = 1; i < ans.size(); ++i) {
            assert ans.get(i).val >= ans.get(i - 1).val;
        }
    }

    public static void main(String[] args) {
        simpleTest(generateFirstKABSqrt2(8));
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int k;
            if (args.length == 1) {
                k = Integer.parseInt(args[0]);
            } else {
                k = r.nextInt(10000) + 1;
            }
            List<ABSqrt2> ans1 = generateFirstKABSqrt2(k);
            check(ans1, k);
            List<ABSqrt2> ans2 = GeneratingABSqrt2Improved.generateFirstKABSqrt2(k);
            check(ans2, k);
            for (int i = 0; i < k; ++i) {
                assert ans1.get(i).val == ans2.get(i).val;
            }
        }
    }
}
