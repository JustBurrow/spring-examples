package kr.lul.spring.examples.data.jpa.mysql.index.compare;

import kr.lul.common.util.Anchor;

/**
 * @author justburrow
 * @since 2020/05/17
 */
public abstract class DataJpaIndexCompareAnchor extends Anchor {
  public static final Package PACKAGE = DataJpaIndexCompareAnchor.class.getPackage();
  public static final String PACKAGE_NAME = DataJpaIndexCompareAnchor.class.getPackageName();

  protected DataJpaIndexCompareAnchor() {
    throw new UnsupportedOperationException();
  }
}
