package rofla.back.back.Dto;

import lombok.Data;

@Data
public class TimeTableDTO {
    private String subjectNum;
    private String subjectName;
    private String professor;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
}
