package kr.lul.spring.examples.data.jpa.mysql.index.compare.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2020/05/19
 */
@Entity(name = "Case2Child")
@Table(name = "case2_child",
    indexes = @Index(name = "idx_case2_child", columnList = "root ASC, seq ASC"))
public class Case2Child {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  private long id;
  @ManyToOne
  @JoinColumn(name = "root", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "fk_case2_child_pk_case2_root"),
      referencedColumnName = "id")
  private Case2Root root;
  @Column(name = "seq", nullable = false, updatable = false)
  private int sequence;
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  public Case2Child() {
  }

  public Case2Child(Case2Root root, int sequence) {
    this.root = root;
    this.sequence = sequence;
  }

  @PrePersist
  private void prePersist() {
    this.createdAt = Instant.ofEpochMilli(System.currentTimeMillis());
  }

  public long getId() {
    return this.id;
  }

  public Case2Root getRoot() {
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
    if (0L >= this.id || o == null || getClass() != o.getClass()) return false;
    return this.id == ((Case2Child) o).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public String toString() {
    return format("%s(id=%d, root=%d, sequence=%d, createdAt=%s)",
        Case2Child.class.getSimpleName(), this.id, this.root.getId(), this.sequence, this.createdAt);
  }
}
