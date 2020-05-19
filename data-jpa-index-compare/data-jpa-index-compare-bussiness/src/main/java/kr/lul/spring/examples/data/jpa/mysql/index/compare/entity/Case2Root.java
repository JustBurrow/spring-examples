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
@Entity(name = "Case2Root")
@Table(name = "case2_root")
public class Case2Root {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
  private long id;
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @OneToMany(targetEntity = Case2Child.class, mappedBy = "root", orphanRemoval = true, cascade = CascadeType.PERSIST)
  @OrderBy("sequence ASC")
  private List<Case2Child> children = new ArrayList<>();

  public Case2Root() {
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

  public Case2Child add() {
    Case2Child child = new Case2Child(this, this.children.size());
    this.children.add(child);
    return child;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Case2Root that = (Case2Root) o;
    return this.id == that.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public String toString() {
    return format("%s{id=%d, children.size=%d, createdAt=%s}",
        Case2Root.class.getSimpleName(), this.id, this.children.size(), this.createdAt);
  }
}
