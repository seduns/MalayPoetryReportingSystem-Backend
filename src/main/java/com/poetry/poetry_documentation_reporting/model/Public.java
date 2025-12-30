package com.poetry.poetry_documentation_reporting.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;

@Entity
@Data
@AllowNonPortable
@NoArgsConstructor
public class Public {

    // Shared PK dengan User
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

}
