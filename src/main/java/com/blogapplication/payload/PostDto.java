package com.blogapplication.payload;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;
    @NotBlank(message = "Title should not be blank or empty")
    private String title;
    private String url;
    @NotBlank(message = "Content should not be blank or empty")
    private String content;
    @NotBlank(message = "Description should not be blank or empty")
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Set<CommentDto> comments;
}
