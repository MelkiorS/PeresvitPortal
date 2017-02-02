package ua.peresvit.service.impl;

import org.springframework.web.multipart.MultipartFile;
import ua.peresvit.config.Constant;
import ua.peresvit.dao.MarkRepository;
import ua.peresvit.entity.Mark;
import ua.peresvit.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkServiceImpl implements MarkService{

    @Autowired
    private MarkRepository markRepository;

    @Override
    public <S extends Mark> S save(S arg0) { return markRepository.save(arg0); }
    @Override
    public Mark findOne(Long arg0) {

        Mark mark =  markRepository.findOne(arg0);
        //Hibernate.initialize(mark.getUsers());
        return mark;
    }
    @Override
    public java.util.List<Mark> findAll() {return markRepository.findAll(); }

    @Override
    public String saveFile(Mark mark, MultipartFile inputFile) {

        //Files
        if(!inputFile.getOriginalFilename().isEmpty()){

            String fileContentType = inputFile.getContentType();
            String pathFile = Constant.UPLOAD_PATH + "/" + Constant.MARK + "/" + fileContentType;

            String fileURL = Constant.uploadingFile(inputFile, pathFile);

            // don't delete any file,
            // this path could use other post

//            // delete old file
//            if(post.getPostId() != null){
//                String oldPath = post.getUrl();
//                if(!fileURL.equals(oldPath))
//                    Constant.deleteFile(oldPath);
//            }

            return fileURL;
        }

        return null;
    }
}
