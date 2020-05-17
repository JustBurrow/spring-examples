package kr.lul.spring.examples.data.jpa.mysql.index.compare.entity;

import javax.persistence.*;
import java.io.Serializable;
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
  @Embeddable
  public static class Case1ChildId implements Serializable {
    @Column(table = "case1_child", name = "root", nullable = false, updatable = false)
    private long root;
    @Column(table = "case1_child", name = "seq", nullable = false, updatable = false)
    private int sequence;

    public Case1ChildId() {
    }

    public Case1ChildId(long root, int sequence) {
      this.root = root;
      this.sequence = sequence;
    }

    public long root() {
      return this.root;
    }

    public int sequence() {
      return this.sequence;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Case1ChildId that = (Case1ChildId) o;
      return this.root == that.root &&
                 this.sequence == that.sequence;
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.root, this.sequence);
    }

    @Override
    public String toString() {
      return format("(%d, %d)", this.root, this.sequence);
    }
  }

  @EmbeddedId
//  @AttributeOverrides({
//      @AttributeOverride(name = "root", column = @Column(name = "root", nullable = false, updatable = false)),
//      @AttributeOverride(name = "sequence", column = @Column(name = "seq", nullable = false, updatable = false))
//  })
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
  @MapsId("sequence")
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
    return format("%s(id=%s, root=%d, sequence=%d, createdAt=%s)",
        Case1Child.class.getSimpleName(), this.id, this.root.getId(), this.sequence, this.createdAt);
  }
}
