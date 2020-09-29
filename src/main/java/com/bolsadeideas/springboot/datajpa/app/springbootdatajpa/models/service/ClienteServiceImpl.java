package com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.service;


import com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.dao.IClienteDao;
import com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteDao iClienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {

        return (List<Cliente>) iClienteDao.findAll();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        iClienteDao.save(cliente);

    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return iClienteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        iClienteDao.deleteById(id);

    }
}
