package io.contek.invoker.ftx.api.common;

public class _SubAccount {

    public String nickname;
    public boolean deletable;
    public boolean editable;
    public boolean competition;

    @Override
    public String toString() {
        return "_SubAccount{" +
                "nickname='" + nickname + '\'' +
                ", deletable=" + deletable +
                ", editable=" + editable +
                ", competition=" + competition +
                '}';
    }
}
