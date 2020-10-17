package io.contek.invoker.binancefutures.api.rest.user;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static io.contek.invoker.commons.api.rest.RestMethod.DELETE;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancefutures.api.rest.common.RestUpdateResponse;
import io.contek.invoker.binancefutures.api.rest.user.PostLeverage.Response;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class PostLeverage extends UserRestRequest<Response> {

  private String symbol;
  private Integer leverage;

  PostLeverage(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostLeverage setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostLeverage setLeverage(Integer leverage) {
    this.leverage = leverage;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected RestMethod getMethod() {
    return DELETE;
  }

  @Override
  protected String getEndpointPath() {
    return "/fapi/v1/leverage";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkNotNull(symbol);
    builder.add("symbol", symbol);

    checkNotNull(leverage);
    builder.add("leverage", leverage);

    builder.add("timestamp", getMillis());

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestUpdateResponse {

    public int leverage;
    public double maxNotionalValue;
  }
}
