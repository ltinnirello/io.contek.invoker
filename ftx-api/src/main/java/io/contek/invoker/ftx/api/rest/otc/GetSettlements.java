package io.contek.invoker.ftx.api.rest.otc;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Settlement;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class GetSettlements extends OTCQuotesRestRequest<GetSettlements.Response> {

  private Integer quoteId;
  private Integer before;
  private Double limit;

  public GetSettlements(IActor actor, RestContext context) {
    super(actor, context);
  }

  public Integer getQuoteId() {
    return quoteId;
  }

  public GetSettlements setQuoteId(Integer quoteId) {
    this.quoteId = quoteId;
    return this;
  }

  public Integer getBefore() {
    return before;
  }

  public GetSettlements setBefore(Integer before) {
    this.before = before;
    return this;
  }

  public Double getLimit() {
    return limit;
  }

  public GetSettlements setLimit(Double limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.GET;
  }

  @Override
  protected String getEndpointPathOTC() {
    return "/settlements";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (quoteId != null) {
      builder.add("quoteId", quoteId);
    }

    if (before != null) {
      builder.add("before", before);
    }

    if (limit != null) {
      builder.add("limit", limit);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Settlement>> {}
}
