package com.example.demo.mapper;

public interface IMapper <E, D> {
	public E toEntity(D dto);
	
	public D toDto(E entity);
	
	public void toEntityWithNotNullProperties(D dto, E entity);

}
