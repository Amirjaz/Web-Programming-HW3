package com.amirjaz.paintingapp.controller;

import com.amirjaz.paintingapp.dto.ShapeDto;
import com.amirjaz.paintingapp.dto.ShapeRequestDto;
import com.amirjaz.paintingapp.dto.caster.ShapeDtoCaster;
import com.amirjaz.paintingapp.model.Shape;
import com.amirjaz.paintingapp.service.ShapeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.amirjaz.paintingapp.dto.caster.ShapeDtoCaster.fromDto;
import static com.amirjaz.paintingapp.dto.caster.ShapeDtoCaster.toDto;

@RestController
@RequestMapping("/api/shapes")
public class ShapeController {

    private final ShapeService service;

    public ShapeController(ShapeService service) {
        this.service = service;
    }

    @GetMapping
    public List<ShapeDto> all(@RequestParam(required = false) Long paintingId) {
        List<Shape> shapes = paintingId != null
                ? service.findByPainting(paintingId)
                : service.findAll();
        return shapes.stream().map(ShapeDtoCaster::toDto).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShapeDto create(@RequestBody ShapeRequestDto dto) {
        return toDto(service.save(dto.getPaintingId(), fromDto(dto)));
    }

    @PutMapping("/{id}")
    public ShapeDto update(@PathVariable Long id, @RequestBody ShapeRequestDto dto) {
        Shape shape = fromDto(dto);
        Shape updated = service.update(id, shape);
        return toDto(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}