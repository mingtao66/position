package net.hzbox.vj.journal.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 创建时间：2015年11月19日下午4:12:28
 * @ClassName: StringUtil
 * @Description:String类型帮助类
 */
public class StringUtil {


    private static boolean isNotEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
            || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }


    /**
     * 删除逗号
     *
     * @param s
     * @return
     */
    public static String removeComma(String s) {
        if (s == null || s.equals("")) {
            return s;
        }
        if (s.startsWith(",")) {
            s = s.substring(1, s.length());
        }

        if (s.endsWith(",")) {
            s = s.substring(0, s.length() - 1);
        }

        s = s.replaceAll(",,", ",");
        return s;
    }

    /**
     * string按,转换为list
     *
     * @param s
     * @return
     */
    public static List<String> stringToArray(String s) {
        List<String> list = new ArrayList<String>();
        s = removeComma(s);

        if (s == null || s.equals("")) {
            return null;
        }
        String[] s_array = s.split(",");
        list.addAll(Arrays.asList(s_array));

        return list;
    }

    /**
     * String 转为数组,Integer类型
     *
     * @param s
     * @param length
     * @return
     */
    public static Integer[] stringToArray(String s, int length) {

        s = removeComma(s);
        if (s == null || s.equals("")) {
            return null;
        }
        String[] s_array = s.split(",");
        Integer[] ids = new Integer[s_array.length];
        for (int i = 0; i < s_array.length; i++) {
            ids[i] = Integer.parseInt(s_array[i]);
        }
        return ids;
    }

    /**
     * String转为list,Integer
     *
     * @param s
     * @return
     */
    public static List<Integer> stringToIntArray(String s) {
        List<Integer> list = new ArrayList<Integer>();
        s = removeComma(s);
        if (s == null || s.equals("")) {
            return null;
        }
        String[] s_array = s.split(",");
        for (String i : s_array) {
            list.add(Integer.parseInt(i));
        }
        return list;
    }

    /**
     * 去除提交文本中得特殊字符
     *
     * @param str
     * @return
     */
    public static String replaceScript(String str) {
        str = str.replaceAll("\\?|java|script|\\(|\\)|\\<|\\>|\"|\'|\\@|\\#|\\%|\\&|\\*|\\!|or|alert|\\:|\\;|", "");
        return str;
    }

    /**
     * 在数据库进行模糊查询时，对数据库通配符进行转义
     *
     * @param character 原始字符
     * @return 转义后的字符
     */
    public static String filterSpecialCharacter(String character) {
        character = character.replaceAll("%", "\\\\%");
        character = character.replaceAll("_", "\\\\_");
        return character;
    }

}
