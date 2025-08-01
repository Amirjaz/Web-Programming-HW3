package com.amirjaz.paintingapp.dto.caster;

import com.amirjaz.paintingapp.dto.ShapeDto;
import com.amirjaz.paintingapp.dto.ShapeRequestDto;
import com.amirjaz.paintingapp.model.Shape;

public class ShapeDtoCaster {
    public static ShapeDto toDto(Shape shape) {
        ShapeDto dto = new ShapeDto();
        dto.setId(shape.getId());
        dto.setType(shape.getType());
        dto.setX(shape.getX());
        dto.setY(shape.getY());
        dto.setSize(shape.getSize());
        if (shape.getPainting() != null) {
            dto.setPaintingId(shape.getPainting().getId());
        }
        return dto;
    }

    public static Shape fromDto(ShapeRequestDto dto) {
        Shape shape = new Shape();
        shape.setType(dto.getType());
        shape.setX(dto.getX());
        shape.setY(dto.getY());
        shape.setSize(dto.getSize());
        return shape;
    }
}
