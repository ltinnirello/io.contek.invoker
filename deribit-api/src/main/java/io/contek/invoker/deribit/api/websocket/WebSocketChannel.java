package io.contek.invoker.deribit.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.deribit.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSubscriptionRequest;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSubscriptionResponse;
import io.contek.invoker.deribit.api.websocket.common.constants.WebSocketInboundKeys;
import io.contek.invoker.deribit.api.websocket.common.constants.WebSocketOutboundKeys;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketInboundMessage>
    extends BaseWebSocketChannel<Message> {

  protected abstract String getChannel();

  protected abstract String getMarket();

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = WebSocketOutboundKeys._subscribe;
    request.channel = getChannel();
    request.market = getMarket();
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = WebSocketOutboundKeys._unsubscribe;
    request.channel = getChannel();
    request.market = getMarket();
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof WebSocketSubscriptionResponse) {
      WebSocketSubscriptionResponse confirmation = (WebSocketSubscriptionResponse) message;
      if (!getChannel().equals(confirmation.channel) || !getMarket().equals(confirmation.market)) {
        return null;
      }
      switch (confirmation.type) {
        case WebSocketInboundKeys._subscribed:
          return SUBSCRIBED;
        case WebSocketInboundKeys._unsubscribed:
          return UNSUBSCRIBED;
        default:
          throw new IllegalArgumentException(confirmation.type);
      }
    }
    return null;
  }

  @Override
  protected final void reset() {}
}
