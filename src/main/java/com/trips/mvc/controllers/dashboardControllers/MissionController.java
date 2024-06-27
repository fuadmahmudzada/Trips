package com.trips.mvc.controllers.dashboardControllers;


import com.trips.mvc.dtos.missiondtos.MissionCreateDto;
import com.trips.mvc.dtos.missiondtos.MissionDto;
import com.trips.mvc.dtos.missiondtos.MissionUpdateDto;
import com.trips.mvc.services.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class MissionController {
    @Autowired
    private MissionService missionService;
    private static String data;
    @GetMapping("/admin/mission")
    public String index(Model model) {
        MissionDto mission = missionService.getMission();
        model.addAttribute("mission", mission);
        return "dashboard/mission";

    }

    @GetMapping("/admin/mission/create")
    public String articleCreate( ) {

        return "dashboard/missionCreate";

    }

    @PostMapping("/admin/mission/create")
    public String articleCreate(@ModelAttribute MissionCreateDto missionCreateDto) {
        missionService.add(missionCreateDto);
        data = missionCreateDto.getContent();

        return "redirect:/admin/mission";
    }


    @GetMapping("/admin/mission/update/{id}")
    public String updateArticle(@PathVariable Long id, Model model) {
        MissionUpdateDto missionUpdateDto = missionService.findUpdateMission(id);


        model.addAttribute("mission", missionUpdateDto);

        data = missionUpdateDto.getContent();
        model.addAttribute("data", data);
        return "dashboard/missionUpdate";
    }

    @PostMapping("/admin/mission/update")
    public String updateArticle(@ModelAttribute MissionUpdateDto missionUpdateDto) {

        missionService.updateMission(missionUpdateDto);

        return "redirect:/admin/mission";
    }
}
