package com.branow.memoweb.service;

import com.branow.memoweb.dto.score.ScoreSimpleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService {

    List<ScoreSimpleDto> getScoreSimpleDtoAllByModuleId(Integer moduleId);

}
