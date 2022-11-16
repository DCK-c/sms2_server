package org.subit.sms.Data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@Table(name = "play_list")
@AllArgsConstructor
@NoArgsConstructor
public class PlayList {

    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "did", nullable = false)
    private Draft draft;

    /*
    1 - left screen
    2 - right screen
     */
    @ManyToOne
    @JoinColumn(name = "sid")
    private Screen screen;
    /*
    higher priority will be play earlier than lower priority
     */
    @Column(name = "priority")
    private int priority;
}
