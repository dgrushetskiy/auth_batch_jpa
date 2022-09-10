package ru.gothmog.ws.auth.core.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NaturalId;
import ru.gothmog.ws.auth.core.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;


@Entity
@Table(name = "roles", schema = "auth",
        indexes = {@Index(name = "unq_roles_role_name_key", columnList = "role_name", unique = true)})
@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "role_name", length = 100)
    private RoleName roleName;
}
