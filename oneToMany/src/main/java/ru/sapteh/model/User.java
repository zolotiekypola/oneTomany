package ru.sapteh.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 50)
    @NonNull
    private String firstName;

    @Column(nullable = false,length = 50)
    @NonNull
    private String lastName;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<UserRole> user_roles;

    public void addUser(UserRole userRole){
        if(user_roles == null) user_roles = new HashSet<>();
        this.user_roles.add(userRole);
    }

    @Transient
    private final int sizeRole = sizeRoleMethod();

    @Transient
    public int sizeRoleMethod(){
        if(user_roles == null) user_roles = new HashSet<>();
        return user_roles.size();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
