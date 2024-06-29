package com.api.gereciamento.viagens.rest;

import com.api.gereciamento.viagens.viagem.Viagem;
import com.api.gereciamento.viagens.viagem.ViagemInput;
import com.api.gereciamento.viagens.viagem.ViagemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("viagens")
public class ViagemController {

    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @PostMapping
    public Viagem create(ViagemInput viagemInput){
        return viagemService.create(viagemInput);
    }

}
