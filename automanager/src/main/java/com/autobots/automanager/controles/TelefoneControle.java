package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.ClienteSelecionador;
import com.autobots.automanager.modelo.TelefoneSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {
  @Autowired
  private TelefoneRepositorio RepositorioTelefone;
  
  @Autowired
  private ClienteRepositorio clienteRepositorio;
  
  @Autowired
  private ClienteSelecionador clienteSelecionador;
  
  @Autowired
  private TelefoneSelecionador selecionarTelefone;

  
  
  
  @GetMapping("/telefones")
  public List<Telefone> obterTelefone() {
	  List<Telefone> telefone =RepositorioTelefone.findAll();
    return telefone;
  }

  @GetMapping("telefone/{id}")
  public Telefone obterTelefone(@PathVariable long id) {
    List<Telefone> telefones = RepositorioTelefone.findAll();
    return selecionarTelefone.selecionar(telefones, id);
  }

  @PutMapping("/cadastro")
  public void cadastrarTelefone(@RequestBody Cliente cliente) {
	 List<Cliente> clientes = clienteRepositorio.findAll();
	 Cliente clienteAlvo = clienteSelecionador.selecionar(clientes, cliente.getId());
	 clienteAlvo.getTelefones().addAll(cliente.getTelefones());
	 clienteRepositorio.save(clienteAlvo);
  }

  @DeleteMapping("/excluir")
  public void deletarEndereco(@PathVariable long id) {
    List<Telefone> telefones = RepositorioTelefone.findAll();
    Telefone selecionado = selecionarTelefone.selecionar(telefones, id);
    selecionado.setId(null);
    RepositorioTelefone.save(selecionado);
  }

}
