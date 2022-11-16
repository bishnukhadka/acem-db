package com.acem.db.dao.impl;

import com.acem.db.dao.StudentDao;
import com.acem.db.exception.ExceptionHandler;
import com.acem.db.model.Student;
import com.acem.db.utils.JacksonUtil;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class StudentDaoHibernateImpl implements StudentDao {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("collegePersistenceUnit");

    @Override
    public Optional<List<Student>> getAll() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> studentCriteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = studentCriteriaQuery.from(Student.class);
        studentCriteriaQuery = studentCriteriaQuery.select(studentRoot);
        TypedQuery<Student> studentTypedQuery = entityManager.createQuery(studentCriteriaQuery);
        List<Student> students = studentTypedQuery.getResultList();

        return ExceptionHandler.handle(() -> Optional.of(students), Optional.empty());
    }

    @Override
    public Optional<Student> getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Student student = entityManager.find(Student.class, id);
        return ExceptionHandler.handle(() -> Optional.of(student), Optional.empty());
    }

    @Override
    public Optional<Student> getByEmailAddress(String emailAddress) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Student> typedQuery = entityManager.createQuery("SELECT s FROM Student s WHERE s.email=:email", Student.class);
        typedQuery.setParameter("email", emailAddress);
        Student student = typedQuery.getSingleResult();
        return ExceptionHandler.handle(() -> Optional.of(student), Optional.empty());
    }

    @Override
    public Optional<Student> getByContactNo(String contactNo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Student> typedQuery = entityManager.createQuery("SELECT s FROM Student s WHERE s.contactNo=:contactNo", Student.class);
        typedQuery.setParameter("contactNo", contactNo);
        Student student = typedQuery.getSingleResult();
        return ExceptionHandler.handle(() -> Optional.of(student), Optional.empty());
    }

    @Override
    public Boolean save(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        return ExceptionHandler.handleWithFallBack(() -> {
            entityTransaction.begin();
            entityManager.persist(student);
            entityTransaction.commit();
            return true;
        }, () -> {
            entityTransaction.rollback();
            return false;
        });
    }

    @Override
    public Boolean update(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        return ExceptionHandler.handleWithFallBack(() -> {
            entityTransaction.begin();
            entityManager.merge(student);
            entityTransaction.commit();
            return true;
        }, () -> {
            entityTransaction.rollback();
            return false;
        });
    }

    @Override
    public Boolean delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("DELETE FROM Student s WHERE s.id=:id");
        query.setParameter("id", id);
        EntityTransaction entityTransaction = entityManager.getTransaction();
        return ExceptionHandler.handleWithFallBack(() -> {
            entityTransaction.begin();
            boolean result = query.executeUpdate() > 0;
            entityTransaction.commit();
            return result;
        }, () -> {
            entityTransaction.rollback();
            return false;
        });

    }

}
