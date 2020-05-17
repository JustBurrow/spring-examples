package kr.lul.spring.examples.data.jpa.mysql.index.compare.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2020/05/17
 */
@Entity(name = "Case1Child")
@Table(name = "case1_child")
public class Case1Child {
  @EmbeddedId
  private Case1ChildId id;
  @ManyToOne(targetEntity = Case1Root.class)
  @JoinColumn(name = "root",
      nullable = false,
      insertable = false,
      updatable = false,
      foreignKey = @ForeignKey(name = "fk_case1_child_pk_case1_root"),
      referencedColumnName = "id")
  @MapsId("root")
  private Case1Root root;
  @Column(name = "seq", nullable = false, insertable = false, updatable = false)
  @MapsId("getSequence")
  private int sequence;
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  public Case1Child() {
  }

  public Case1Child(Case1Root root, int sequence) {
    this.root = root;
    this.sequence = sequence;
  }

  @PrePersist
  private void prePersist() {
    this.id = new Case1ChildId(this.root.getId(), this.sequence);
    this.createdAt = Instant.ofEpochMilli(System.currentTimeMillis());
  }

  public Case1ChildId getId() {
    return this.id;
  }

  public Case1Root getRoot() {
    return this.root;
  }

  public int getSequence() {
    return this.sequence;
  }

  public Instant getCreatedAt() {
    return this.createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (this.id == null || o == null || getClass() != o.getClass()) return false;
    return this.id.equals(((Case1Child) o).id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public String toString() {
    return format("%s(id=%s, root=%d, getSequence=%d, createdAt=%s)",
        Case1Child.class.getSimpleName(), this.id, this.root.getId(), this.sequence, this.createdAt);
  }
}
