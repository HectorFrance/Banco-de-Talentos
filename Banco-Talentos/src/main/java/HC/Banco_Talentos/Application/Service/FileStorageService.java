package HC.Banco_Talentos.Application.Service;

import org.apache.commons.io.FilenameUtils;
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

    public String salvarCurriculo(MultipartFile arquivo, String nomeArquivo) throws IOException {
        Path caminhoAbsoluto = Paths.get(curriculosDir).toAbsolutePath().normalize();
        Files.createDirectories(caminhoAbsoluto);

        String nomeFinal = nomeArquivo + "-" + UUID.randomUUID() + "." +
                FilenameUtils.getExtension(arquivo.getOriginalFilename());

        Path destino = caminhoAbsoluto.resolve(nomeFinal);
        Files.copy(arquivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        return destino.toString();
    }
}