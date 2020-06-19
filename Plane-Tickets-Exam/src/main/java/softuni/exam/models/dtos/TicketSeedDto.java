package softuni.exam.models.dtos;

import org.hibernate.validator.constraints.Length;
import softuni.exam.adaptors.LocalDateTimeAdaptor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedDto {

    @XmlElement(name = "serial-number")
    private String serialNumber;

    @XmlElement
    private BigDecimal price;

    @XmlElement(name = "take-off")
    @XmlJavaTypeAdapter(LocalDateTimeAdaptor.class)
    private LocalDateTime takeoff;

    @XmlElement(name = "from-town")
    private TownSeedDto fromTown;

    @XmlElement(name = "to-town")
    private TownSeedDto toTown;

    @XmlElement
    private PassengerSeedDto passenger;

    @XmlElement
    private PlaneDto plane;

    public TicketSeedDto() {
    }

    @NotNull
    @Length(min = 2)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @DecimalMin(value = "0")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull
    public LocalDateTime getTakeoff() {
        return takeoff;
    }

    public void setTakeoff(LocalDateTime takeoff) {
        this.takeoff = takeoff;
    }

    public TownSeedDto getFromTown() {
        return fromTown;
    }

    public void setFromTown(TownSeedDto fromTown) {
        this.fromTown = fromTown;
    }

    public TownSeedDto getToTown() {
        return toTown;
    }

    public void setToTown(TownSeedDto toTown) {
        this.toTown = toTown;
    }

    public PassengerSeedDto getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerSeedDto passenger) {
        this.passenger = passenger;
    }

    public PlaneDto getPlane() {
        return plane;
    }

    public void setPlane(PlaneDto plane) {
        this.plane = plane;
    }
}
