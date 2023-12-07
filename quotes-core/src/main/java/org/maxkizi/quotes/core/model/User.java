package org.maxkizi.quotes.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Table(name = "user")
public class User {
    @Id
    private Long id;
    @Column(name = "login")
    private String login;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Quote> quotes;
}
