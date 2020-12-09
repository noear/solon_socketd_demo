package demo01.xcanal.base.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class TypeUtils {
    private static final SimpleDateFormat DATE_DEF_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public static Object change(String val, Class<?> targetType) {
        if (String.class == (targetType)) {
            return val;
        }

        if (val.length() == 0) {
            return null;
        }

        Object rst = tryTo(val, targetType);

        if (rst != null) {
            return rst;
        }

        if (Date.class == (targetType)) {
            try {
                return DATE_DEF_FORMAT.parse(val);
            } catch (RuntimeException ex) {
                throw ex;
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        }


        throw new ClassCastException("不支持类型:" + targetType.getName());
    }

    /**
     * 转换 string 值
     *
     * @param targetType 目标类型
     * @param val        值
     */
    public static Object tryTo(String val, Class<?> targetType) {
        if (Short.class == targetType || targetType == Short.TYPE) {
            return Short.parseShort(val);
        }

        if (Integer.class == targetType || targetType == Integer.TYPE) {
            return Integer.parseInt(val);
        }

        if (Long.class == targetType || targetType == Long.TYPE) {
            return Long.parseLong(val);
        }

        if (Double.class == targetType || targetType == Double.TYPE) {
            return Double.parseDouble(val);
        }

        if (Float.class == targetType || targetType == Float.TYPE) {
            return Float.parseFloat(val);
        }

        if (Boolean.class == targetType || targetType == Boolean.TYPE) {
            return Boolean.parseBoolean(val);
        }

        if (LocalDate.class == targetType) {
            //as "2007-12-03", not null
            return LocalDate.parse(val);
        }

        if (LocalTime.class == targetType) {
            //as "10:15:30", not null
            return LocalTime.parse(val);
        }

        if (LocalDateTime.class == targetType) {
            //as "2007-12-03T10:15:30", not null
            return LocalDateTime.parse(val);
        }

        if (BigDecimal.class == targetType) {
            return new BigDecimal(val);
        }

        if (BigInteger.class == targetType) {
            return new BigInteger(val);
        }

        return null;
    }
}
