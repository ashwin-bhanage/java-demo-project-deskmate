package com.deskmate.model;

import java.time.LocalDateTime;

public class Desk {

    private final long deskId;
    private final String deskCode;
    private final String name;
    private final boolean active;
    private final LocalDateTime createdAt;

    public Desk(long deskId,
                String deskCode,
                String name,
                boolean active,
                LocalDateTime createdAt) {

        this.deskId = deskId;
        this.deskCode = deskCode;
        this.name = name;
        this.active = active;
        this.createdAt = createdAt;
    }

    public long getDeskId() { return deskId; }
    public String getDeskCode() { return deskCode; }
    public String getName() { return name; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
