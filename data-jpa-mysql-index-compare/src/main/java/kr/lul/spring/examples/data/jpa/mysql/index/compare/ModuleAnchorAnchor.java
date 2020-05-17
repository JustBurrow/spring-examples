package kr.lul.spring.examples.data.jpa.mysql.index.compare;

import kr.lul.common.util.Anchor;

/**
 * @author justburrow
 * @since 2020/05/17
 */
public abstract class ModuleAnchorAnchor extends Anchor {
  public static final Package PACKAGE = ModuleAnchorAnchor.class.getPackage();
  public static final String PACKAGE_NAME = ModuleAnchorAnchor.class.getPackageName();

  protected ModuleAnchorAnchor() {
    throw new UnsupportedOperationException();
  }
}
