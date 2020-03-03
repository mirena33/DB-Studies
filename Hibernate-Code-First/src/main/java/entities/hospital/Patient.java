package entities.hospital;

import entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntity {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private LocalDateTime dateOfBirth;
    private String picture;
    private boolean hasMedicalInsurance;

    public Patient() {
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "date_of_birth")
    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "picture", columnDefinition = "BLOB")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Column(name = "has_medical_insurance")
    public boolean isHasMedicalInsurance() {
        return hasMedicalInsurance;
    }

    public void setHasMedicalInsurance(boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }
}
