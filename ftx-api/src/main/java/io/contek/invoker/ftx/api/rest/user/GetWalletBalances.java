package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._WalletBalance;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class GetWalletBalances extends UserRestRequest<GetWalletBalances.Response> {

    public GetWalletBalances(IActor actor, RestContext context) {
        super(actor, context);
    }

    @Override
    protected RestMethod getMethod() {
        return RestMethod.GET;
    }

    @Override
    protected String getEndpointPath() {
        return "/api/wallet/balances";
    }

    @Override
    protected RestParams getParams() {
        return RestParams.empty();
    }

    @Override
    protected Class<Response> getResponseType() {
        return Response.class;
    }

    @NotThreadSafe
    public static final class Response extends RestResponse<List<_WalletBalance>> {
    }
}
