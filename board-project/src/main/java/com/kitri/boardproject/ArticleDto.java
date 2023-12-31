package com.kitri.boardproject;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.kitri.boardproject.domain.Article}
 */
@Value
public class ArticleDto implements Serializable {
    LocalDateTime createdAt;
    String createdBy;
    String title;
    String content;
    String hashtag;

    public static ArticleDto of(LocalDateTime createdAt, String createdBy, String title, String content, String hashtag) {
        return new ArticleDto(createdAt, createdBy, title, content, hashtag);
    }
}