package com.amirjaz.paintingapp.controller;

import com.amirjaz.paintingapp.dto.PaintingDto;
import com.amirjaz.paintingapp.dto.PaintingRequestDto;
import com.amirjaz.paintingapp.dto.caster.PaintingDtoCaster;
import com.amirjaz.paintingapp.model.Painting;
import com.amirjaz.paintingapp.service.PaintingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.amirjaz.paintingapp.dto.caster.PaintingDtoCaster.toDto;

@RestController
@RequestMapping("/api/paintings")
public class PaintingController {

    private final PaintingService service;

    public PaintingController(PaintingService service) {
        this.service = service;
    }

    @GetMapping
    public List<PaintingDto> all() {
        return service.findAll().stream().map(PaintingDtoCaster::toDto).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaintingDto create(@RequestBody PaintingRequestDto dto) {
        Painting painting = new Painting();
        painting.setName(dto.getName());
        Painting saved = service.save(painting);
        return toDto(saved);
    }

    @GetMapping("/{id}")
    public PaintingDto one(@PathVariable Long id) {
        return toDto(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
