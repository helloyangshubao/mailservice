package com.payc.tool.utils;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.function.Function;

public class BigDecimalUtil {
    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        if (v1 == null) {
            v1 = BigDecimal.ZERO;
        }
        if (v2 == null) {
            v2 = BigDecimal.ZERO;
        }
        return v1.multiply(v2);
    }

    public static BigDecimal mul(BigDecimal v1, BigDecimal v2, int scale) {
        if (v1 == null) {
            v1 = BigDecimal.ZERO;
        }
        if (v2 == null) {
            v2 = BigDecimal.ZERO;
        }
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return round(v1.multiply(v2), scale);
    }


    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
        if (v1 == null) {
            v1 = BigDecimal.ZERO;
        }
        if (v2 == null) {
            v2 = BigDecimal.ZERO;
        }
        return v1.divide(v2, 8, 4);
    }

    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
        if (v1 == null) {
            v1 = BigDecimal.ZERO;
        }
        if (v2 == null) {
            v2 = BigDecimal.ZERO;
        }
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            return v1.divide(v2, scale, 4);
        }
    }


    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        if (v1 == null) {
            v1 = BigDecimal.ZERO;
        }
        if (v2 == null) {
            v2 = BigDecimal.ZERO;
        }
        return v1.add(v2);
    }

    public static BigDecimal add(BigDecimal v1, BigDecimal v2, int scale) {
        if (v1 == null) {
            v1 = BigDecimal.ZERO;
        }
        if (v2 == null) {
            v2 = BigDecimal.ZERO;
        }
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            return v1.add(v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
        }

    }

    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
        if (v1 == null) {
            v1 = BigDecimal.ZERO;
        }
        if (v2 == null) {
            v2 = BigDecimal.ZERO;
        }
        return v1.subtract(v2);
    }

    public static BigDecimal sub(BigDecimal v1, BigDecimal v2, int scale) {
        if (v1 == null) {
            v1 = BigDecimal.ZERO;
        }
        if (v2 == null) {
            v2 = BigDecimal.ZERO;
        }
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            return v1.subtract(v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
        }
    }

    public static BigDecimal round(BigDecimal v, int scale) {
        if (v != null) {
            if (scale < 0) {
                throw new IllegalArgumentException("The scale must be a positive integer or zero");
            } else {
                BigDecimal one = new BigDecimal(1);
                if (scale == 2) {
                    DecimalFormat countFormat = new DecimalFormat("0.00");
                    String value = countFormat.format(v);
                    return new BigDecimal(value);
                } else {
                    return v.divide(one, scale, 4);
                }
            }
        }
        return new BigDecimal(0);
    }

    public static BigDecimal abs(BigDecimal v) {
        if (null == v) {
            return BigDecimal.ZERO;
        }
        return v.abs();
    }

    public static <T> BigDecimal sum(Collection<T> items, Function<T, BigDecimal> numberExtractor) {
        return items.stream()
                .map(numberExtractor)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 根据金额、税率 计算税金（金额/(1+税率)*税率）
     *
     * @param v1 金额
     * @param v2 税率
     * @return 税金
     */
    public static BigDecimal calculateTax(BigDecimal v1, BigDecimal v2) {
        return mul(div(v1, add(v2, BigDecimal.ONE)), v2);
    }

    /**
     * 根据金额、税率 计算税金（金额/(1+税率)*税率）
     *
     * @param v1 金额
     * @param v2 税率
     * @return 税金
     */
    public static BigDecimal calculateTaxRound(BigDecimal v1, BigDecimal v2, int scale) {
        return mul(div(v1, add(v2, BigDecimal.ONE)), v2, scale);
    }

    /**
     * @param str
     * @return java.math.BigDecimal
     * 含%的字符串 转成 BigDecimal
     */
    public static BigDecimal convertFromPercentString(String str) {
        return new BigDecimal(
                str.substring(
                        0,
                        str.indexOf("%")
                )
        );
    }

    /**
     * @param bigDecimal
     * @return java.lang.String
     * BigDecimal 转成 含%的字符串
     */
    public static String convertFromBigDecimal(BigDecimal bigDecimal) {
        return String.valueOf(bigDecimal).concat("%");
    }

    public static boolean isEmpty(BigDecimal bigDecimal) {
        return (
                null == bigDecimal
                        || BigDecimal.ZERO.compareTo(bigDecimal) == 0
        );
    }

    public static boolean isNotEmpty(BigDecimal bigDecimal) {
        return (
                null != bigDecimal
                        && BigDecimal.ZERO.compareTo(bigDecimal) != 0
        );
    }
}
