package kr.co.shortenurlservice.presentation;

import kr.co.shortenurlservice.application.SimpleShortenUrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ShortenUrlRestController.class) // controller 빈을 실제로 생성
public class ShortenUrlRestControllerTest {

    @MockBean // spring container에서 사용될 mock bean
    private SimpleShortenUrlService simpleShortenUrlService;

    @Autowired
    private MockMvc mockMvc; //컨트롤러 메서드 직접 호출 대신 HTTP 클라이언트 요청 응답 확인 용도 for 컨트롤러 테스트

    @Test
    @DisplayName("원래의 URL로 리다이렉트 되어야 한다.")
    void redirectTest() throws Exception {
        String expectedOriginalUrl = "https://www.hanbit.co.kr/";

        when(simpleShortenUrlService.getOriginalUrlByShortenUrlKey(any())).thenReturn(expectedOriginalUrl);

        mockMvc.perform(get("/any-key")).andExpect(status().isMovedPermanently())
                .andExpect(header().string("Location", expectedOriginalUrl));
    }
}
