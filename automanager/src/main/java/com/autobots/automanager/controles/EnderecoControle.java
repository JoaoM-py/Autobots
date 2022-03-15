package com.autobots.automanager.controles;

import java.util.List;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.ClienteSelecionador;
import com.autobots.automanager.modelo.EnderecoSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {
  @Autowired
  private EnderecoSelecionador selecionador;

  @Autowired
  private ClienteSelecionador selecionadorCliente;

  @Autowired
  private ClienteRepositorio repositorioCliente;

  @Autowired
  private EnderecoRepositorio repositorio;

  @GetMapping("/enderecos")
  public List<Endereco> obterEnderecos() {
    List<Endereco> enderecos = repositorio.findAll();
    return enderecos;
  }

  @GetMapping("/endereco/{id}")
  public Endereco obterEndereco(@PathVariable long id) {
    List<Endereco> enderecos = repositorio.findAll();
    return selecionador.selecionar(enderecos, id);
  }


  @PutMapping("/alterar")
  public void alterarCliente(@RequestBody Cliente cliente) {
    List<Cliente> clientes = repositorioCliente.findAll();
    Cliente selecionado = selecionadorCliente.selecionar(clientes, cliente.getId());
    selecionado.setEndereco(cliente.getEndereco());
    repositorioCliente.save(selecionado);
  }
  
  
  
  @DeleteMapping("/excluir")
  public void deletarEndereco(@PathVariable long id) {
    List<Cliente> clientes = repositorioCliente.findAll();
    Cliente selecionado = selecionadorCliente.selecionar(clientes, id);
    selecionado.setEndereco(null);
    repositorioCliente.save(selecionado);
  }

}
