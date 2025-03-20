package org.acme.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.enums.ItemsStatus;
import org.acme.exception.ItemsException;
import org.acme.model.Item;
import org.acme.model.Item;
import java.util.HashSet;
import java.util.List;

import static org.acme.model.Item.GET_ALL_ITEMS;
import static org.acme.model.Item.GET_ITEM_BY_ID;


@Dependent
public class ItemService {
    @Inject
    EntityManager manager;

    @Transactional
    public Item createItem(Item item) throws ItemsException {

        List<Item> students = getAllItems();// получаем список предметов

        if (students.contains(item)) {// Проверяем на наличие этого предмета в базе данных
            throw new ItemsException(ItemsStatus.EXISTS.getLabel());
        }
       // Добавляем или обнавляет существующий предмет
        return manager.merge(item);
    }

    public List<Item> getAllItems() {
        List<Item> Item = manager.createNamedQuery(GET_ALL_ITEMS, Item.class).getResultList();

        for (Item item : Item) {// temp
            System.out.println("["+item.getId()+"] "+item.getNameOfItem());//Простой вывод предметов для теста
        }
        return Item;
    }

    public Item getItemById(Long id){
        Item item = manager.createNamedQuery(GET_ITEM_BY_ID, Item.class)
                .setParameter("id", id)
                .getSingleResult();
        return item;
    }

    @Transactional
    public Item deleteItemById(Long id) {
        Item item = manager.find(Item.class, id);
        if (item != null) {
            manager.remove(item);
        }
        return item; // Вернем удаленный объект, если нужно
    }
}

//@Transactional
//public List<Student> getStudentsByName(String name) {
//    List<Student> students = em.createNamedQuery(Student.GET_STUDENTS_BY_NAME, Student.class)
//            .setParameter("name", name).getResultList();
//
//    for (Student student : students) {
//        List<Telefon> telefoni = getAllForStudent(student);
//        student.setTelefoni(new HashSet<>(telefoni));
//    }
//
//    return students;
//
//}