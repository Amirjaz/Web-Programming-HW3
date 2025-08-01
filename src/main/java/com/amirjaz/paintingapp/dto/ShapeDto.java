package com.amirjaz.paintingapp.dto;

import com.amirjaz.paintingapp.model.types.ShapeType;
import lombok.Data;

@Data
public class ShapeDto {
    private Long id;
    private ShapeType type;
    private double x;
    private double y;
    private double size;
    private Long paintingId;
}