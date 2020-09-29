package com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.controllers;

import com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.dao.IClienteDao;
import com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;
import java.util.Optional;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private IClienteDao iClienteDao;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", iClienteDao.findAll());
        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");

        return "form";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Optional<Cliente> cliente = null;
        if (id > 0) {
            cliente = iClienteDao.findById(id);
        } else {
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Validated Cliente cliente, BindingResult result, Model model , SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de cliente");
            return "form";
        }
        iClienteDao.save(cliente);
        status.setComplete();
        return "redirect:listar";
    }
    @RequestMapping(value="/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id){
        if(id>0){
            iClienteDao.deleteById(id);
        }
        return "redirect:/listar";
    }
}
