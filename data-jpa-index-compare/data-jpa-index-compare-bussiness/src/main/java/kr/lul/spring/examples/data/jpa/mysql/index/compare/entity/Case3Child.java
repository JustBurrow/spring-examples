package kr.lul.spring.examples.data.jpa.mysql.index.compare.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

/**
 * @author justburrow
 * @since 2020/05/19
 */
@Entity(name = "Case3Child")
@Table(name = "case3_child")
public class Case3Child {
  @EmbeddedId
  @AttributeOverrides({
      @AttributeOverride(name = "root", column = @Column(name = "root", nullable = false, updatable = false)),
      @AttributeOverride(name = "sequence", column = @Column(name = "seq", nullable = false, updatable = false))
  })
  private Case3ChildId id;
  @ManyToOne
  @JoinColumn(name = "root", nullable = false, insertable = false, updatable = false,
      foreignKey = @ForeignKey(name = "fk_case3_child_pk_case3_root"), referencedColumnName = "id")
  @MapsId("root")
  private Case3Root root;
  @Column(name = "seq", nullable = false, insertable = false, updatable = false)
  @MapsId("sequence")
  private int sequence;
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  public Case3Child() {
  }

  public Case3Child(Case3Root root, int sequence) {
    this.id = new Case3ChildId(root.getId(), sequence);
    this.root = root;
    this.sequence = sequence;
  }

  @PrePersist
  private void prePersist() {
    this.createdAt = Instant.ofEpochMilli(System.currentTimeMillis());
  }

  public Case3ChildId getId() {
    return this.id;
  }

  public Case3Root getRoot() {
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
    if (o == null || getClass() != o.getClass()) return false;
    return this.id.equals(((Case3Child) o).id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public String toString() {
    return new StringBuilder(Case3Child.class.getSimpleName())
               .append("{id=").append(this.id)
               .append(", root=").append(this.root.getId())
               .append(", sequence=").append(this.sequence)
               .append(", createdAt=").append(this.createdAt)
               .append('}').toString();
  }
}
