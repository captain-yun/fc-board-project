package com.kitri.boardproject.service;

import com.kitri.boardproject.ArticleDto;
import com.kitri.boardproject.domain.constant.SearchType;
import com.kitri.boardproject.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    // @InjectMocks : mock을 주입하는 대상
    // 나머지 mock은 @Mock
    @InjectMocks private ArticleService sut; // System Under Test : 테스트 대상이다라는 뜻
    @Mock private ArticleRepository articleRepository;

    // 구현해야하는 기능들
        //    검색
        //    각 게시글 페이지로 이동
        //    페이지네이션
        //    홈 버튼 -> 게시판 페이지로 리다이렉션
        //    정렬 기능
    @DisplayName("비즈니스 로직 - 게시글을 검색하면 게시글 리스트 반환")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        // Given
//        SearchParameters params = SearchParameters.of(SearchType.TITLE, "search keyword");

        // When
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); // 제목, 본문, ID, 닉네임, 해시태그

        // Then
        assertThat(articles).isNotNull();
    }

    @DisplayName("비즈니스 로직 - 게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticle_thenReturnsArticle() {
        // Given
//        SearchParameters params = SearchParameters.of(SearchType.TITLE, "search keyword");

        // When
        ArticleDto article = sut.searchArticle(1L);

        // Then
        assertThat(article).isNotNull();
    }
}