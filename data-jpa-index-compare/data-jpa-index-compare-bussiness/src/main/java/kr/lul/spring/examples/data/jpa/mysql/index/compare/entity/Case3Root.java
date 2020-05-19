package kr.lul.spring.examples.data.jpa.mysql.index.compare.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
import static kr.lul.common.util.Arguments.notEmpty;

/**
 * @author justburrow
 * @since 2020/05/19
 */
@Entity(name = "Case3Root")
@Table(name = "case3_root")
public class Case3Root {
  public static final int ID_LENGTH = 10;

  @Id
  @Column(name = "id", nullable = false, unique = true, updatable = false)
  private String id;
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @OneToMany(mappedBy = "root", cascade = CascadeType.PERSIST)
  @OrderBy("sequence ASC")
  private List<Case3Child> children = new ArrayList<>();

  public Case3Root() {
  }

  public Case3Root(String id) {
    notEmpty(id, "id");
    this.id = id;
  }

  @PrePersist
  private void prePersist() {
    this.createdAt = Instant.ofEpochMilli(System.currentTimeMillis());
  }

  public String getId() {
    return this.id;
  }

  public Instant getCreatedAt() {
    return this.createdAt;
  }

  public Case3Child add() {
    Case3Child child = new Case3Child(this, this.children.size());
    this.children.add(child);
    return child;
  }

  public List<Case3Child> getChildren() {
    return this.children;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Case3Root case3Root = (Case3Root) o;
    return this.id.equals(case3Root.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public String toString() {
    return format("%s{id=%s, children.size=%d, createdAt=%s}",
        Case3Root.class.getSimpleName(), this.id, this.children.size(), this.createdAt);
  }
}
