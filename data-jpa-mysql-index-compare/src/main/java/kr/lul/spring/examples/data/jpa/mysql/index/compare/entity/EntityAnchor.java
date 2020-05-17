package kr.lul.spring.examples.data.jpa.mysql.index.compare.entity;

import kr.lul.common.util.Anchor;

/**
 * @author justburrow
 * @since 2020/05/17
 */
public abstract class EntityAnchor extends Anchor {
  public static final Package PACKAGE = EntityAnchor.class.getPackage();
  public static final String PACKAGE_NAME = EntityAnchor.class.getPackageName();

  protected EntityAnchor() {
    throw new UnsupportedOperationException();
  }
}
