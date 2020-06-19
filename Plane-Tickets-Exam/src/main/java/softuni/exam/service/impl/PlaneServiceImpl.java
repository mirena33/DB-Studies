package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.PlaneRootSeedDto;
import softuni.exam.models.entities.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository planeRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.planeRepository = planeRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(Path.of(GlobalConstants.PLANES_PATH));
    }

    @Override
    public String importPlanes() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        PlaneRootSeedDto planeRootSeedDto = this.xmlParser.parseXml(PlaneRootSeedDto.class, GlobalConstants.PLANES_PATH);

        planeRootSeedDto.getPlanes()
                .forEach(planeSeedDto -> {
                    if (this.validationUtil.isValid(planeSeedDto)) {
                        if (this.planeRepository.getByRegisterNumber(planeSeedDto.getRegisterNumber()) == null) {
                            Plane plane = this.modelMapper.map(planeSeedDto, Plane.class);
                            this.planeRepository.saveAndFlush(plane);

                            sb.append(String.format("Successfully imported Plane %s",
                                    plane.getRegisterNumber()));

                        } else {
                            sb.append("Already in DB");
                        }
                    } else {
                        sb.append("Invalid Plane");
                    }
                    sb.append(System.lineSeparator());
                });

        return sb.toString();
    }

    @Override
    public Plane getPlaneByRegNumber(String number) {
        return this.planeRepository.getByRegisterNumber(number);
    }
}
