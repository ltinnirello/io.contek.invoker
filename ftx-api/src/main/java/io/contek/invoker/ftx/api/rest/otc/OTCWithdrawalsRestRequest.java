package io.contek.invoker.ftx.api.rest.otc;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.ftx.api.rest.RestRequest;

import static com.google.common.base.Preconditions.checkArgument;

abstract class OTCWithdrawalsRestRequest<T> extends RestRequest<T> {
  OTCWithdrawalsRestRequest(IActor actor, RestContext context) {
    super(actor, context);
    checkArgument(!actor.getCredential().isAnonymous());
  }

  @Override
  protected String getEndpointPath() {
    return "/api/otc/withdrawals" + getEndpointPathOTC();
  }

  protected abstract String getEndpointPathOTC();
}
