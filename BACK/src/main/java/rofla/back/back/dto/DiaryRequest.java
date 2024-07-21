package rofla.back.back.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryRequest {
    String username;
    String date;
    Integer emptyNum;
}
