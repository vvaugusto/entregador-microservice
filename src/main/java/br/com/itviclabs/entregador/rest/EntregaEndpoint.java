package br.com.itviclabs.entregador.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itviclabs.entregador.domain.Entrega;
import br.com.itviclabs.entregador.dto.ConfirmacaoPedidoResponse;
import br.com.itviclabs.entregador.dto.PedidoRequest;
import br.com.itviclabs.entregador.service.EntregaService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/entrega")
@Slf4j
public class EntregaEndpoint {

	@Autowired
	private EntregaService entregaService;
	
	@PostMapping
	public ConfirmacaoPedidoResponse realizarEntrega(@RequestBody PedidoRequest pedidoRequest) {
		log.info("Authenticated user: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		log.info("realizarEntrega(): {} ", pedidoRequest);
		return entregaService.realizarEntrega(pedidoRequest);
	}
	
	@GetMapping
	public Page<Entrega> findAll(@PageableDefault(direction = Direction.ASC) Pageable pageable){
		return entregaService.findAll(pageable);
	}
}
