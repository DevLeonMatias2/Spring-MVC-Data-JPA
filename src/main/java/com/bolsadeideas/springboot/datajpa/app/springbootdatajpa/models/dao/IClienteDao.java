package com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.dao;


import com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;



public interface IClienteDao  extends CrudRepository<Cliente,Long> {


}
