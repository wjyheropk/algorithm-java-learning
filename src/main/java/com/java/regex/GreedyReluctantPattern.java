package com.java.regex;

/**
 * 正则表达式：贪婪、勉强模式
 *
 * @author wangjiayin
 * @since 2017/10/17
 */
public class GreedyReluctantPattern {

    public static void main(String[] args) {

        String test = "a<tr>aava </tr>abb ";
        // 默认是贪婪模式
        String reg = "<.+>";
        System.out.println(test.replaceAll(reg, "###"));
        // 勉强模式
        // Java是支持勉强模式的，勉强模式的表示方式是重复次数字符后面紧跟一个?，例如??就表示勉强模式的?，+?就表示勉强模式的+，*?就表示勉强模式的*，{ }?就表示勉强模式的{ }
        reg = "<.+?>";
        System.out.println(test.replaceAll(reg, "###"));

    }
}
