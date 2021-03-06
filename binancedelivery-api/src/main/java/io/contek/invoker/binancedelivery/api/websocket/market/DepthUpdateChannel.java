package io.contek.invoker.binancedelivery.api.websocket.market;

import io.contek.invoker.binancedelivery.api.websocket.WebSocketChannel;
import io.contek.invoker.binancedelivery.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketStreamMessage;
import io.contek.invoker.binancedelivery.api.websocket.market.DepthUpdateChannel.Message;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static java.text.MessageFormat.format;

@ThreadSafe
public final class DepthUpdateChannel extends WebSocketChannel<Message> {

  private final String symbol;

  DepthUpdateChannel(String symbol, WebSocketRequestIdGenerator requestIdGenerator) {
    super(requestIdGenerator);
    this.symbol = symbol;
  }

  @Override
  protected String getTopic() {
    return format("{0}@depth@100ms", symbol.toLowerCase());
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
  }

  @Override
  protected boolean accepts(Message message) {
    DepthUpdateEvent data = message.data;
    return symbol.equals(data.s);
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<DepthUpdateEvent> {}
}
