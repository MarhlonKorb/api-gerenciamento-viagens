package com.api.gereciamento.viagens.rest;

import com.api.gereciamento.viagens.viagem.Viagem;
import com.api.gereciamento.viagens.viagem.ViagemInput;
import com.api.gereciamento.viagens.viagem.ViagemOutput;
import com.api.gereciamento.viagens.viagem.ViagemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("viagens")
public class ViagemController {

    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @GetMapping
    public List<ViagemOutput> getAll(){
        return viagemService.getAll();
    }

    @PostMapping
    public ViagemOutput create(@RequestBody ViagemInput viagemInput){
        return viagemService.create(viagemInput);
    }

}
