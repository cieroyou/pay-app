package com.sera.payapp.banking.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class RequestFirmbankingUpdaedEvent {
    private int firmbankingStatus;

    @Override
    public String toString() {
        return "RequestFirmbankingUpdaedEvent{" +
                "firmbankingStatus=" + firmbankingStatus +
                '}';
    }
}
