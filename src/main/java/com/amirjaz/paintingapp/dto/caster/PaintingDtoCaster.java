package com.amirjaz.paintingapp.dto.caster;

import com.amirjaz.paintingapp.dto.PaintingDto;
import com.amirjaz.paintingapp.model.Painting;

public class PaintingDtoCaster {
    public static PaintingDto toDto(Painting painting) {
        PaintingDto dto = new PaintingDto();
        dto.setId(painting.getId());
        dto.setName(painting.getName());
        return dto;
    }
}
