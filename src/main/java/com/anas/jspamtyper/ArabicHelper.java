package com.anas.jspamtyper;

public class ArabicHelper {
    public static boolean isArabic(char c) {
        return c >= 'ء' && c <= 'ي';
    }

    public static char convertToEnglish(char c) {
        return switch (c) {
            case 'ء' -> 'x';
            case 'ا' -> 'h';
            case 'ب' -> 'f';
            case 'ت' -> 'j';
            case 'ث' -> 'e';
            case 'ج' -> '[';
            case 'ح' -> 'p';
            case 'خ' -> 'o';
            case 'د' -> ']';
            case 'ذ' -> '`';
            case 'ر' -> 'v';
            case 'ز' -> '.';
            case 'س' -> 's';
            case 'ش' -> 'a';
            case 'ص' -> 'w';
            case 'ض' -> 'q';
            case 'ط' -> '\'';
            case 'ظ' -> '/';
            case 'ع' -> 'u';
            case 'غ' -> 'y';
            case 'ف' -> 't';
            case 'ق' -> 'r';
            case 'ك' -> ';';
            case 'ل' -> 'g';
            case 'م' -> 'l';
            case 'ن' -> 'k';
            case 'ه' -> 'i';
            case 'و' -> ',';
            case 'ي' -> 'd';
            case 'ى' -> 'n';
            case 'ؤ' -> 'c';
            case 'ئ' -> 'z';
            case 'ة' -> 'm';
            case '؟' -> '?';
            case 'إ' -> 'Y';
            case 'أ' -> 'H';
            case 'آ' -> 'N';
            case '،' -> 'K';
            case 'ْ' -> 'X';
            case 'ُ' -> 'E';
            case 'ِ' -> 'A';
            case 'ّ' -> '~';
            case '~' -> 'Z';
            case 'ٌ' -> 'R';
            case 'ٍ' -> 'S';
            default -> c;
        };
    }
}
