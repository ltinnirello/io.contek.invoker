package io.contek.invoker.deribit.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.rest.common.RestResponse;
import io.contek.invoker.deribit.api.common._Order;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostOrders extends UserRestRequest<PostOrders.Response> {

  private String market;
  private String side;
  private Double price;
  private String type;
  private Double size;
  private Boolean reduceOnly;
  private Boolean ioc;
  private Boolean postOnly;
  private String clientId;

  PostOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostOrders setMarket(String market) {
    this.market = market;
    return this;
  }

  public PostOrders setSide(String side) {
    this.side = side;
    return this;
  }

  public PostOrders setPrice(Double price) {
    this.price = price;
    return this;
  }

  public PostOrders setType(String type) {
    this.type = type;
    return this;
  }

  public PostOrders setSize(Double size) {
    this.size = size;
    return this;
  }

  public PostOrders setReduceOnly(Boolean reduceOnly) {
    this.reduceOnly = reduceOnly;
    return this;
  }

  public PostOrders setIoc(Boolean ioc) {
    this.ioc = ioc;
    return this;
  }

  public PostOrders setPostOnly(Boolean postOnly) {
    this.postOnly = postOnly;
    return this;
  }

  public PostOrders setClientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/orders";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(market);
    builder.add("market", market);

    requireNonNull(side);
    builder.add("side", side);

    requireNonNull(type);
    builder.add("type", type);

    requireNonNull(size);
    builder.add("size", size);

    if (price != null) {
      builder.add("price", price);
    }

    if (reduceOnly != null) {
      builder.add("reduceOnly", reduceOnly);
    }

    if (ioc != null) {
      builder.add("ioc", ioc);
    }

    if (postOnly != null) {
      builder.add("postOnly", postOnly);
    }

    if (clientId != null) {
      builder.add("clientId", clientId);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_Order> {}
}
