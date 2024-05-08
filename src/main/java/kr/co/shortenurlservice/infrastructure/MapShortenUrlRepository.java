package kr.co.shortenurlservice.infrastructure;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.domain.ShortenUrlRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MapShortenUrlRepository implements ShortenUrlRepository {
    private Map<String, ShortenUrl> shortenUrls = new ConcurrentHashMap<>();

    @Override
    public void saveShortenUrl(ShortenUrl shortenUrl){
        shortenUrls.put(shortenUrl.getShortenUrlKey(), shortenUrl);
    } // 단축 url 생성 기능 저장소

    @Override
    public ShortenUrl findShortenUrlByShortenUrlKey(String shortenUrlKey){
        ShortenUrl shortenUrl = shortenUrls.get(shortenUrlKey); // Map은 value가 없는 key를 받으면 null 리턴
        return shortenUrl;
    }
}
