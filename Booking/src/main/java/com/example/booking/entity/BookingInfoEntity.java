package com.example.booking.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "booking")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date fromDate;

    private Date toDate;

    private String aadharNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int numOfRooms;

    private String roomNumbers;

    @Column(nullable = false)
    private int roomPrice;

    @Column(columnDefinition = "int default 0")
    private int transactionId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookedOn;


}
