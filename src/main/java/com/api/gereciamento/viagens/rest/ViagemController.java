package com.api.gereciamento.viagens.rest;

import com.api.gereciamento.viagens.viagem.ViagemInput;
import com.api.gereciamento.viagens.viagem.ViagemOutput;
import com.api.gereciamento.viagens.viagem.ViagemService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("viagens")
public class ViagemController {

    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @GetMapping
    public PagedModel<EntityModel<ViagemOutput>> getAll(){
        return viagemService.getAllPageable();
    }

    @PostMapping
    public ViagemOutput create(@RequestBody ViagemInput viagemInput){
        return viagemService.create(viagemInput);
    }

}
