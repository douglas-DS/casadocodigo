package br.com.casadocodigo.loja.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Component
public class FileSaver {

    private HttpServletRequest request;

    @Autowired
    public FileSaver(HttpServletRequest request) {
        this.request = request;
    }

    public String write(String baseFolder, MultipartFile file) {

        try {

//            String realPath = request.getServletContext().getRealPath("/" + baseFolder); //Use this String in production server
            String realPath = "/home/douglas/Workspace/IdeaProjects/casadocodigo/src/main/resources/static/" + baseFolder;

//            String realPath = "C:\\Users\\X1 Carbon\\IdeaProjects\\casadocodigo/src/main/resources/static/" + baseFolder;
            String path = realPath + "/" + file.getOriginalFilename();
            file.transferTo(new File(path));

            return baseFolder + "/" + file.getOriginalFilename();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
