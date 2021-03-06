package io.contek.invoker.ftx.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serializable;

@NotThreadSafe
public class _WalletBalance implements Serializable {

  public String coin;
  public Double free;
  public Double total;

  @Override
  public String toString() {
    return "_WalletBalance{" + "coin='" + coin + '\'' + ", free=" + free + ", total=" + total + '}';
  }
}
