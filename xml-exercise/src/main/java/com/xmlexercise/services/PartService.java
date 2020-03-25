package com.xmlexercise.services;

import com.xmlexercise.models.dtos.PartSeedDto;
import com.xmlexercise.models.entities.Part;

import java.util.List;
import java.util.Set;

public interface PartService {

    void seedParts(List<PartSeedDto> partSeedRootDtos);

    Set<Part> getRandomParts();
}
