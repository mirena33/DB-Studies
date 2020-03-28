package softuni.exam.service;


import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.domain.dtos.PlayerSeedDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.util.ValidatorUtil;


import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final Gson gson;
    private final TeamService teamService;
    private final PictureService pictureService;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, Gson gson, TeamService teamService, PictureService pictureService) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.gson = gson;
        this.teamService = teamService;
        this.pictureService = pictureService;
    }


    @Override
    public String importPlayers() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        PlayerSeedDto[] playerSeedDtos
                = this.gson.fromJson(new FileReader(GlobalConstants.PLAYERS_FILE_PATH), PlayerSeedDto[].class);

        Arrays.stream(playerSeedDtos)
                .forEach(playerSeedDto -> {
                    if (this.validatorUtil.isValid(playerSeedDto)) {
                        if (this.playerRepository
                                .findByFirstNameAndLastName(playerSeedDto.getFirstName(), playerSeedDto.getLastName()) == null) {

                            Player player = this.modelMapper.map(playerSeedDto, Player.class);
                            Team team = this.teamService.getTeamByName(playerSeedDto.getTeam().getName());
                            Picture picture = this.pictureService.getPictureByUrl(playerSeedDto.getPicture().getUrl());

                            if (team != null && picture != null) {
                                player.setTeam(team);
                                player.setPicture(picture);

                                this.playerRepository.saveAndFlush(player);
                                sb
                                        .append("Successfully imported player: ")
                                        .append(playerSeedDto.getFirstName())
                                        .append(" ").append(playerSeedDto.getLastName());
                            }

                        } else {
                            sb.append("Already in DB");
                        }

                    } else {

                        sb.append("Invalid player");
                    }

                    sb.append(System.lineSeparator());
                });

        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return Files.readString(Path.of(GlobalConstants.PLAYERS_FILE_PATH));
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        StringBuilder sb = new StringBuilder();

        this.playerRepository
                .findAllBySalaryGreaterThanOrderBySalaryDesc(BigDecimal.valueOf(100000))
                .forEach(p -> {
                    sb.append(String.format("Player name: %s %s\n" +
                                    "\tNumber: %d\n" +
                                    "\tSalary: %.2f\n" +
                                    "\tTeam: %s\n",
                            p.getFirstName(),
                            p.getLastName(),
                            p.getNumber(),
                            p.getSalary(),
                            p.getTeam().getName()
                    ))
                            .append(System.lineSeparator());
                });

        return sb.toString();
    }

    @Override
    public String exportPlayersInATeam() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team name: North Hub").append(System.lineSeparator());

        this.playerRepository.findAllByTeamName("North Hub")
                .forEach(p -> {
                    sb.append(String.format("\tPlayer name: %s %s - %s\n" +
                                    "\tNumber: %d\n",
                            p.getFirstName(),
                            p.getLastName(),
                            p.getPosition(),
                            p.getNumber()
                    ))
                            .append(System.lineSeparator());
                });

        return sb.toString();
    }


}
