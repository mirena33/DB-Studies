package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.TicketRootSeedDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Ticket;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TicketRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.PlaneService;
import softuni.exam.service.TicketService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final PassengerService passengerService;
    private final PlaneService planeService;
    private final TownService townService;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, PassengerService passengerService, PlaneService planeService, TownService townService) {
        this.ticketRepository = ticketRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.passengerService = passengerService;
        this.planeService = planeService;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(Path.of(GlobalConstants.TICKETS_PATH));
    }

    @Override
    public String importTickets() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        TicketRootSeedDto ticketRootSeedDto = this.xmlParser.parseXml(TicketRootSeedDto.class, GlobalConstants.TICKETS_PATH);

        ticketRootSeedDto.getTickets()
                .forEach(ticketSeedDto -> {
                    if (this.validationUtil.isValid(ticketSeedDto)) {
                        if (this.ticketRepository.findBySerialNumber(ticketSeedDto.getSerialNumber()) == null) {

                            Ticket ticket = this.modelMapper.map(ticketSeedDto, Ticket.class);
                            Plane plane = this.planeService.getPlaneByRegNumber(ticketSeedDto.getPlane().getRegisterNumber());
                            Passenger passenger = this.passengerService.getPassengerByEmail(ticketSeedDto.getPassenger().getEmail());
                            Town fromTown = this.townService.getTownByName(ticketSeedDto.getFromTown().getName());
                            Town toTown = this.townService.getTownByName(ticketSeedDto.getToTown().getName());

                            ticket.setPlane(plane);
                            ticket.setPassenger(passenger);
                            ticket.setFromTown(fromTown);
                            ticket.setToTown(toTown);

                            if (plane == null || passenger == null || fromTown == null || toTown == null) {
                                sb.append("Invalid Ticket");

                            } else {
                                this.ticketRepository.saveAndFlush(ticket);

                                sb.append(String.format("Successfully imported Ticket %s - %s",
                                        ticketSeedDto.getFromTown().getName(),
                                        ticketSeedDto.getToTown().getName()));
                            }

                        } else {
                            sb.append("Already in DB");
                        }
                    } else {
                        sb.append("Invalid Ticket");
                    }
                    sb.append(System.lineSeparator());
                });

        return sb.toString();
    }

    @Override
    public Ticket getTicketBySerialNumber(String number) {
        return this.ticketRepository.findBySerialNumber(number);
    }
}
