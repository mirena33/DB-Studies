package entities.billsSystem;

import entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "billing_details")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class BillingDetail extends BaseEntity {
    private String number;
    private String owner;

    public BillingDetail() {
    }

    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
