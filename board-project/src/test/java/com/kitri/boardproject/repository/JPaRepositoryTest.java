package com.kitri.boardproject.repository;

import com.kitri.boardproject.config.JpaConfig;
import com.kitri.boardproject.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class) // JPA auditing 기능 넣어주기 위해
@DataJpaTest
class JPaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    public JPaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        List<Article> articles = articleRepository.findAll();

        assertThat(articles)
                .isNotNull()
                .hasSize(100);
    }

    @DisplayName(("insert 테스트"))
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        long previousCount = articleRepository.count();
        articleRepository.save(Article.of("new article", "new content", "#spring"));

        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName(("update 테스트"))
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);

        // When
        Article savedArticle = articleRepository.saveAndFlush(article);

        // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @DisplayName(("delete 테스트"))
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        // cascading 옵션을 걸어놨기 때문에 게시글 삭제 시 연관된 댓글도 사라지는지 테스트 해야함.
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        long deletedCommentSize = article.getArticleComments().size();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentSize);
    }

}