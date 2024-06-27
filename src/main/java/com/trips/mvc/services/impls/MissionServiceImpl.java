package com.trips.mvc.services.impls;

import com.trips.mvc.dtos.missiondtos.MissionCreateDto;
import com.trips.mvc.dtos.missiondtos.MissionDto;
import com.trips.mvc.dtos.missiondtos.MissionUpdateDto;
import com.trips.mvc.dtos.storydtos.StoryCreateDto;
import com.trips.mvc.dtos.storydtos.StoryDto;
import com.trips.mvc.dtos.storydtos.StoryUpdateDto;
import com.trips.mvc.models.Mission;
import com.trips.mvc.models.Story;
import com.trips.mvc.repositories.MissionRepository;
import com.trips.mvc.repositories.StoryRepository;
import com.trips.mvc.services.MissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissionServiceImpl implements MissionService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MissionRepository missionRepository;
    @Override
    public void add(MissionCreateDto missionCreateDto) {
        try {
            Mission mission = new Mission();


//            article.setAuthor(articleCreateDto.getAuthor());
            mission.setContent(missionCreateDto.getContent());
            mission.setHeader(missionCreateDto.getHeader());
            mission.setImage(missionCreateDto.getImage());




            missionRepository.save(mission);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public MissionDto getMission() {

        MissionDto missionDto = missionRepository.findAll()
                .stream().map(mission -> modelMapper.map(mission, MissionDto.class))
                .findFirst().orElseThrow();
        return missionDto;
    }

    @Override
    public void updateMission(MissionUpdateDto missionUpdateDto) {
        Mission findMission = missionRepository.findById(missionUpdateDto.getId()).orElseThrow();

        findMission.setId(missionUpdateDto.getId());
        findMission.setHeader(missionUpdateDto.getHeader());
//        findArticle.setAuthor(articleUpdateDto.getAuthor());
        findMission.setContent(missionUpdateDto.getContent());


        findMission.setImage(missionUpdateDto.getImage());


        missionRepository.saveAndFlush(findMission);
    }

    @Override
    public MissionUpdateDto findUpdateMission(Long id) {
        Mission mission = missionRepository.findById(id).orElseThrow();
        MissionUpdateDto missionUpdateDto = modelMapper.map(mission, MissionUpdateDto.class);
        return missionUpdateDto;
    }

}
