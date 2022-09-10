package ru.gothmog.ws.auth.core.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import ru.gothmog.ws.auth.core.model.AbstractEntity;

import javax.persistence.Index;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.UniqueConstraint;
import javax.persistence.FetchType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "auth",
        indexes = {
                @Index(name = "unq_users_email_key", columnList = "email", unique = true),
                @Index(name = "unq_users_username_key", columnList = "username", unique = true)
        },
        uniqueConstraints = {@UniqueConstraint(name = "unq_users_email_username_key", columnNames = {"email", "username"})}
)
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends AbstractEntity {
    @NaturalId
    @NotBlank
    @Size(max = 150)
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 150)
    @Email
    @Column(name = "email", length = 150)
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(name = "pswd")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", schema = "auth",
            joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_roles_users")),
            inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_user_roles_roles")))
    private Set<Role> roles = new HashSet<>();

}
