package rofla.back.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rofla.back.back.repository.SubjectInfoRepository;

@Service
@RequiredArgsConstructor
public class SubjectInfoService {
    private final SubjectInfoRepository subjectInfoRepository;

}
