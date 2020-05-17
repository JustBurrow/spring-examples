package kr.lul.spring.examples.data.jpa.mysql.index.compare.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2020/05/17
 */
@Embeddable
public class Case1ChildId implements Serializable {
  @Column(name = "root", nullable = false, updatable = false)
  private long root;
  @Column(name = "seq", nullable = false, updatable = false)
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
