package io.contek.invoker.bitmex.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketTableDataActionKeys {

  public static final String _partial = "partial";

  public static final String _update = "update";

  public static final String _insert = "insert";

  public static final String _delete = "delete";

  private WebSocketTableDataActionKeys() {}
}
