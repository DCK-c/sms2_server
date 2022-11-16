package org.subit.sms.Data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "screen")
@AllArgsConstructor
@NoArgsConstructor
public class Screen {
    @Column(name = "sid")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    @Column(name = "width", nullable = false)
    private int width;

    @Column(name = "height", nullable = false)
    private int height;
}
