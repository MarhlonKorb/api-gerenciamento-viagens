package com.api.gereciamento.viagens.viagem;

import com.api.gereciamento.viagens.core.enums.Status;
import com.api.gereciamento.viagens.viagem.enums.MeioTransporte;
import com.api.gereciamento.viagens.viagemmeiostransporte.ViagemMeiosTransporte;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ViagemServiceImpl implements ViagemService {
    private final ViagemRepository viagemRepository;

    private final ModelMapper modelMapper;

    public ViagemServiceImpl(ViagemRepository viagemRepository, ModelMapper modelMapper) {
        this.viagemRepository = viagemRepository;
        this.modelMapper = modelMapper;
        configureMappings();
    }

    /**
     * Configura os mapeamentos do ModelMapper para converter uma entidade Viagem em um DTO ViagemOutput.
     * <p>
     * Este método define um mapeamento personalizado para transformar o conjunto de ViagemMeiosTransporte de uma Viagem
     * em um conjunto de IDs de MeioTransporte, representados por seus valores ordinais.
     */
    private void configureMappings() {
        modelMapper.typeMap(Viagem.class, ViagemOutput.class).addMappings(mapper -> {
            mapper.using(context -> ((Set<ViagemMeiosTransporte>) context.getSource())
                            .stream()
                            .map(ViagemMeiosTransporte::getIdMeioTransporte) // Obtém o enum MeioTransporte
                            .map(Enum::ordinal) // Converte o enum para seu valor ordinal (int)
                            .collect(Collectors.toSet())) // Coleta os valores ordinais em um Set
                    .map(Viagem::getViagemMeiosTransporte, ViagemOutput::setIdsMeiosTransporte); // Mapeia o Set resultante para ViagemOutput
        });
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
        final var viagem = new Viagem(
                viagemInput.nome(), viagemInput.cidade(), viagemInput.estado(),
                viagemInput.pais(), viagemInput.descricao(), viagemInput.custoTotal(),
                viagemInput.hospedagem(), viagemInput.numeroPessoas(), Status.valueOf(viagemInput.status())
        );
        // Adiciona os meios de transporte à Viagem
        viagemInput.idsMeiosTransporte().forEach(idMeioTransporte ->
                viagem.addMeioTransporte(new ViagemMeiosTransporte(MeioTransporte.fromId(idMeioTransporte)))
        );
        final var viagemCriada = viagemRepository.save(viagem);
        // Mapeia a entidade Viagem criada para ViagemOutput e retorna
        return modelMapper.map(viagemCriada, ViagemOutput.class);
    }

    @Override
    public List<ViagemOutput> getAll() {
        List<Viagem> viagens = viagemRepository.findAll();
        return modelMapper.map(viagens, new TypeToken<List<ViagemOutput>>() {
        }.getType());
    }

    @Override
    public void updateStatusViagem(Long idViagem) {
        viagemRepository.updateStatusViagem(idViagem);
    }
}
