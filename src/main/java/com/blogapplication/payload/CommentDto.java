package com.blogapplication.payload;

import com.blogapplication.model.Post;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;
    @NotBlank(message = "You need to pass name")
    private String name;
    @NotBlank(message = "Email should not be blank or empty")
    @Email(message = "You need to pass proper email")
    private String email;
    @NotBlank(message = "Comment should not be blank or empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
