package com.blog.blog_project.validator;

import com.blog.blog_project.annotation.NoCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NoCodeValidator implements ConstraintValidator<NoCode, String> {

    // Kod kalıplarını tespit etmek için regex pattern'ları
    private static final Pattern[] CODE_PATTERNS = {
            // HTML/XML script etiketleri
            Pattern.compile("<script[^>]*>.*?</script>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL),

            // Markdown kod blokları (``` veya ~~~)
            Pattern.compile("```[\\s\\S]*?```|~~~[\\s\\S]*?~~~"),

            // Inline kod blokları (`)
            Pattern.compile("`[^`]+`"),

            // HTML kod etiketleri
            Pattern.compile("<code[^>]*>.*?</code>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL),
            Pattern.compile("<pre[^>]*>.*?</pre>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL),

            // Yaygın programlama dili anahtar kelimeleri ve syntax'ı
            Pattern.compile("\\b(function|class|import|export|const|let|var|def|public|private|protected)\\s*[({]"),

            // Noktalı virgül ve süslü parantez kombinasyonu (programlama syntax'ı)
            Pattern.compile("[;{}]\\s*\\n\\s*[;{}]"),

            // HTML/JavaScript event handler'ları
            Pattern.compile("on(click|load|error|change|submit|focus|blur)\\s*=", Pattern.CASE_INSENSITIVE)
    };

    @Override
    public void initialize(NoCode constraintAnnotation) {
        // Başlangıç konfigürasyonu gerekirse buraya eklenebilir
    }

    @Override
    public boolean isValid(String content, ConstraintValidatorContext context) {
        // Null değerleri geçerli kabul et (null kontrolü başka annotation'larla
        // yapılmalı)
        if (content == null) {
            return true;
        }

        // Her bir pattern'i kontrol et
        for (Pattern pattern : CODE_PATTERNS) {
            if (pattern.matcher(content).find()) {
                return false;
            }
        }

        return true;
    }
}
