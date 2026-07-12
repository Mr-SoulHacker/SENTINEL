package com.sentinel.sentinel.controller.IncidentController;
import com.sentinel.sentinel.model.Incident;
import com.sentinel.sentinel.service.IncidentService;
import org.springframework.data.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    private final IncidentService incidentService;
    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

@PostMapping
    public Incident create(@RequestBody Incident incident){
        return incidentService.save(incident);
}

@GetMapping("/Thefts")
    public List<Incident> getThefts(){
        return incidentService.getThefts();
}
    @GetMapping("/accidents")
    public List<Incident> getAccidents() {
        return incidentService.getAccidents();
    }
}
