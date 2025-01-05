package org.oracleone.forohub.interfaces;

public interface EntityConverter<DTO,Entity> {

    DTO EntityToDTO(Entity entity);

    Entity DTOtoEntity(DTO dto);

}
