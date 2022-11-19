package org.subit.sms.data;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "play_list")
@AllArgsConstructor
public class PlayList {

    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlayList playList = (PlayList) o;
        return id != null && Objects.equals(id, playList.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
