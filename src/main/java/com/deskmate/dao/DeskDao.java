package com.deskmate.dao;

import com.deskmate.model.Desk;

import java.util.List;
import java.util.Optional;

public interface DeskDao {

    long insertDesk(String code, String name);

    void deactivateDesk(long deskId);

    Optional<Desk> findByCode(String code);

    List<Desk> listActive();
}
