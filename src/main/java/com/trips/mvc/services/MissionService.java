package com.trips.mvc.services;

import com.trips.mvc.dtos.bannerdtos.BannerCreateDto;
import com.trips.mvc.dtos.bannerdtos.BannerDto;
import com.trips.mvc.dtos.bannerdtos.BannerUpdateDto;
import com.trips.mvc.dtos.missiondtos.MissionCreateDto;
import com.trips.mvc.dtos.missiondtos.MissionDto;
import com.trips.mvc.dtos.missiondtos.MissionUpdateDto;

public interface MissionService {

    void add(MissionCreateDto missionCreateDto);

    MissionDto getMission();
    void updateMission(MissionUpdateDto missionUpdateDto);
    MissionUpdateDto findUpdateMission(Long id);
}
