package HC.Banco_Talentos.Application.Service;

import HC.Banco_Talentos.Shared.Exceptions.CurriculoStorageException;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${candidato.curriculo.upload-dir}")
    private String curriculosDir;

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    public String salvarCurriculo(MultipartFile arquivo, String nomeCandidato) {
        try {
            String nomeArquivo = formatarNomeCamelCase(nomeCandidato);

            Path caminhoAbsoluto = Paths.get(curriculosDir).toAbsolutePath().normalize();
            Files.createDirectories(caminhoAbsoluto);

            String nomeFinal = nomeArquivo + "-" + UUID.randomUUID() + "." +
                    FilenameUtils.getExtension(arquivo.getOriginalFilename());

            Path destino = caminhoAbsoluto.resolve(nomeFinal);
            Files.copy(arquivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

            return destino.toString();

        } catch (IOException e) {
            throw new CurriculoStorageException("Erro ao salvar currículo do candidato " + nomeCandidato, e);
        }
    }

    public void excluirCurriculo(String caminhoCompleto) {
        if (caminhoCompleto == null || caminhoCompleto.isBlank()) return;

        try {
            Path caminho = Paths.get(caminhoCompleto).toAbsolutePath().normalize();
            if (Files.exists(caminho)) {
                Files.delete(caminho);
            }
        } catch (IOException e) {
            logger.error("Erro ao excluir currículo no caminho: {}", caminhoCompleto, e);
        }
    }

    private String formatarNomeCamelCase(String nome) {
        if (nome == null || nome.isBlank()) return "";

        String[] partes = nome.trim().split("\\s+");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < partes.length; i++) {
            String palavra = partes[i].toLowerCase();
            if (i == 0) {
                builder.append(palavra); // primeira palavra minúscula
            } else {
                builder.append(Character.toUpperCase(palavra.charAt(0)))
                        .append(palavra.substring(1));
            }
        }

        return builder.toString();
    }
}