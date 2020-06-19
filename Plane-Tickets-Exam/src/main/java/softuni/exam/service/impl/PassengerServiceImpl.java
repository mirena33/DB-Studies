package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.PassengerSeedDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final TownService townService;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, TownService townService) {
        this.passengerRepository = passengerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(Path.of(GlobalConstants.PASSENGERS_PATH));
    }

    @Override
    public String importPassengers() throws IOException {
        StringBuilder sb = new StringBuilder();

        PassengerSeedDto[] passengerSeedDtos = this.gson.fromJson(new FileReader(GlobalConstants.PASSENGERS_PATH), PassengerSeedDto[].class);

        Arrays.stream(passengerSeedDtos)
                .forEach(passengerSeedDto -> {
                    if (this.validationUtil.isValid(passengerSeedDto)) {
                        if (this.passengerRepository.getByEmail(passengerSeedDto.getEmail()) == null) {
                            Passenger passenger = this.modelMapper.map(passengerSeedDto, Passenger.class);
                            Town town = this.townService.getTownByName(passengerSeedDto.getTown());

                            passenger.setTown(town);
                            this.passengerRepository.saveAndFlush(passenger);

                            sb.append(String.format("Successfully imported Passenger %s - %s",
                                    passengerSeedDto.getLastName(),
                                    passengerSeedDto.getEmail()));

                        } else {
                            sb.append("Already in DB");
                        }
                    } else {
                        sb.append("Invalid Passenger");
                    }
                    sb.append(System.lineSeparator());
                });

        return sb.toString();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {

        return this.passengerRepository.findAllOrderedByTicketsDESCAndEmailASC()
                .stream()
                .map(p -> {
                    return String.format("Passenger %s %s\n" +
                                    "\tEmail - %s\n" +
                                    "\tPhone - %s\n" +
                                    "\tNumber of tickets - %d\n",
                            p.getFirstName(), p.getLastName(),
                            p.getEmail(), p.getPhoneNumber(),
                            p.getTickets().size());
                })
                .collect(Collectors.joining());
    }

    @Override
    public Passenger getPassengerByEmail(String email) {
        return this.passengerRepository.getByEmail(email);
    }
}
