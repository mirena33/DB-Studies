package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.TownSeedDto;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }


    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(GlobalConstants.TOWNS_PATH));
    }

    @Override
    public String importTowns() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        TownSeedDto[] townSeedDtos = this.gson.fromJson(new FileReader(GlobalConstants.TOWNS_PATH), TownSeedDto[].class);

        Arrays.stream(townSeedDtos)
                .forEach(townSeedDto -> {
                    if (this.validationUtil.isValid(townSeedDto)) {
                        if (this.townRepository.getByName(townSeedDto.getName()) == null) {

                            Town town = this.modelMapper.map(townSeedDto, Town.class);
                            this.townRepository.saveAndFlush(town);

                            sb.append(String.format("Successfully imported Town %s - %d", townSeedDto.getName(),
                                    townSeedDto.getPopulation()));

                        } else {
                            sb.append("Already in DB");
                        }
                    } else {
                        sb.append("Invalid Town");
                    }

                    sb.append(System.lineSeparator());
                });

        return sb.toString();
    }

    @Override
    public Town getTownByName(String name) {
        return this.townRepository.getByName(name);
    }
}
