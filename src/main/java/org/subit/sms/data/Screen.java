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
@Table(name = "screen")
@AllArgsConstructor
public class Screen {
    @Column(name = "sid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;

    @Column(name = "width", nullable = false)
    private int width;

    @Column(name = "height", nullable = false)
    private int height;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Screen screen = (Screen) o;
        return id != null && Objects.equals(id, screen.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
