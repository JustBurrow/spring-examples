package kr.lul.spring.examples.data.jpa.mysql.index.compare.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2020/05/20
 */
@Embeddable
public class Case3ChildId implements Serializable {
  private String root;
  private int sequence;

  public Case3ChildId() {
  }

  public Case3ChildId(String root, int sequence) {
    this.root = root;
    this.sequence = sequence;
  }

  public String root() {
    return this.root;
  }

  public int sequence() {
    return this.sequence;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Case3ChildId that = (Case3ChildId) o;
    return this.sequence == that.sequence &&
               this.root.equals(that.root);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.root, this.sequence);
  }

  @Override
  public String toString() {
    return format("(%s, %d)", this.root, this.sequence);
  }
}
