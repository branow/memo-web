package com.branow.memoweb.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPublicDto {

    private Integer userId;
    private String username;
    private String description;
    private Integer daysStreak;
    private Integer createdCards;
    private Integer cardsStudied;

}
