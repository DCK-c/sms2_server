package org.subit.sms.Data;

import cn.dev33.satoken.secure.SaSecureUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
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
