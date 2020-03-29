package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.GlobalConstants;
import hiberspring.domain.dtos.TownSeedDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return Files.readString(Path.of(GlobalConstants.TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns(String townsFileContent) throws FileNotFoundException {
        StringBuilder resultInfo = new StringBuilder();

        TownSeedDto[] dtos = this.gson.fromJson(new FileReader(GlobalConstants.TOWNS_FILE_PATH), TownSeedDto[].class);

        Arrays.stream(dtos)
                .forEach(townSeedDto -> {
                    if (this.validationUtil.isValid(townSeedDto)) {
                        if (this.townRepository.getByName(townSeedDto.getName()) == null) {

                            Town town = this.modelMapper.map(townSeedDto, Town.class);
                            this.townRepository.saveAndFlush(town);

                            resultInfo.append(String.format(GlobalConstants.SUCCESSFUL_IMPORT_MESSAGE, "Town", townSeedDto.getName()));

                        } else {
                            resultInfo.append("Already in DB");
                        }
                    } else {
                        resultInfo.append(GlobalConstants.INCORRECT_DATA_MESSAGE);
                    }
                    resultInfo.append(System.lineSeparator());
                });

        return resultInfo.toString();
    }

    @Override
    public Town getTownByName(String name) {
        return this.townRepository.getByName(name);
    }
}
