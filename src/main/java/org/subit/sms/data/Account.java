package org.subit.sms.data;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;


    /*
    save after running
    SaSecureUtil.sha256(password);
     */
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email")
    private String email;

    /*
    0 - super user
    1 - admin
    2 - student
     */
    @Column(name = "role", nullable = false)
    private int role;

    /*
    true - forbidden to login
     */
    @Column(name = "disable")
    private boolean disable = false;

    @Column(name = "deleted")
    private boolean deleted = false;

    @Column(name = "activated")
    private boolean activated;

}
