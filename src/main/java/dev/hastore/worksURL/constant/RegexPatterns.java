package dev.hastore.worksURL.constant;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public enum RegexPatterns {
    WORKS_GUIDE1("검사 후 위협 URL 일 시, 전체, HOST, 쿼리 제외 중 다시 판단하여 등록해주세요\\.?\\s*"),
    WORKS_GUIDE2("해당 URL 직접 확인 후 전체, HOST, 쿼리 제외 중 다시 판단하여 등록해주세요\\.?\\s*"),
    WORKS_GUIDE3("\\*정상 URL 등록 주의\\s*"),
    WORKS_GUIDE4("\\*Short Link URL 등록 주의\\s*"),
    WORKS_GUIDE5("\\[CUBE URL 검출\\]\\s*"),
    WORKS_GUIDE6("\\s*\\-\\s*SOCIAL_ENGINEERING"),
    WORKS_GUIDE7("kiwontech_bot\\s*"),
    WORKS_GUIDE8("위험 URL이 검출되었습니다.\\s*");
    private final String patternString;
    private final Pattern compiledPattern;

    RegexPatterns(String patternString) {
        this.patternString = patternString;
        this.compiledPattern = Pattern.compile(patternString); // 생성 시점에 컴파일
    }

}
