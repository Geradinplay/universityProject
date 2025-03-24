package org.acme.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.enums.ItemsStatus;
import org.acme.exception.ItemsException;
import org.acme.model.Profession;

import java.util.List;

@Dependent
public class ProfessionService {
    @Inject
    private EntityManager manager;

    @Transactional
    public Profession createProfession(Profession profession) throws ItemsException {

        List<Profession> professions = getAllProfession();// получаем список предметов

        if (professions.contains(profession)) {// Проверяем на наличие этого предмета в базе данных
            throw new ItemsException(ItemsStatus.EXISTS.getLabel());
        }
        // Добавляем или обновляет существующий предмет
        return manager.merge(profession);
    }

    public List<Profession> getAllProfession() {
        List<Profession> professions = manager.createNamedQuery(Profession.GET_ALL_ITEMS, Profession.class).getResultList();
        return professions;
    }
}
