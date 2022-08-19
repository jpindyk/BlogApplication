package com.blogapplication.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
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
}
