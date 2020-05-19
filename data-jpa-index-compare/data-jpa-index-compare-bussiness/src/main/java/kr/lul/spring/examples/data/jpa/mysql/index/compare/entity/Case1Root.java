package kr.lul.spring.examples.data.jpa.mysql.index.compare.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2020/05/17
 */
@Entity(name = "Case1Root")
@Table(name = "case1_root")
public class Case1Root {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
  private long id;
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @OneToMany(targetEntity = Case1Child.class, mappedBy = "root", orphanRemoval = true, cascade = CascadeType.PERSIST)
  @OrderBy("sequence ASC")
  private List<Case1Child> children = new ArrayList<>();

  public Case1Root() {
  }

  @PrePersist
  private void prePersist() {
    this.createdAt = Instant.ofEpochMilli(System.currentTimeMillis());
  }

  public long getId() {
    return this.id;
  }

  public Instant getCreatedAt() {
    return this.createdAt;
  }

  public Case1Child add() {
    Case1Child child = new Case1Child(this, this.children.size());
    this.children.add(child);
    return child;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Case1Root that = (Case1Root) o;
    return this.id == that.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public String toString() {
    return format("%s{id=%d, createdAt=%s}", Case1Root.class.getSimpleName(), this.id, this.createdAt);
  }
}
