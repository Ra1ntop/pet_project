package com.ra1ntest.persistance.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatusType {
    CONFIRMING("Pending"),
    CANCELED("Canceled"),
    SENT("Shipped"),
    DELIVERED("Delivered");

    private final String orderStatus;
}
