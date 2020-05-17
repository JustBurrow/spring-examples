package kr.lul.spring.examples.data.jpa.mysql.index.compare.repository;

import kr.lul.common.util.Anchor;

/**
 * @author justburrow
 * @since 2020/05/17
 */
public abstract class RepositoryAnchor extends Anchor {
  public static final Package PACKAGE = RepositoryAnchor.class.getPackage();
  public static final String PACKAGE_NAME = RepositoryAnchor.class.getPackageName();

  protected RepositoryAnchor() {
    throw new UnsupportedOperationException();
  }
}
