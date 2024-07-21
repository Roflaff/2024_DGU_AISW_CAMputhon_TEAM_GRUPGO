package rofla.back.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rofla.back.back.repository.SubjectInfoRepository;
import rofla.back.back.repository.SubjectRepository;

@RequiredArgsConstructor
@Service
public class ExcelService {
    private final SubjectInfoRepository subjectInfoRepository;
    private final SubjectRepository subjectRepository;
}
