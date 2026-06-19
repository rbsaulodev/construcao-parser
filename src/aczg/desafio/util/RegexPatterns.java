package aczg.desafio.util;

import java.util.regex.Pattern;

public final class RegexPatterns {

    private RegexPatterns() {

    }

    public static final String VOGAIS = "[aeiouáéíóúâêîôûãõàäëïöü]";
    public static final String SEMIVOGAL = "[iu]";
    public static final Pattern CEDILHA_OU_TILDE =
        Pattern.compile("\\b\\w*[çÇãÃõÕ]\\w*\\b", Pattern.UNICODE_CHARACTER_CLASS);

    public static final Pattern DITONGO_CRESCENTE =
        Pattern.compile(
            "\\b\\w*[iu][aeiouáéíóúâêîôûãõàäëïöü]\\w*\\b",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS
        );

    public static final Pattern DITONGO_DECRESCENTE =
        Pattern.compile(
            "\\b\\w*[aeiouáéíóúâêîôûãõàäëïöü][iu](?![aeiouáéíóúâêîôûãõàäëïöü])\\w*\\b",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS
        );

    public static final Pattern DITONGO =
        Pattern.compile(
            "\\b(?=\\S)" +
            "(?:[a-záéíóúâêîôûãõàçA-ZÁÉÍÓÚÂÊÎÔÛÃÕÀÇ])*" +
            "(?:" +
              "[aeiouáéíóúâêîôûãõàäëïöü][iu](?![aeiouáéíóúâêîôûãõàäëïöü])" +
              "|[iu][aeiouáéíóúâêîôûãõàäëïöü]" +
            ")" +
            "(?:[a-záéíóúâêîôûãõàçA-ZÁÉÍÓÚÂÊÎÔÛÃÕÀÇ])*\\b",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS
        );

    public static final Pattern TRITONGO =
        Pattern.compile(
            "\\b\\w*[iu][aeiouáéíóúâêîôûãõà][iu]\\w*\\b",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS
        );

    public static final Pattern HIATO =
        Pattern.compile(
            "\\b\\w*" +
            "(?:" +
              "[aeoáéóâêô][aeiouáéíóúâêîôûãõà]" +
              "|[aeiouáéíóúâêîôûãõà][áéíóúâêîôûãõ]" +
            ")" +
            "\\w*\\b",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS
        );

    public static final Pattern FRASE_QUATRO_PALAVRAS =
        Pattern.compile(
            "^[\\w\\-àáâãäåçèéêëìíîïòóôõöùúûüýÿÀÁÂÃÄÅÇÈÉÊËÌÍÎÏÒÓÔÕÖÙÚÛÜÝ]+" +
            "(?:\\s+[\\w\\-àáâãäåçèéêëìíîïòóôõöùúûüýÿÀÁÂÃÄÅÇÈÉÊËÌÍÎÏÒÓÔÕÖÙÚÛÜÝ]+){3}$",
            Pattern.UNICODE_CHARACTER_CLASS
        );

    public static final Pattern PROPAROXITONA =
        Pattern.compile(
            "\\b[a-záéíóúâêîôûãõàçA-ZÁÉÍÓÚÂÊÎÔÛÃÕÀÇ]*" +
            "[áéíóúâêîôûÁÉÍÓÚÂÊÎÔÛ]" +
            "[a-záéíóúâêîôûãõàçA-ZÁÉÍÓÚÂÊÎÔÛÃÕÀÇ]{2,}" +
            "[a-zA-ZàáâãäåçèéêëìíîïòóôõöùúûüýÿÀÁÂÃÄÅÇÈÉÊËÌÍÎÏÒÓÔÕÖÙÚÛÜÝ]\\b",
            Pattern.UNICODE_CHARACTER_CLASS
        );

    public static final Pattern PLURAL_ES =
        Pattern.compile("\\b(\\w+)es\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);

    public static final Pattern PLURAL_S =
        Pattern.compile("\\b(\\w+[aeiouáéíóúâêîôûãõà])s\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
}
