package kr.co.shortenurlservice.domain;

import java.util.Random;

public class ShortenUrl {
    private String  originalUrl;
    private String shortenUrlKey;
    private Long redirectCount;

    public ShortenUrl(String originalUrl, String shortenUrlKey) {
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.redirectCount = 0L;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }

    public static String generateShortenUrlKey() {
        String base56characters = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
        Random random = new Random();
        StringBuilder shortenUrlKey = new StringBuilder();
        // stringBuilder가 스레드(tomcat) 스택에서 지역변수로서 처리되기 때문에 스레드 세이프하지 않아도 사용 가능
        for(int count = 0; count < 8; count++) {
            int base56CharacterIndex = random.nextInt(0, base56characters.length());
            char base56character = base56characters.charAt(base56CharacterIndex);
            shortenUrlKey.append(base56character);
        }

        return shortenUrlKey.toString();
    }

    public void increaseRedirectCount() {
        this.redirectCount = this.redirectCount + 1;
    }
}
