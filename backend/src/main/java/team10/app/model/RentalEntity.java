package team10.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    protected String title;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    protected Address address;
    @Column(nullable = false, length = 500)
    protected String description;
    @Column(nullable = false, length = 200)
    protected String rulesOfConduct;
    @Column(nullable = false, length = 200)
    protected String additionalServices;
    @Column(nullable = false)
    protected int price;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "picture_id")
    protected Set<Picture> pictures;
    protected boolean deleted = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    protected BusinessClient owner;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_id")
    protected Set<Availability> availability;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<Reservation> reservations;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<Action> actions;

    public void setPictures(Set<Picture> pictures) {
        this.pictures.clear();
        if (pictures != null) {
            this.pictures.addAll(pictures);
        }
    }

    public void setAvailability(Set<Availability> availability) {
        this.availability.clear();
        if (availability != null) {
            this.availability.addAll(availability);
        }
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations.clear();
        if (reservations != null) {
            this.reservations.addAll(reservations);
        }
    }

    public void setActions(Set<Action> actions) {
        this.actions.clear();
        if (actions != null) {
            this.actions.addAll(actions);
        }
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public Set<Long> getTakenDates() {
        Set<Long> dates = new HashSet<>();
        Set<Reservation> reservations = new HashSet<>(this.getReservations());
        reservations.addAll(this.getActions());
        for (Reservation reservation : reservations) {
            long date = reservation.getStartDate();
            while (date <= reservation.getEndDate()) {
                dates.add(date);
                date += TimeUnit.DAYS.toMillis(1);
            }
        }
        return dates;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
