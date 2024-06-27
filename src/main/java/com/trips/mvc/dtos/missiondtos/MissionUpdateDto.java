package com.trips.mvc.dtos.missiondtos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionUpdateDto {
    private Long id;
    private String header;
    private String content;
    private String image;
}
