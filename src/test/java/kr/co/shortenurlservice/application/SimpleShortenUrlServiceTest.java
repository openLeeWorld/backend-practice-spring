package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.NotFoundShortenUrlException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kr.co.shortenurlservice.presentation.ShortenUrlCreateRequestDto;
import kr.co.shortenurlservice.presentation.ShortenUrlCreateResponseDto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SimpleShortenUrlServiceTest {

    @Autowired
    private SimpleShortenUrlService simpleShortenUrlService;

    @Test
    @DisplayName("URL을 단축한 후 단축된 URL키로 조회하면 원래 URL이 조회되어야 한다.")
    void shortenUrlAddTest() {
        String expectedOriginalUrl = "http://www.hanbit.co.kr";
        ShortenUrlCreateRequestDto shortenUrlCreateRequestDto = new ShortenUrlCreateRequestDto();
        shortenUrlCreateRequestDto.setOriginalUrl(expectedOriginalUrl);

        ShortenUrlCreateResponseDto shortenUrlCreateResponseDto =
                simpleShortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto);

        String shortenUrlKey = shortenUrlCreateResponseDto.getShortenUrlKey();

        String originalUrl = simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);
        assertTrue(originalUrl.equals(expectedOriginalUrl));
    }

    @Test
    @DisplayName("존재하지 않는 단축 URL을 조회하는 경우에는 LackOfShortenUrlKeyException을 던져야 한다.")
    void findShortenUrlNotExistedTest() {
        String notExistedUrl = "mNCQ8ET3";

        assertThrows(NotFoundShortenUrlException.class, () -> {
            simpleShortenUrlService.getOriginalUrlByShortenUrlKey(notExistedUrl);
        });

        assertThrows(NotFoundShortenUrlException.class, () -> {
            simpleShortenUrlService.getShortenUrlInformationByShortenUrlKey(notExistedUrl);
        });
    }
}
