package com.api.gereciamento.viagens.viagem;

import com.api.gereciamento.viagens.core.enums.Status;
import com.api.gereciamento.viagens.viagem.enums.MeioTransporte;
import com.api.gereciamento.viagens.viagemmeiostransporte.ViagemMeiosTransporte;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ViagemServiceImpl implements ViagemService {
    private final ViagemRepository viagemRepository;

    private final ModelMapper modelMapper;
    private final PagedResourcesAssembler<ViagemOutput> pagedResourcesAssembler;

    public ViagemServiceImpl(ViagemRepository viagemRepository, ModelMapper modelMapper, PagedResourcesAssembler<ViagemOutput> pagedResourcesAssembler) {
        this.viagemRepository = viagemRepository;
        this.modelMapper = modelMapper;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    /**
     * Cria uma nova instância de Viagem com base nos dados fornecidos em ViagemInput.
     * <p>
     * Este método é anotado com @Transactional para garantir que todas as operações de banco de dados sejam
     * executadas em uma única transação. Se qualquer operação falhar, toda a transação será revertida.
     *
     * @param viagemInput os dados de entrada necessários para criar uma nova Viagem, encapsulados em um objeto ViagemInput.
     *                    Inclui informações como nome, cidade, estado, país, descrição, custo total, hospedagem,
     *                    número de pessoas, status e uma lista de meios de transporte.
     * @return uma instância de ViagemOutput representando a Viagem criada, mapeada a partir da entidade Viagem salva.
     */
    @Transactional
    public ViagemOutput create(ViagemInput viagemInput) {
        final var viagem = viagemInput.toEntity();
        // vincula os ids dos meios de transporte recebidos à nova Viagem
        vinculaViagemMeiosTransporte(viagem, viagemInput.idsMeiosTransporte());
        final var viagemCriada = viagemRepository.save(viagem);
        // Mapeia a entidade Viagem criada para ViagemOutput e retorna
        return modelMapper.map(viagemCriada, ViagemOutput.class);
    }

    /**
     * Vincula os ids dos meios de transporte à uma viagem
     */
    private void vinculaViagemMeiosTransporte(Viagem viagem, Set<Long> idsMeiosTransporte) {
        // Percorre os idsMeiosTransporte e para cada id adiciona um novo vínculo de ViagemMeiosTransporte à viagem
        idsMeiosTransporte.stream().sorted().forEach(idMeioTransporte ->
                viagem.addMeioTransporte(new ViagemMeiosTransporte(MeioTransporte.fromId(idMeioTransporte)))
        );
    }

    /**
     * Retorna uma página de objetos ViagemOutput, encapsulada em um PagedModel<EntityModel<ViagemOutput>>.
     * <p>
     * Este método busca todas as viagens com o status 'A' no repositório de viagens, mapeia-as para o tipo ViagemOutput,
     * e então converte a página resultante em um PagedModel para garantir uma estrutura JSON estável.
     *
     * @return PagedModel<EntityModel < ViagemOutput>> contendo as viagens com status 'A' ordenadas pela data de criação em ordem decrescente.
     */
    @Override
    public PagedModel<EntityModel<ViagemOutput>> getAllPageable() {
        // Busca todas as viagensEncontradas com status 'A', paginadas e ordenadas pela data de criação em ordem decrescente
        Page<Viagem> viagensEncontradas = viagemRepository.findAllByStatus(Status.A, PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "dataCriacao")));
        // Mapeia cada Viagem para ViagemOutput usando ModelMapper
        List<ViagemOutput> viagemOutputList = viagensEncontradas.stream()
                .map(viagem -> modelMapper.map(viagem, ViagemOutput.class))
                .collect(Collectors.toList());
        // Cria uma nova página de ViagemOutput preservando os metadados de paginação da página original
        Page<ViagemOutput> viagensOutputPage = new PageImpl<>(viagemOutputList, viagensEncontradas.getPageable(), viagensEncontradas.getTotalElements());
        // Converte a página de ViagemOutput para um PagedModel<EntityModel<ViagemOutput>>
        return pagedResourcesAssembler.toModel(viagensOutputPage);
    }

    /**
     * Altera o status de uma viagem
     *
     * @param idViagem
     */
    @Override
    public void updateStatusViagem(Long idViagem) {
        viagemRepository.updateStatusViagem(idViagem);
    }
}
