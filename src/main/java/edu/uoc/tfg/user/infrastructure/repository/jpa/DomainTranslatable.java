package edu.uoc.tfg.user.infrastructure.repository.jpa;

public interface DomainTranslatable<T> {

    T toDomain();

}
